package com.rogge.ctwo.bus.my_frag;


import com.rogge.base.BasePresenter;
import com.rogge.ctwo.bean.MyBean;


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

public interface MyContract {

    interface View {
        void getDataSuc(MyBean myBean);
    }

    abstract class Presenter extends BasePresenter{
        public abstract void lodeDataRequest();
    }
}
