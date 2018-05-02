package com.example.rogge.bus.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

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

public class DependentBehavior extends CoordinatorLayout.Behavior<View>{

    public DependentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int leftTop = dependency.getTop() - child.getTop();
        ViewCompat.offsetTopAndBottom(child,leftTop);
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
