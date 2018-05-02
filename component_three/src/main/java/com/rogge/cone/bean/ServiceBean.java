package com.rogge.cone.bean;

import java.io.Serializable;

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
public class ServiceBean implements Serializable {
    private int serviceLinkId;
    private String serviceLinkName;
    private String remarkOne;
    private String serciceLinkUrlF;

    public int getServiceLinkId() {
        return serviceLinkId;
    }

    public void setServiceLinkId(int serviceLinkId) {
        this.serviceLinkId = serviceLinkId;
    }

    public String getServiceLinkName() {
        return serviceLinkName;
    }

    public void setServiceLinkName(String serviceLinkName) {
        this.serviceLinkName = serviceLinkName;
    }

    public String getRemarkOne() {
        return remarkOne;
    }

    public void setRemarkOne(String remarkOne) {
        this.remarkOne = remarkOne;
    }

    public String getSerciceLinkUrlF() {
        return serciceLinkUrlF;
    }

    public void setSerciceLinkUrlF(String serciceLinkUrlF) {
        this.serciceLinkUrlF = serciceLinkUrlF;
    }
}
