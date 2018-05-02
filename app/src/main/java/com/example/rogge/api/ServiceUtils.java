package com.example.rogge.api;

import android.app.Application;
import android.util.Log;

import com.example.rogge.R;
import com.rogge.base.BaseModule;
import com.rogge.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Cookie;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceUtils extends BaseModule {

    private static final String BASE_PATH = "http://gank.io/api/";

    private static final int DISK_CACHE_SIZE = 30 * 1024 * 1024; // 10MB

    private static final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    private static OkHttpClient okClient = null;

    private ServiceUtils() {
    }

    public static void init(Application app) {
        File baseDir = app.getCacheDir();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Log.i("OkHttp", message));
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        if (baseDir != null) {
            File cacheDir = new File(app.getCacheDir(), "httpCache");
            Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
            builder.cache(cache);
        }
        builder.connectionPool(new ConnectionPool(0, 1, TimeUnit.SECONDS));
        builder.readTimeout(30L, TimeUnit.SECONDS);
        builder.writeTimeout(30L, TimeUnit.SECONDS);
        builder.connectTimeout(30L, TimeUnit.SECONDS);
        builder.cookieJar(new JavaNetCookieJar(cookieManager));
        try {
            KeyStore ksTrust = KeyStore.getInstance("BKS");
            InputStream instream = Utils.getContext().getResources().openRawResource(R.raw.cacerts);
            ksTrust.load(instream, "".toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ksTrust);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            // TODO: 2018/5/1 0001 by Rogge 使用https则打开
//            builder.sslSocketFactory(sslContext.getSocketFactory());
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | KeyManagementException e) {
            e.printStackTrace();
        }
        okClient = builder.build();
    }

    public static void resetCookieStore() {
        cookieStore.clear();
    }

    public static MainService provideCommonService() {
        String wfKey = MainService.class.getName() + BASE_PATH;
        MainService service = BaseModule.getReferenceInstance(wfKey);
        if (service == null) {
            service = new Retrofit.Builder()
                    .baseUrl(BASE_PATH)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okClient)
                    .build()
                    .create(MainService.class);
            BaseModule.weakReferenceInstance(wfKey, service);
        }
        return service;
    }

}
