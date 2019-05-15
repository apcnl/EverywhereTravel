package com.example.apcnl.travel.bean;

import java.util.List;

/**
 * Created by apcnl on 2019/5/13.
 */

public class ThemeBean {


    /**
     * code : 0
     * desc :
     * result : {"bundles":[{"title":"艺术之旅","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1522654270428_8f11bbbd503ff42905aea3a58e78e807.png","contentURL":"http://cdn.banmi.com/banmiapp/html/1522659154327.html"},{"title":"吃遍中国天南海北","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521542676849_2e57cca0e9fc7faeefce11894845e0c9.jpg","contentURL":"http://cdn.banmi.com/banmiapp/html/1521542677041.html"}]}
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
        private List<BundlesBean> bundles;

        public List<BundlesBean> getBundles() {
            return bundles;
        }

        public void setBundles(List<BundlesBean> bundles) {
            this.bundles = bundles;
        }

        public static class BundlesBean {
            /**
             * title : 艺术之旅
             * cardURL : http://cdn.banmi.com/banmiapp/rahdna/1522654270428_8f11bbbd503ff42905aea3a58e78e807.png
             * contentURL : http://cdn.banmi.com/banmiapp/html/1522659154327.html
             */

            private String title;
            private String cardURL;
            private String contentURL;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCardURL() {
                return cardURL;
            }

            public void setCardURL(String cardURL) {
                this.cardURL = cardURL;
            }

            public String getContentURL() {
                return contentURL;
            }

            public void setContentURL(String contentURL) {
                this.contentURL = contentURL;
            }
        }
    }
}
