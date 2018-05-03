package com.rogge.ctwo.http;

import com.rogge.base_bean.BaseRespModel;
import com.rogge.ctwo.bean.MyBean;

import retrofit2.http.GET;
import rx.Observable;


public interface ComponentTwoService {

    /**
     * 获取我的页面列表数据
     */
    @GET("day/2015/08/07")
    Observable<BaseRespModel<MyBean>> getMyData();

}
