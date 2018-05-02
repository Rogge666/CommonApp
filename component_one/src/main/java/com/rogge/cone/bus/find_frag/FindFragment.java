package com.rogge.cone.bus.find_frag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.rogge.api.ComponentOneAPI;
import com.rogge.base.BaseFragment;
import com.rogge.cone.R;
import com.rogge.cone.R2;
import com.rogge.cone.adapter.FindRVA;
import com.rogge.cone.bean.FindDataBean;

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
@Route(path = ComponentOneAPI.FIND_FRAGMENT)
public class FindFragment extends BaseFragment implements FindContract.View, BaseQuickAdapter.OnItemClickListener {

    @Inject
    FindPresenter findPresenter;
    @BindView(R2.id.frag_find_rv)
    RecyclerView recyclerView;
    @BindView(R2.id.frag_find_refresh_view)
    TwinklingRefreshLayout refreshLayout;

    private FindRVA mFindRVA;

    private int mPage = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {
        DaggerFindComponent.builder()
                .findModule(new FindModule(this))
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
                findPresenter.lodeDataRequest(mPage, false);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                mPage++;
                findPresenter.lodeDataRequest(mPage, mPage == 1);
            }
        });
        findPresenter.lodeDataRequest(mPage, true);
    }

    @Override
    public void getDataSuc(List<FindDataBean> findDataBeen) {
        if (mFindRVA == null) {
            mFindRVA = new FindRVA(findDataBeen);
            mFindRVA.setOnItemClickListener(this);
            recyclerView.setAdapter(mFindRVA);
        } else {
            mFindRVA.addData(findDataBeen);
        }
        if (mPage == 1) {
            refreshLayout.finishRefreshing();
        } else {
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        FindDataBean findDataBean = (FindDataBean) adapter.getData().get(position);
        ARouter.getInstance()
                .build(ComponentOneAPI.WEB_ACTIVITY)
                .withBoolean("isUrl", false)
                .withString("title", findDataBean.getTitle())
                .withString("content", findDataBean.getContent())
                .navigation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (findPresenter != null)
            findPresenter.onDestroy();
    }
}
