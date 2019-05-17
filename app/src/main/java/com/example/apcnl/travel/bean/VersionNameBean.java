package com.example.apcnl.travel.bean;

/**
 * Created by apcnl on 2019/5/16.
 */

public class VersionNameBean {


    /**
     * code : 0
     * desc : 处理成功
     * result : {"info":{"download_url":"http://cdn.banmi.com/banmiapp/apk/banmi_333.apk","version":"3.3.3","recommend":"3.1.3","title":"","feature":"","is_force_update":"2"}}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * info : {"download_url":"http://cdn.banmi.com/banmiapp/apk/banmi_333.apk","version":"3.3.3","recommend":"3.1.3","title":"","feature":"","is_force_update":"2"}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * download_url : http://cdn.banmi.com/banmiapp/apk/banmi_333.apk
             * version : 3.3.3
             * recommend : 3.1.3
             * title :
             * feature :
             * is_force_update : 2
             */

            private String download_url;
            private String version;
            private String recommend;
            private String title;
            private String feature;
            private String is_force_update;

            public String getDownload_url() {
                return download_url;
            }

            public void setDownload_url(String download_url) {
                this.download_url = download_url;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getRecommend() {
                return recommend;
            }

            public void setRecommend(String recommend) {
                this.recommend = recommend;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getFeature() {
                return feature;
            }

            public void setFeature(String feature) {
                this.feature = feature;
            }

            public String getIs_force_update() {
                return is_force_update;
            }

            public void setIs_force_update(String is_force_update) {
                this.is_force_update = is_force_update;
            }
        }
    }
}
