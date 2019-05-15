package com.example.apcnl.travel.bean;

import java.util.List;

/**
 * Created by apcnl on 2019/5/14.
 */

public class BanmiParticularsPathBean {


    /**
     * code : 0
     * desc :
     * result : {"count":1,"page":1,"limit":20,"routes":[{"id":164,"cityID":39,"priceInCents":190,"title":"吴晓波的伊豆之旅","intro":"山脉温泉滋养出的文学半岛","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1516009848977_411a5e77eb17af977631c0bdff38f4cb.jpg","videoURL":"","sequence":-750,"isPurchased":false,"isCollected":false,"city":"日本·静冈","price":"1.9","date":"2018-01-05 19:06"}]}
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
         * count : 1
         * page : 1
         * limit : 20
         * routes : [{"id":164,"cityID":39,"priceInCents":190,"title":"吴晓波的伊豆之旅","intro":"山脉温泉滋养出的文学半岛","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1516009848977_411a5e77eb17af977631c0bdff38f4cb.jpg","videoURL":"","sequence":-750,"isPurchased":false,"isCollected":false,"city":"日本·静冈","price":"1.9","date":"2018-01-05 19:06"}]
         */

        private int count;
        private int page;
        private int limit;
        private List<RoutesBean> routes;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

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

        public List<RoutesBean> getRoutes() {
            return routes;
        }

        public void setRoutes(List<RoutesBean> routes) {
            this.routes = routes;
        }

        public static class RoutesBean {
            /**
             * id : 164
             * cityID : 39
             * priceInCents : 190
             * title : 吴晓波的伊豆之旅
             * intro : 山脉温泉滋养出的文学半岛
             * cardURL : http://cdn.banmi.com/banmiapp/rahdna/1516009848977_411a5e77eb17af977631c0bdff38f4cb.jpg
             * videoURL :
             * sequence : -750
             * isPurchased : false
             * isCollected : false
             * city : 日本·静冈
             * price : 1.9
             * date : 2018-01-05 19:06
             */

            private int id;
            private int cityID;
            private int priceInCents;
            private String title;
            private String intro;
            private String cardURL;
            private String videoURL;
            private int sequence;
            private boolean isPurchased;
            private boolean isCollected;
            private String city;
            private String price;
            private String date;

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

            public int getPriceInCents() {
                return priceInCents;
            }

            public void setPriceInCents(int priceInCents) {
                this.priceInCents = priceInCents;
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

            public String getCardURL() {
                return cardURL;
            }

            public void setCardURL(String cardURL) {
                this.cardURL = cardURL;
            }

            public String getVideoURL() {
                return videoURL;
            }

            public void setVideoURL(String videoURL) {
                this.videoURL = videoURL;
            }

            public int getSequence() {
                return sequence;
            }

            public void setSequence(int sequence) {
                this.sequence = sequence;
            }

            public boolean isIsPurchased() {
                return isPurchased;
            }

            public void setIsPurchased(boolean isPurchased) {
                this.isPurchased = isPurchased;
            }

            public boolean isIsCollected() {
                return isCollected;
            }

            public void setIsCollected(boolean isCollected) {
                this.isCollected = isCollected;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
