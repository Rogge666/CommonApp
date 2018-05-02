package com.rogge.cone.http;

import android.app.Application;
import android.util.Log;

import com.rogge.base.BaseModule;

import java.io.File;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message ->  Log.i("OkHttp", message));
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
        okClient = builder.build();
    }

    public static void resetCookieStore() {
        cookieStore.clear();
    }

    public static ComponentOneService provideCommonService() {
        String wfKey = ComponentOneService.class.getName() + BASE_PATH;
        ComponentOneService service = BaseModule.getReferenceInstance(wfKey);
        if (service == null) {
            service = new Retrofit.Builder()
                    .baseUrl(BASE_PATH)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okClient)
                    .build()
                    .create(ComponentOneService.class);
            BaseModule.weakReferenceInstance(wfKey, service);
        }
        return service;
    }
}
