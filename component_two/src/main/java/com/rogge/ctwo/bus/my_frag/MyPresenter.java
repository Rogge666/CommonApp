package com.rogge.ctwo.bus.my_frag;

import android.support.v4.app.Fragment;

import com.rogge.base.BaseSubscriber;
import com.rogge.ctwo.bean.MyBean;
import com.rogge.ctwo.http.ComponentTwoDataManager;

import javax.inject.Inject;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2018/5/2 0002.
 * @since 1.0.0
 */

public class MyPresenter extends MyContract.Presenter {

    private MyContract.View view;

    @Inject
    public MyPresenter(MyContract.View view) {
        this.view = view;
    }

    @Override
    public void lodeDataRequest() {
        mRxManager.add(ComponentTwoDataManager.getInstants().getMyData()
                .subscribe(new BaseSubscriber<MyBean>(((Fragment) view).getActivity()) {
                    @Override
                    public void onSucCall(MyBean response) {
                        view.getDataSuc(response);
                    }
                }));
    }

}
