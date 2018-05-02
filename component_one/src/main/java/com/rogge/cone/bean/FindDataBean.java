package com.rogge.cone.bean;

import java.io.Serializable;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2018/5/1 0001.
 * @since 1.0.0
 */

public class FindDataBean implements Serializable {

    /**
     * _id : 5aa5cd7e421aa910426a189c
     * content : <p><img alt="" src="https://ws1.sinaimg.cn/large/610dc034ly1fp9qm6nv50j20u00miacg.jpg" /></p>
     * created_at : 2018-03-12T08:44:46.969Z
     * publishedAt : 2018-03-12T08:44:00.0Z
     * rand_id : 41acf292-57f2-445a-83df-40e720e9fe10
     * title : 今日力推：Android Dropdown 按钮效果 / Blockchain 区块链-学习资源汇总
     * updated_at : 2018-03-12T08:44:46.969Z
     */

    private String _id;
    private String content;
    private String created_at;
    private String publishedAt;
    private String rand_id;
    private String title;
    private String updated_at;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getRand_id() {
        return rand_id;
    }

    public void setRand_id(String rand_id) {
        this.rand_id = rand_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
