package com.rogge.cone.bus;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rogge.base.BaseFragment;
import com.rogge.cone.adapter.ServiceRVA;
import com.rogge.cone.bean.ServiceBean;
import com.rogge.cone.common.Constans;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;


/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/6/23.
 * @since 1.0.0
 */
@Route(path = "/service/fragmentService")
public class FragmentService extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
    RecyclerView mRvService;

    private List<ServiceBean> mServiceBeen = new ArrayList<>();
    private ServiceRVA mServiceRVA;
    private int mPremissionsId;

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @Override
    protected void initView() {
        mRvService.setLayoutManager(new GridLayoutManager(getActivity(), 2));

    }

    private void onRxBus(){
        mRxManager.on(Constans.GET_MAIN_TAG, new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                if (integer == Constans.GET_SERVICE_DATA) getServiceData();
            }
        });
    }

    private void getServiceData() {
        /*ParamsUtilV2 lParamsUtilV2 = new ParamsUtilV2();
        lParamsUtilV2.put("projectCode", AppConfiguration.getInstance().getUserInfo().getProjectCode());
        lParamsUtilV2.put("permissionsId", mPremissionsId);
        mRxManager.add(ServiceUtils.provideSMService().getService(lParamsUtilV2.getParams("myService"))
                .compose(RxSchedulers.<TServiceBean>io_main())
                .subscribe(new BaseSubscriber<TServiceBean>(getActivity()) {
                    @Override
                    public void onSucCall(TServiceBean response) {

                    }

                }));*/
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
        ServiceBean lServiceBean = mServiceBeen.get(position);
    }
}
