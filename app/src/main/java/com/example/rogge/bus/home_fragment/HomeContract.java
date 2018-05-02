package com.example.rogge.bus.home_fragment;


import com.example.rogge.bean.HomeDataBean;
import com.rogge.base.BasePresenter;

import java.util.List;


/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2016/12/23.
 * @since 1.0.0
 */

public interface HomeContract {

    interface View {
        void getDataSuc(List<HomeDataBean> mainDataBeen);
    }

    abstract class Presenter extends BasePresenter{
        public abstract void lodeDataRequest(int page, boolean isFirst);
    }
}
