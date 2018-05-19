package com.joy.freeread.bean.gank;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by admin on 2018/5/15.
 */
public class 瞎推荐Bean implements MultiItemEntity, Serializable {
    /**
     * _id : 5a98a4f6421aa910426a1886
     * createdAt : 2018-03-02T09:12:22.309Z
     * desc : 高性能 Web 缓存服务器 nuster 1.7.9.6 发布
     * publishedAt : 2018-03-12T08:44:50.326Z
     * source : web
     * type : 瞎推荐
     * url : https://github.com/jiangwenyuan/nuster/releases/tag/v1.7.9.6
     * used : true
     * who : null
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private Object who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Object getWho() {
        return who;
    }

    public void setWho(Object who) {
        this.who = who;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
