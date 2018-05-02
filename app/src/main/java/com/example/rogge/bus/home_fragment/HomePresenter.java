package com.example.rogge.bus.home_fragment;

import android.support.v4.app.Fragment;

import com.example.rogge.bean.HomeDataBean;
import com.example.rogge.bus.MainAppDataManager;
import com.rogge.base.BaseSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2016/12/23.
 * @since 1.0.0
 */

public class HomePresenter extends HomeContract.Presenter {

    private HomeContract.View view;

    @Inject
    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void lodeDataRequest(int page, boolean isFirst) {
        mRxManager.add(MainAppDataManager.getInstants().getHomeData(10, page).subscribe(new BaseSubscriber<List<HomeDataBean>>(((Fragment) view).getActivity(), isFirst) {
            @Override
            public void onSucCall(List<HomeDataBean> response) {
                view.getDataSuc(response);
            }
        }));
    }

}
