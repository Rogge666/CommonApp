package com.example.rogge.bus;

import com.example.rogge.api.ServiceUtils;
import com.example.rogge.bean.HomeDataBean;
import com.rogge.base_bean.BaseRespModel;
import com.rogge.baserx.RxSchedulers;

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
public class MainAppDataManager {

    private static volatile MainAppDataManager INSTANTS;

    public static MainAppDataManager getInstants(){
        if (INSTANTS == null){
            synchronized (MainAppDataManager.class){
                if (INSTANTS == null){
                    INSTANTS = new MainAppDataManager();
                }
            }
        }
        return INSTANTS;
    }

    public Observable<BaseRespModel<List<HomeDataBean>>> getHomeData(int pageSize, int page) {
        return ServiceUtils.provideCommonService().getHomeData(pageSize, page).compose(RxSchedulers.io_main());
    }

}
