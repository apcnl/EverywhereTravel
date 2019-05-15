package com.example.apcnl.travel.bean;

import java.util.List;

/**
 * Created by apcnl on 2019/5/12.
 */

public class MeCollectBean {


    /**
     * code : 0
     * desc : 处理成功
     * result : {"page":1,"limit":20,"count":7,"collectedRoutes":[{"id":196,"cityID":52,"title":"濑户内海艺术双岛","intro":"直岛·丰岛美术馆巡礼","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":204,"cityID":54,"title":"清迈美食","intro":"舌尖上的\u201c泰北玫瑰\u201d","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1522380380748_6145161c36f9e41ba37225d1e01a2de6.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":199,"cityID":53,"title":"厦门·鼓浪屿","intro":"文艺情怀厦之味","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521714816208_c314aeb52357c354eb51ad82e129864c.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":216,"cityID":64,"title":"新西兰 北岛","intro":"5天地热探险自驾游","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1540982638615_c7bd2fad4079a48261ab4dd0e0c7d1e9.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":214,"cityID":63,"title":"苏州 姑苏区","intro":"走一回姑苏繁华图","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1525418415881_d0239f0bc75e95e82fb5563be1ddfb7e.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":209,"cityID":46,"title":"莫斯科博物馆巡礼","intro":"探寻莫斯科的历史文化精髓","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1523618947824_4b301684903fe9fc6623df712be83a99.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":198,"cityID":51,"title":"环西澳自驾","intro":"沿印度洋海岸线探索奇澳之旅","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521105648687_4d84cb8e74f4dc80a9282bef22cd3c57.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"}]}
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
         * page : 1
         * limit : 20
         * count : 7
         * collectedRoutes : [{"id":196,"cityID":52,"title":"濑户内海艺术双岛","intro":"直岛·丰岛美术馆巡礼","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":204,"cityID":54,"title":"清迈美食","intro":"舌尖上的\u201c泰北玫瑰\u201d","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1522380380748_6145161c36f9e41ba37225d1e01a2de6.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":199,"cityID":53,"title":"厦门·鼓浪屿","intro":"文艺情怀厦之味","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521714816208_c314aeb52357c354eb51ad82e129864c.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":216,"cityID":64,"title":"新西兰 北岛","intro":"5天地热探险自驾游","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1540982638615_c7bd2fad4079a48261ab4dd0e0c7d1e9.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":214,"cityID":63,"title":"苏州 姑苏区","intro":"走一回姑苏繁华图","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1525418415881_d0239f0bc75e95e82fb5563be1ddfb7e.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":209,"cityID":46,"title":"莫斯科博物馆巡礼","intro":"探寻莫斯科的历史文化精髓","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1523618947824_4b301684903fe9fc6623df712be83a99.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":198,"cityID":51,"title":"环西澳自驾","intro":"沿印度洋海岸线探索奇澳之旅","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521105648687_4d84cb8e74f4dc80a9282bef22cd3c57.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"}]
         */

        private int page;
        private int limit;
        private int count;
        private List<CollectedRoutesBean> collectedRoutes;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<CollectedRoutesBean> getCollectedRoutes() {
            return collectedRoutes;
        }

        public void setCollectedRoutes(List<CollectedRoutesBean> collectedRoutes) {
            this.collectedRoutes = collectedRoutes;
        }

        public static class CollectedRoutesBean {
            /**
             * id : 196
             * cityID : 52
             * title : 濑户内海艺术双岛
             * intro : 直岛·丰岛美术馆巡礼
             * priceInCents : 190
             * cardURL : http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg
             * isPurchased : false
             * createdAt : 2019-05-10
             * price : 1.90
             */

            private int id;
            private int cityID;
            private String title;
            private String intro;
            private int priceInCents;
            private String cardURL;
            private boolean isPurchased;
            private String createdAt;
            private String price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCityID() {
                return cityID;
            }

            public void setCityID(int cityID) {
                this.cityID = cityID;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public int getPriceInCents() {
                return priceInCents;
            }

            public void setPriceInCents(int priceInCents) {
                this.priceInCents = priceInCents;
            }

            public String getCardURL() {
                return cardURL;
            }

            public void setCardURL(String cardURL) {
                this.cardURL = cardURL;
            }

            public boolean isIsPurchased() {
                return isPurchased;
            }

            public void setIsPurchased(boolean isPurchased) {
                this.isPurchased = isPurchased;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
