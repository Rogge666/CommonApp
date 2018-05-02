package com.rogge.cone.http;


import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by citic03 on 2017/6/1.
 */

public interface SMService {

    /**
     * 获取我的服务
     */
    @FormUrlEncoded
    @POST("myService.do")
    Observable<TServiceBean> getService(@FieldMap Map<String, String> params);

}
