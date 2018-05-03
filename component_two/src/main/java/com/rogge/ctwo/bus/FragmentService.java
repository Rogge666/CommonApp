package com.rogge.ctwo.bus;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.rogge.base.BaseFragment;


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
@Route(path = "/service/fragmentService")
public class FragmentService extends BaseFragment {

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @Override
    protected void initView() {

    }

}
