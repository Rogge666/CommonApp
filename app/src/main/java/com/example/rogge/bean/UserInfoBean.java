package com.example.rogge.bean;

import java.io.Serializable;

/**
 * 在此写用途
 * 个人信息Bean对象
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: gd.fjtsoft.com.fun.model.bean.UserInfoBean.java
 * @author: Rogge
 * @date: 2016-06-20 17:06
 */
public class UserInfoBean implements Serializable {

    /**
     * HeadPhoto :
     * Name : 中华
     * Phone : 18118702833
     * CityName : 深圳市
     * CorporateName : 优房优派
     * alias : 18118702833
     * tag : 优房优派,深圳市
     * StoreAdress : 所属门店地址
     */

    private String HeadPhoto;
    private String Name;
    private String Phone;
    private String CityName;
    private String CorporateName;
    private String alias;
    private String tag;
    private String StoreAdress;

    private UserInfoBean() {
    }

    public String getStoreAdress() {
        return StoreAdress;
    }

    public void setStoreAdress(String storeAdress) {
        StoreAdress = storeAdress;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getHeadPhoto() {
        return HeadPhoto;
    }

    public void setHeadPhoto(String HeadPhoto) {
        this.HeadPhoto = HeadPhoto;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public String getCorporateName() {
        return CorporateName;
    }

    public void setCorporateName(String CorporateName) {
        this.CorporateName = CorporateName;
    }
}
