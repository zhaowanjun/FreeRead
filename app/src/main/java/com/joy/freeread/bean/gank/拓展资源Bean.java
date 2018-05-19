package com.joy.freeread.bean.gank;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by admin on 2018/5/15.
 */
public class 拓展资源Bean implements MultiItemEntity, Serializable {
    /**
     * _id : 5a96219b421aa9106fb5f460
     * createdAt : 2018-02-28T11:27:23.566Z
     * desc : 一个帮你轻松完成Api接口调试的IDEA插件
     * publishedAt : 2018-03-12T08:44:50.326Z
     * source : web
     * type : 拓展资源
     * url : https://github.com/fingerart/ApiDebugger
     * used : true
     * who : 指尖上的艺术
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

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

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}

