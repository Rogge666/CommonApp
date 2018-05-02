package com.rogge.cone.http;


import com.rogge.base_bean.BaseRespModel;
import com.rogge.cone.bean.FindDataBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by citic03 on 2017/6/1.
 */

public interface ComponentOneService {

    /**
     * 获取发现页面列表数据
     */
    @GET("history/content/{pageSize}/{page}")
    Observable<BaseRespModel<List<FindDataBean>>> getFindData(@Path("pageSize") int pageSize, @Path("page") int page);

}
