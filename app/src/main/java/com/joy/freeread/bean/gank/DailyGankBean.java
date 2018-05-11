package com.joy.freeread.bean.gank;

import android.widget.ExpandableListAdapter;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/4/23.
 */
public class DailyGankBean implements Serializable {


    /**
     * category : ["休息视频","Android","iOS","福利","前端"]
     * error : false
     * results : {"Android":[{"_id":"5a5e0fbc421aa91154899281","createdAt":"2018-01-16T22:44:12.875Z","desc":"全面总结WebView遇到的坑及优化","publishedAt":"2018-01-23T08:46:45.132Z","source":"web","type":"Android","url":"https://www.jianshu.com/p/2b2e5d417e10","used":true,"who":"阿韦"},{"_id":"5a600bf9421aa9115b930674","createdAt":"2018-01-18T10:52:41.730Z","desc":"Android 股票图表库","publishedAt":"2018-01-23T08:46:45.132Z","source":"web","type":"Android","url":"https://github.com/donglua/JZAndroidChart","used":true,"who":"东东东鲁"},{"_id":"5a634c83421aa9115b93067c","createdAt":"2018-01-20T22:04:51.258Z","desc":"AndroidGodEye是一个可以在PC浏览器中实时监控Android数据指标（比如性能指标，但是不局限于性能）的工具，你可以通过wifi连接手机和pc，通过pc浏览器实时监控手机性能。","publishedAt":"2018-01-23T08:46:45.132Z","source":"web","type":"Android","url":"https://github.com/Kyson/AndroidGodEye","used":true,"who":"AndroidKy"},{"_id":"5a65a83f421aa91156960028","createdAt":"2018-01-22T17:00:47.798Z","desc":"WhatsNew - 自动展示更新日志的提示库","publishedAt":"2018-01-23T08:46:45.132Z","source":"web","type":"Android","url":"https://github.com/TonnyL/WhatsNew","used":true,"who":"黎赵太郎"},{"_id":"5a65a8f8421aa911577aa7e6","createdAt":"2018-01-22T17:03:52.479Z","desc":"用Lottie把启动界面动起来","publishedAt":"2018-01-23T08:46:45.132Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247489401&idx=1&sn=9eecc9faa9d2dc0ce8bae6d7c45885a9","used":true,"who":"陈宇明"}],"iOS":[{"_id":"5a5f088c421aa91154899285","createdAt":"2018-01-17T16:25:48.635Z","desc":"iOS 一个异步渲染TextKit开源库","images":["http://img.gank.io/977610b0-04b5-49b3-be65-00d76389dd6d"],"publishedAt":"2018-01-23T08:46:45.132Z","source":"web","type":"iOS","url":"https://github.com/12207480/TYText","used":true,"who":"yeBlueColor"},{"_id":"5a60068f421aa91154899288","createdAt":"2018-01-18T10:29:35.552Z","desc":"基于protocol的iOS模块路由和依赖注入框架","images":["http://img.gank.io/08c2da4a-795c-48d7-b470-8ffe5dbd86c4"],"publishedAt":"2018-01-23T08:46:45.132Z","source":"web","type":"iOS","url":"https://github.com/Zuikyo/ZIKRouter","used":true,"who":"Zuik"}],"休息视频":[{"_id":"571cd54667765974f885bf75","createdAt":"2016-04-24T22:16:38.889Z","desc":"搞机番外篇：三个曲面屏环绕打LOL算不算作弊？","publishedAt":"2018-01-23T08:46:45.132Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av4416912/","used":true,"who":"LHF"}],"前端":[{"_id":"5a65aa65421aa91156960029","createdAt":"2018-01-22T17:09:57.815Z","desc":"前端每周清单第 48 期：Slack Webpack 构建优化，CSS 命名规范与用户追踪，Vue.js 单元测试","publishedAt":"2018-01-23T08:46:45.132Z","source":"web","type":"前端","url":"https://zhuanlan.zhihu.com/p/33185341","used":true,"who":"王下邀月熊(Chevalier)"}],"福利":[{"_id":"5a65381a421aa91156960022","createdAt":"2018-01-22T09:02:18.715Z","desc":"1-22","publishedAt":"2018-01-23T08:46:45.132Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20180122090204_A4hNiG_Screenshot.jpeg","used":true,"who":"daimajia"}]}
     */

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class ResultsBean extends AbstractExpandableItem implements MultiItemEntity, Serializable {
        private List<AndroidBean> Android;
        private List<IOSBean> iOS;
        private List<休息视频Bean> 休息视频;
        private List<前端Bean> 前端;
        private List<福利Bean> 福利;

        public List<AndroidBean> getAndroid() {
            return Android;
        }

        public void setAndroid(List<AndroidBean> Android) {
            this.Android = Android;
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

        public List<福利Bean> get福利() {
            return 福利;
        }

        public void set福利(List<福利Bean> 福利) {
            this.福利 = 福利;
        }

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getItemType() {
            return 0;
        }

        public static class AndroidBean extends AbstractExpandableItem implements MultiItemEntity, Serializable {
            /**
             * _id : 5a5e0fbc421aa91154899281
             * createdAt : 2018-01-16T22:44:12.875Z
             * desc : 全面总结WebView遇到的坑及优化
             * publishedAt : 2018-01-23T08:46:45.132Z
             * source : web
             * type : Android
             * url : https://www.jianshu.com/p/2b2e5d417e10
             * used : true
             * who : 阿韦
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
            public int getLevel() {
                return 1;
            }

            @Override
            public int getItemType() {
                return 1;
            }
        }

        public static class IOSBean extends AbstractExpandableItem implements MultiItemEntity, Serializable {
            /**
             * _id : 5a5f088c421aa91154899285
             * createdAt : 2018-01-17T16:25:48.635Z
             * desc : iOS 一个异步渲染TextKit开源库
             * images : ["http://img.gank.io/977610b0-04b5-49b3-be65-00d76389dd6d"]
             * publishedAt : 2018-01-23T08:46:45.132Z
             * source : web
             * type : iOS
             * url : https://github.com/12207480/TYText
             * used : true
             * who : yeBlueColor
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
            private List<String> images;

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

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            @Override
            public int getLevel() {
                return 1;
            }

            @Override
            public int getItemType() {
                return 1;
            }
        }

        public static class 休息视频Bean extends AbstractExpandableItem implements MultiItemEntity, Serializable {
            /**
             * _id : 571cd54667765974f885bf75
             * createdAt : 2016-04-24T22:16:38.889Z
             * desc : 搞机番外篇：三个曲面屏环绕打LOL算不算作弊？
             * publishedAt : 2018-01-23T08:46:45.132Z
             * source : chrome
             * type : 休息视频
             * url : http://www.bilibili.com/video/av4416912/
             * used : true
             * who : LHF
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
            public int getLevel() {
                return 1;
            }

            @Override
            public int getItemType() {
                return 1;
            }
        }

        public static class 前端Bean extends AbstractExpandableItem implements MultiItemEntity, Serializable {
            /**
             * _id : 5a65aa65421aa91156960029
             * createdAt : 2018-01-22T17:09:57.815Z
             * desc : 前端每周清单第 48 期：Slack Webpack 构建优化，CSS 命名规范与用户追踪，Vue.js 单元测试
             * publishedAt : 2018-01-23T08:46:45.132Z
             * source : web
             * type : 前端
             * url : https://zhuanlan.zhihu.com/p/33185341
             * used : true
             * who : 王下邀月熊(Chevalier)
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
            public int getLevel() {
                return 1;
            }

            @Override
            public int getItemType() {
                return 1;
            }
        }

        public static class 福利Bean extends AbstractExpandableItem implements MultiItemEntity, Serializable {
            /**
             * _id : 5a65381a421aa91156960022
             * createdAt : 2018-01-22T09:02:18.715Z
             * desc : 1-22
             * publishedAt : 2018-01-23T08:46:45.132Z
             * source : chrome
             * type : 福利
             * url : http://7xi8d6.com1.z0.glb.clouddn.com/20180122090204_A4hNiG_Screenshot.jpeg
             * used : true
             * who : daimajia
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
            public int getLevel() {
                return 1;
            }

            @Override
            public int getItemType() {
                return 1;
            }
        }
    }
}
