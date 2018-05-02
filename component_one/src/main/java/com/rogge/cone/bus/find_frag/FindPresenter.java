package com.rogge.cone.bus.find_frag;

import android.support.v4.app.Fragment;

import com.rogge.base.BaseSubscriber;
import com.rogge.cone.bean.FindDataBean;
import com.rogge.cone.http.ComponentOneDataManager;

import java.util.List;

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

public class FindPresenter extends FindContract.Presenter {

    private FindContract.View view;

    @Inject
    public FindPresenter(FindContract.View view) {
        this.view = view;
    }

    @Override
    public void lodeDataRequest(int page, boolean isFirst) {
        mRxManager.add(ComponentOneDataManager.getInstants().getFindData(10, page)
                .subscribe(new BaseSubscriber<List<FindDataBean>>(((Fragment) view).getActivity(), isFirst) {
                    @Override
                    public void onSucCall(List<FindDataBean> response) {
                        view.getDataSuc(response);
                    }
                }));
    }
}
