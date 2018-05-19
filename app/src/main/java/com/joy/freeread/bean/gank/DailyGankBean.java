package com.joy.freeread.bean.gank;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/5/15.
 */
public class DailyGankBean implements Serializable{

    /**
     * category : ["休息视频","拓展资源","前端","Android","瞎推荐","App","iOS","福利"]
     * error : false
     * results : {"Android":[{"_id":"5a967b41421aa91071b838f7","createdAt":"2018-02-28T17:49:53.265Z","desc":"MusicLibrary-一个丰富的音频播放SDK","publishedAt":"2018-03-12T08:44:50.326Z","source":"web","type":"Android","url":"https://github.com/lizixian18/MusicLibrary","used":true,"who":"lizixian"},{"_id":"5a9cf1f5421aa9103fff20b1","createdAt":"2018-03-05T15:29:57.435Z","desc":"DirectSelect Dropdown is a selection widget with an ethereal, full-screen modal popup displaying the available choices when the widget is interact with.","images":["http://img.gank.io/90db2f35-2e9d-4d75-b5a9-53ee1719b57b"],"publishedAt":"2018-03-12T08:44:50.326Z","source":"web","type":"Android","url":"https://github.com/Ramotion/direct-select-android","used":true,"who":"Alex Mikhnev"},{"_id":"5a9f4968421aa910426a1890","createdAt":"2018-03-07T10:07:36.87Z","desc":"TextPathView是一个把文字转化为路径动画然后展现出来的自定义控件","images":["http://img.gank.io/bc3987dc-eb33-451f-a901-3e65bd7b666b"],"publishedAt":"2018-03-12T08:44:50.326Z","source":"chrome","type":"Android","url":"https://github.com/totond/TextPathView","used":true,"who":"Jason"},{"_id":"5aa24192421aa9103fff20c3","createdAt":"2018-03-09T16:10:58.75Z","desc":"VirtualXposed：不用 Root，不解锁 BootLoader 也不用刷机就使用 Xposed 模块的APP","publishedAt":"2018-03-12T08:44:50.326Z","source":"web","type":"Android","url":"https://github.com/android-hacker/VirtualXposed","used":true,"who":"weishu"}],"App":[{"_id":"5aa2558e421aa9103fff20c4","createdAt":"2018-03-09T17:36:14.467Z","desc":"(Python3)把Instagram上的用户文章整理成词云。","images":["http://img.gank.io/592fcbe2-1314-4ead-b474-084b749a33f5"],"publishedAt":"2018-03-12T08:44:50.326Z","source":"web","type":"App","url":"https://github.com/realdennis/igcloud","used":true,"who":"Dennis"}],"iOS":[{"_id":"5aa5b368421aa9103d719144","createdAt":"2018-03-12T06:53:28.854Z","desc":"iOS-APP内置调试工具(debugging-tool)","images":["http://img.gank.io/03fb5bd6-2004-4c5a-bfe8-a8efd0fe1f9c"],"publishedAt":"2018-03-12T08:44:50.326Z","source":"api","type":"iOS","url":"https://github.com/DotzuX/DotzuX","used":true,"who":"  李满"}],"休息视频":[{"_id":"58ae6af7421aa957f9dd6dc3","createdAt":"2017-02-23T12:54:15.286Z","desc":"我媽要我告訴你 ","publishedAt":"2018-03-12T08:44:50.326Z","source":"chrome","type":"休息视频","url":"https://v.qq.com/x/page/m0377ib544o.html?start=1","used":true,"who":"lxxself"}],"前端":[{"_id":"5a967887421aa9107543cd93","createdAt":"2018-02-28T17:38:15.933Z","desc":"A tiny but mighty list virtualization library for Angular","publishedAt":"2018-03-12T08:44:50.326Z","source":"web","type":"前端","url":"https://a-jie.github.io/angular-infinite-list","used":true,"who":"A-JIE"},{"_id":"5a9fc070421aa9103fff20b7","createdAt":"2018-03-07T18:35:28.967Z","desc":"前端每周清单第 53 期：Go 与 WebAssembly, React Suspense 演练, CSS 技巧","publishedAt":"2018-03-12T08:44:50.326Z","source":"web","type":"前端","url":"https://zhuanlan.zhihu.com/p/34320651","used":true,"who":"王下邀月熊(Chevalier)"}],"拓展资源":[{"_id":"5a96219b421aa9106fb5f460","createdAt":"2018-02-28T11:27:23.566Z","desc":"一个帮你轻松完成Api接口调试的IDEA插件","publishedAt":"2018-03-12T08:44:50.326Z","source":"web","type":"拓展资源","url":"https://github.com/fingerart/ApiDebugger","used":true,"who":"指尖上的艺术"},{"_id":"5a9c9261421aa9103fff20b0","createdAt":"2018-03-05T08:42:09.572Z","desc":"\u200bBlockchain 区块链-学习资源汇总","publishedAt":"2018-03-12T08:44:50.326Z","source":"chrome","type":"拓展资源","url":"https://news.zossin-tech.com/blockchain-qukuailian-xuexiziyuanhuizong/","used":true,"who":"daimajia"}],"瞎推荐":[{"_id":"5a98a4f6421aa910426a1886","createdAt":"2018-03-02T09:12:22.309Z","desc":"高性能 Web 缓存服务器 nuster 1.7.9.6 发布","publishedAt":"2018-03-12T08:44:50.326Z","source":"web","type":"瞎推荐","url":"https://github.com/jiangwenyuan/nuster/releases/tag/v1.7.9.6","used":true,"who":null}],"福利":[{"_id":"5aa5cc6a421aa9103ed33c7c","createdAt":"2018-03-12T08:40:10.360Z","desc":"3-12","publishedAt":"2018-03-12T08:44:50.326Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fp9qm6nv50j20u00miacg.jpg","used":true,"who":"daimajia"}]}
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

}
