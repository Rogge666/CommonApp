package com.rogge.ctwo;

import com.rogge.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        showToolbar(false);
    }
}
