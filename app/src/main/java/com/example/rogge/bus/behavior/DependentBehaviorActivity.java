package com.example.rogge.bus.behavior;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;

import com.example.rogge.R;
import com.rogge.base.BaseActivity;

import butterknife.BindView;
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

public class DependentBehaviorActivity extends BaseActivity {

    @BindView(R.id.behavior_left_tv)
    TextView mBehaviorLeftTv;

    @Override
    public int getLayoutId() {
        return R.layout.dependent_behavior_activity;
    }

    @Override
    public void initView() {
        mToolBarManager.setTitle("依赖Behavior");
    }

    @OnClick({R.id.behavior_left_tv, R.id.behavior_right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.behavior_left_tv:
                ViewCompat.offsetTopAndBottom(mBehaviorLeftTv, 20);
                break;
            case R.id.behavior_right_tv:
                ViewCompat.offsetTopAndBottom(mBehaviorLeftTv, -20);
                break;
        }

    }

}
