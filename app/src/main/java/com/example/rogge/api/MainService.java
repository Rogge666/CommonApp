package com.example.rogge.api;

import com.example.rogge.bean.HomeDataBean;
import com.rogge.base_bean.BaseRespModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface MainService {

    /**
     * 获取主页面列表数据
     */
    @GET("data/福利/{pageSize}/{page}")
    Observable<BaseRespModel<List<HomeDataBean>>> getHomeData(@Path("pageSize") int pageSize, @Path("page") int page);

}
