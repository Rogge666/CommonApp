package com.rogge.cone;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.rogge.cone.common.Constans;
import com.rogge.base.BaseActivity;

/**
 * Created by Administrator on 2018/4/18.
 */

@Route(path = "/service/test")
public class TestActivity extends BaseActivity{

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {
        mRxManager.post(Constans.GET_MAIN_TAG,Constans.GET_SERVICE_DATA);
    }
}
