package com.example.rogge.bus.home_fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.rogge.R;
import com.example.rogge.bean.HomeDataBean;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.rogge.api.ComponentOneAPI;
import com.rogge.base.BaseFragment;

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
 * @author Created by Rogge on 2018/5/1 0001.
 * @since 1.0.0
 */

public class FragmentHome extends BaseFragment implements HomeContract.View, BaseQuickAdapter.OnItemClickListener {

    @Inject
    HomePresenter homePresenter;

    @BindView(R.id.frag_main_rv)
    RecyclerView recyclerView;
    @BindView(R.id.frag_main_refresh_view)
    TwinklingRefreshLayout refreshLayout;

    private HomeRVA mHomeRVA;

    private int mPage = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        DaggerHomeComponent.builder()
                .homeModule(new HomeModule(this))
                .build().inject(this);
        SinaRefreshView headerView = new SinaRefreshView(getActivity());
        headerView.setArrowResource(R.drawable.xlistview_arrow);
        headerView.setTextColor(0xff745D5C);
        refreshLayout.setHeaderView(headerView);
        LoadingView loadingView = new LoadingView(getActivity());
        refreshLayout.setBottomView(loadingView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout.setAutoLoadMore(true);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                mPage = 1;
                homePresenter.lodeDataRequest(mPage, false);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                mPage++;
                homePresenter.lodeDataRequest(mPage, mPage == 1);
            }
        });
        homePresenter.lodeDataRequest(mPage, true);
    }

    @Override
    public void getDataSuc(List<HomeDataBean> homeDataBeen) {
        if (mHomeRVA == null) {
            mHomeRVA = new HomeRVA(homeDataBeen);
            mHomeRVA.setOnItemClickListener(this);
            recyclerView.setAdapter(mHomeRVA);
        } else {
            mHomeRVA.addData(homeDataBeen);
        }
        if (mPage == 1) {
            refreshLayout.finishRefreshing();
        } else {
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (homePresenter != null)
            homePresenter.onDestroy();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeDataBean homeDataBean = (HomeDataBean) adapter.getData().get(position);
        ARouter.getInstance()
                .build(ComponentOneAPI.WEB_ACTIVITY)
                .withBoolean("isUrl", true)
                .withString("title", homeDataBean.getDesc())
                .withString("content", homeDataBean.getUrl())
                .navigation();
    }
}
