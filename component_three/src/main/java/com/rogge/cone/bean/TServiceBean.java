package com.rogge.cone.bean;

import java.io.Serializable;
import java.util.List;

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
public class TServiceBean extends BaseDataBean implements Serializable {
    private List<ServiceBean> myServiceBean;

    public List<ServiceBean> getMyServiceBean() {
        return myServiceBean;
    }

    public void setMyServiceBean(List<ServiceBean> myServiceBean) {
        this.myServiceBean = myServiceBean;
    }

}
