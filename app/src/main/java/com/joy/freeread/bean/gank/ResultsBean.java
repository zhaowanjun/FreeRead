package com.joy.freeread.bean.gank;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/5/15.
 */
public class ResultsBean implements Serializable{
    private List<AndroidBean> Android;
    private List<AppBean> App;
    private List<IOSBean> iOS;
    private List<休息视频Bean> 休息视频;
    private List<前端Bean> 前端;
    private List<拓展资源Bean> 拓展资源;
    private List<瞎推荐Bean> 瞎推荐;
    private List<福利Bean> 福利;

    public List<AndroidBean> getAndroid() {
        return Android;
    }

    public void setAndroid(List<AndroidBean> Android) {
        this.Android = Android;
    }

    public List<AppBean> getApp() {
        return App;
    }

    public void setApp(List<AppBean> App) {
        this.App = App;
    }

    public List<IOSBean> getIOS() {
        return iOS;
    }

    public void setIOS(List<IOSBean> iOS) {
        this.iOS = iOS;
    }

    public List<休息视频Bean> get休息视频() {
        return 休息视频;
    }

    public void set休息视频(List<休息视频Bean> 休息视频) {
        this.休息视频 = 休息视频;
    }

    public List<前端Bean> get前端() {
        return 前端;
    }

    public void set前端(List<前端Bean> 前端) {
        this.前端 = 前端;
    }

    public List<拓展资源Bean> get拓展资源() {
        return 拓展资源;
    }

    public void set拓展资源(List<拓展资源Bean> 拓展资源) {
        this.拓展资源 = 拓展资源;
    }

    public List<瞎推荐Bean> get瞎推荐() {
        return 瞎推荐;
    }

    public void set瞎推荐(List<瞎推荐Bean> 瞎推荐) {
        this.瞎推荐 = 瞎推荐;
    }

    public List<福利Bean> get福利() {
        return 福利;
    }

    public void set福利(List<福利Bean> 福利) {
        this.福利 = 福利;
    }
}
