package com.rogge.ctwo.http;

import com.rogge.base_bean.BaseRespModel;
import com.rogge.baserx.RxSchedulers;
import com.rogge.ctwo.bean.MyBean;

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
public class ComponentTwoDataManager {

    private static volatile ComponentTwoDataManager INSTANTS;

    public static ComponentTwoDataManager getInstants(){
        if (INSTANTS == null){
            synchronized (ComponentTwoDataManager.class){
                if (INSTANTS == null){
                    INSTANTS = new ComponentTwoDataManager();
                }
            }
        }
        return INSTANTS;
    }

    public Observable<BaseRespModel<MyBean>> getMyData() {
        return ServiceUtils.provideCommonService().getMyData().compose(RxSchedulers.io_main());
    }

}
