package com.rogge.cone;

import com.rogge.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @OnClick(R2.id.service_main_tv)
    public void textOnClick(){
        startActivity(TestActivity.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }
}
