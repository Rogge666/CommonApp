package com.example.rogge.bus.behavior;

import com.example.rogge.R;
import com.rogge.base.BaseActivity;

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

public class ScrollBehaviorActivity extends BaseActivity{

    @Override
    public int getLayoutId() {
        return R.layout.scroll_behavior_activity;
    }

    @Override
    public void initView() {
        mToolBarManager.setTitle("滑动Behavior");
    }
}
