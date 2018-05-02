package com.rogge.cone.http;

import com.rogge.base.BaseModule;
import com.rogge.gson_converter_factory.CustomGsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ServiceUtils extends BaseModule {

    private static OkHttpClient okHttpClient = HttpClientInit.getInstance();

    private ServiceUtils() {
    }

    public static SMService provideSMService() {
        String apiUrl = PathUtils.PATH_HOST;
        String wfKey = SMService.class.getName() + apiUrl;
        SMService service = BaseModule.getReferenceInstance(wfKey);
        if (service == null) {
            service = new Retrofit.Builder()
                    .baseUrl(apiUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(SMService.class);
            BaseModule.weakReferenceInstance(wfKey, service);
        }
        return service;
    }
}
