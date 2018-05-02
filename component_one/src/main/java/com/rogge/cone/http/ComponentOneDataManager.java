package com.rogge.cone.http;

import com.rogge.base_bean.BaseRespModel;
import com.rogge.baserx.RxSchedulers;
import com.rogge.cone.bean.FindDataBean;

import java.util.List;

import rx.Observable;

/**
 * [Description]
 * <p>主APP公用model
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2016/12/23.
 * @since 1.0.0
 */
public class ComponentOneDataManager {

    private static volatile ComponentOneDataManager INSTANTS;

    public static ComponentOneDataManager getInstants(){
        if (INSTANTS == null){
            synchronized (ComponentOneDataManager.class){
                if (INSTANTS == null){
                    INSTANTS = new ComponentOneDataManager();
                }
            }
        }
        return INSTANTS;
    }

    public Observable<BaseRespModel<List<FindDataBean>>> getFindData(int pageSize, int page) {
        return ServiceUtils.provideCommonService().getFindData(pageSize, page).compose(RxSchedulers.io_main());
    }

}
