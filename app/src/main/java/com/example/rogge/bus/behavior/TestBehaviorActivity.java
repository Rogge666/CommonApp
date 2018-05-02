package com.example.rogge.bus.behavior;

import android.view.View;

import com.example.rogge.R;
import com.rogge.base.BaseActivity;

import butterknife.OnClick;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/3/21.
 * @since 1.0.0
 */

public class TestBehaviorActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.test_behavior_activity;
    }

    @Override
    public void initView() {
        mToolBarManager.setTitle("自定义Behavior");
    }

    @OnClick({R.id.dependent_behavior, R.id.scroll_behavior})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dependent_behavior:
                startActivity(DependentBehaviorActivity.class);
                break;
            case R.id.scroll_behavior:
                startActivity(ScrollBehaviorActivity.class);
                break;
        }
    }
}
