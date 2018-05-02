package com.rogge.cone.bus.find_frag;


import com.rogge.base.BasePresenter;
import com.rogge.cone.bean.FindDataBean;

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

public interface FindContract {

    interface View {
        void getDataSuc(List<FindDataBean> findDataBeen);
    }

    abstract class Presenter extends BasePresenter{
        public abstract void lodeDataRequest(int page, boolean isFirst);
    }
}
