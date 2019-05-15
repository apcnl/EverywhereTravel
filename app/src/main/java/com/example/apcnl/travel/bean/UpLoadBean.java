package com.example.apcnl.travel.bean;

/**
 * Created by apcnl on 2019/5/8.
 */

public class UpLoadBean {
    /**
     * code : 200
     * res : 上传文件成功
     * data : {"url":"http://yun918.cn/study/public/uploadfiles/123/944365-ee747d1e331ed5a4.png"}
     */

    private int code;
    private String res;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * url : http://yun918.cn/study/public/uploadfiles/123/944365-ee747d1e331ed5a4.png
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
