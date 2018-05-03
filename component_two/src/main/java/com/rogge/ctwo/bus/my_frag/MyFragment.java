package com.rogge.ctwo.bus.my_frag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rogge.api.ComponentTwoAPI;
import com.rogge.api.MainAPI;
import com.rogge.base.BaseFragment;
import com.rogge.ctwo.R;
import com.rogge.ctwo.R2;
import com.rogge.ctwo.bean.MyBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


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
@Route(path = ComponentTwoAPI.MY_FRAGMENT)
public class MyFragment extends BaseFragment implements MyContract.View, BaseQuickAdapter.OnItemClickListener {

    @Inject
    MyPresenter myPresenter;
    @BindView(R2.id.my_rv)
    RecyclerView recyclerView;

    private MyRVA mMyRVA;

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_my;
    }

    @Override
    protected void initView() {
        DaggerMyComponent.builder()
                .myModule(new MyModule(this))
                .build().inject(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myPresenter.lodeDataRequest();
    }

    @Override
    public void getDataSuc(MyBean myBean) {
        List<MyBean.DetailBean> detailBeen = new ArrayList<>();
        detailBeen.addAll(myBean.getAndroid());
        detailBeen.addAll(myBean.getIOS());
        mMyRVA = new MyRVA(detailBeen);
        mMyRVA.setOnItemClickListener(this);
        recyclerView.setAdapter(mMyRVA);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MyBean.DetailBean detailBean = (MyBean.DetailBean) adapter.getData().get(position);
        ARouter.getInstance()
                .build(MainAPI.TEST_ACTIVITY)
                .withString("title", detailBean.getWho())
                .navigation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (myPresenter != null)
            myPresenter.onDestroy();
    }
}
