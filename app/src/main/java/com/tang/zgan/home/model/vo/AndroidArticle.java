package com.tang.zgan.home.model.vo;

import java.util.List;

/**
 * Created by tangyc on 2017/2/10.
 */

public class AndroidArticle {

    /**
     * error : false
     * results : [{"_id":"589d2bcd421aa9270bc7332c","createdAt":"2017-02-10T10:56:13.792Z","desc":"Android 信用卡提交效果。","images":["http://img.gank.io/0df0d67f-6d39-4880-9a44-b2531ccb3a75"],"publishedAt":"2017-02-10T11:38:22.122Z","source":"chrome","type":"Android","url":"https://github.com/adonixis/android-sumbit-credit-card-flow","used":true,"who":"代码家"},{"_id":"589d2bed421aa92710db9613","createdAt":"2017-02-10T10:56:45.622Z","desc":"简洁优雅的网络状态提示。","images":["http://img.gank.io/93211cbf-d4af-4bc4-ba76-621dfb1dfe40"],"publishedAt":"2017-02-10T11:38:22.122Z","source":"chrome","type":"Android","url":"https://github.com/iammert/StatusView","used":true,"who":"代码家"},{"_id":"58998544421aa970b84523b0","createdAt":"2017-02-07T16:28:52.513Z","desc":"说一说Android Studio和IDEA中一个很有用的内存调试插件","publishedAt":"2017-02-09T11:46:26.70Z","source":"chrome","type":"Android","url":"https://zhuanlan.zhihu.com/p/25110433?utm_source=qq&utm_medium=social","used":true,"who":"LHF"},{"_id":"589ae435421aa92db8a0042b","createdAt":"2017-02-08T17:26:13.290Z","desc":"Android事件传递三部曲：本地广播LocalBroadcastManager","publishedAt":"2017-02-09T11:46:26.70Z","source":"web","type":"Android","url":"https://shaohui.me/2017/01/21/android-message-3-localBroadcast/","used":true,"who":"邵辉Vista"},{"_id":"589bc26d421aa92dc0dfd3c2","createdAt":"2017-02-09T09:14:21.324Z","desc":"一个可以自由定制外观、支持拖拽消除的MaterialDesign风格BadgeView","images":["http://img.gank.io/fa46d770-5ef5-4138-8d98-7779587ce22f"],"publishedAt":"2017-02-09T11:46:26.70Z","source":"web","type":"Android","url":"https://github.com/qstumn/BadgeView","used":true,"who":"Rorbin Qiu"},{"_id":"589bd06e421aa92dba721a72","createdAt":"2017-02-09T10:14:06.671Z","desc":"Android App 内网络调试工具，超实用。","images":["http://img.gank.io/80f0da4d-8fcb-4fe0-8851-d5d012ee6577"],"publishedAt":"2017-02-09T11:46:26.70Z","source":"chrome","type":"Android","url":"https://github.com/jgilfelt/chuck","used":true,"who":"代码家"},{"_id":"589be499421aa92dba721a7a","createdAt":"2017-02-09T11:40:09.338Z","desc":"java垃圾回收机制","publishedAt":"2017-02-09T11:46:26.70Z","source":"web","type":"Android","url":"http://blog.csdn.net/qq_22329521/article/details/51934783","used":true,"who":"FMT"},{"_id":"58999ab4421aa970b84523b3","createdAt":"2017-02-07T18:00:20.561Z","desc":"Kotlin Primer·第三章·Kotlin 与 Java 混编","images":["http://img.gank.io/eb1ab3db-048b-4714-b051-7f874e1dac72"],"publishedAt":"2017-02-08T11:39:51.371Z","source":"web","type":"Android","url":"http://kymjs.com/code/2017/02/07/01/","used":true,"who":"张涛"},{"_id":"589a6a7a421aa92db8a0041a","createdAt":"2017-02-08T08:46:50.694Z","desc":"支持视频 Preview 效果的 Seekbar。","images":["http://img.gank.io/5d54c587-da3a-4fa8-b963-6483016907af"],"publishedAt":"2017-02-08T11:39:51.371Z","source":"chrome","type":"Android","url":"https://github.com/rubensousa/PreviewSeekBar","used":true,"who":"daimajia"},{"_id":"589a83b9421aa92db8a0041e","createdAt":"2017-02-08T10:34:33.370Z","desc":"从源码角度分析NestedScrolling","publishedAt":"2017-02-08T11:39:51.371Z","source":"chrome","type":"Android","url":"http://blog.csdn.net/tyk0910/article/details/54907245","used":true,"who":"LHF"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 589d2bcd421aa9270bc7332c
         * createdAt : 2017-02-10T10:56:13.792Z
         * desc : Android 信用卡提交效果。
         * images : ["http://img.gank.io/0df0d67f-6d39-4880-9a44-b2531ccb3a75"]
         * publishedAt : 2017-02-10T11:38:22.122Z
         * source : chrome
         * type : Android
         * url : https://github.com/adonixis/android-sumbit-credit-card-flow
         * used : true
         * who : 代码家
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
    }
}
