package com.example.apcnl.travel.bean;

import java.util.List;

/**
 * Created by apcnl on 2019/5/10.
 */

public class PathPartIcularsBean {


    /**
     * code : 0
     * desc :
     * result : {"carousel":["http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg"],"route":{"id":196,"banmiID":49,"cityID":52,"priceInCents":190,"title":"濑户内海艺术双岛","intro":"直岛·丰岛美术馆巡礼","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg","videoURL":"","sequence":-898,"description":"你好！我是Charlie，是一个超级日范，每年至少会在日本生活仨月。日本什么好玩、什么好吃、哪里最值得去，问我就没错！\n\n日本有一句俗话：想看山，有「富士」，要泡温泉到「别府」，看海就请到「濑户内」。在日本这个岛国，濑户内海能被列为海的代表，足见其魅力，而这里除了天然的海景，最让人心驰神往的，还要数充满了艺术气息的各个岛屿。这次我就要带你到其中两座具有代表性的艺术之岛，来一场清新舒缓的人文之旅。\n\n这次旅程，我们要改变通常的交通方式，乘坐渡轮或汽船往来于海岛。在濑户内海这片与世隔绝的文艺净域之上，绝不走马观花，放弃打卡血拼；不看繁华，不寻名胜。而是去和安藤忠雄、草间弥生、莫奈等伟大的名字相遇，呼吸带着艺术的空气，把时间挥霍在对美的探求之中。","shareTitle":"濑户内内海艺术巡礼","shareContent":"直岛·丰岛美术馆巡礼","purchasedTimes":1974,"price":"1.9","isPurchased":false,"isCollected":false,"city":"日本·四国地区","shareURL":"http://banmi.com/app2017/route3.html?id=196","shareImageWechat":"http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg"},"banmi":{"id":49,"name":"CharlieChee","location":"北京","occupation":"互联网公司高管 多元文化爱好者","introduction":"你好！我是Charlie，是一个超级日范，每年至少会在日本生活仨月，日本哪里好玩，什么好吃，问我就没错！\n我很喜欢记录我在旅行中的点点滴滴，更喜欢收集不同地方的故事，每次和朋友们分享那些我在旅途中的见闻，还有那些他们听都没听过的故事，都能刷新他们的三观~\n我希望能够把我更多的见闻分享给你，一起看看世界多大多美好！","photo4":"http://cdn.banmi.com/banmiapp/rahdna/1521227637951_18ec1e9841d98c62b39b5e132ddafe6a.jpg","photo":"http://cdn.banmi.com/banmiapp/rahdna/1521227637951_18ec1e9841d98c62b39b5e132ddafe6a.jpg"},"reviews":[{"reviewID":2830,"userName":"柳吱吱","userPhoto":"http://media.banmi.com/photos/1451977372348_ecafb592064dc3a57adb4f0d9de6a03c","content":"冲着心脏音博物馆去的，很早之前就看过介绍，真得值得","createdAt":"2月前","images":[]},{"reviewID":2821,"userName":"绿色的煎茶盒子","userPhoto":"http://media.banmi.com/photos/1450029418632_fa2a08c26814273ff188de5d783b7f65","content":"今年要去艺术祭，好期待，跟着线路走透透，耶！","createdAt":"2月前","images":[]},{"reviewID":2724,"userName":"Hannah","userPhoto":"http://media.banmi.com/photos/1449799173935_641bec90206a1126c31f3509a216be1d","content":"攻略里说的古民居一带非常值得一逛，虽然岛上那些网红的艺术馆也是不能错过，但逛这里更能融入到当地人的生活里的感觉，不像是在逛景点，喜欢这种感觉~","createdAt":"4月前","images":[]}],"reviewsCount":29}
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
         * carousel : ["http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg"]
         * route : {"id":196,"banmiID":49,"cityID":52,"priceInCents":190,"title":"濑户内海艺术双岛","intro":"直岛·丰岛美术馆巡礼","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg","videoURL":"","sequence":-898,"description":"你好！我是Charlie，是一个超级日范，每年至少会在日本生活仨月。日本什么好玩、什么好吃、哪里最值得去，问我就没错！\n\n日本有一句俗话：想看山，有「富士」，要泡温泉到「别府」，看海就请到「濑户内」。在日本这个岛国，濑户内海能被列为海的代表，足见其魅力，而这里除了天然的海景，最让人心驰神往的，还要数充满了艺术气息的各个岛屿。这次我就要带你到其中两座具有代表性的艺术之岛，来一场清新舒缓的人文之旅。\n\n这次旅程，我们要改变通常的交通方式，乘坐渡轮或汽船往来于海岛。在濑户内海这片与世隔绝的文艺净域之上，绝不走马观花，放弃打卡血拼；不看繁华，不寻名胜。而是去和安藤忠雄、草间弥生、莫奈等伟大的名字相遇，呼吸带着艺术的空气，把时间挥霍在对美的探求之中。","shareTitle":"濑户内内海艺术巡礼","shareContent":"直岛·丰岛美术馆巡礼","purchasedTimes":1974,"price":"1.9","isPurchased":false,"isCollected":false,"city":"日本·四国地区","shareURL":"http://banmi.com/app2017/route3.html?id=196","shareImageWechat":"http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg"}
         * banmi : {"id":49,"name":"CharlieChee","location":"北京","occupation":"互联网公司高管 多元文化爱好者","introduction":"你好！我是Charlie，是一个超级日范，每年至少会在日本生活仨月，日本哪里好玩，什么好吃，问我就没错！\n我很喜欢记录我在旅行中的点点滴滴，更喜欢收集不同地方的故事，每次和朋友们分享那些我在旅途中的见闻，还有那些他们听都没听过的故事，都能刷新他们的三观~\n我希望能够把我更多的见闻分享给你，一起看看世界多大多美好！","photo4":"http://cdn.banmi.com/banmiapp/rahdna/1521227637951_18ec1e9841d98c62b39b5e132ddafe6a.jpg","photo":"http://cdn.banmi.com/banmiapp/rahdna/1521227637951_18ec1e9841d98c62b39b5e132ddafe6a.jpg"}
         * reviews : [{"reviewID":2830,"userName":"柳吱吱","userPhoto":"http://media.banmi.com/photos/1451977372348_ecafb592064dc3a57adb4f0d9de6a03c","content":"冲着心脏音博物馆去的，很早之前就看过介绍，真得值得","createdAt":"2月前","images":[]},{"reviewID":2821,"userName":"绿色的煎茶盒子","userPhoto":"http://media.banmi.com/photos/1450029418632_fa2a08c26814273ff188de5d783b7f65","content":"今年要去艺术祭，好期待，跟着线路走透透，耶！","createdAt":"2月前","images":[]},{"reviewID":2724,"userName":"Hannah","userPhoto":"http://media.banmi.com/photos/1449799173935_641bec90206a1126c31f3509a216be1d","content":"攻略里说的古民居一带非常值得一逛，虽然岛上那些网红的艺术馆也是不能错过，但逛这里更能融入到当地人的生活里的感觉，不像是在逛景点，喜欢这种感觉~","createdAt":"4月前","images":[]}]
         * reviewsCount : 29
         */

        private RouteBean route;
        private BanmiBean banmi;
        private int reviewsCount;
        private List<String> carousel;
        private List<ReviewsBean> reviews;

        public RouteBean getRoute() {
            return route;
        }

        public void setRoute(RouteBean route) {
            this.route = route;
        }

        public BanmiBean getBanmi() {
            return banmi;
        }

        public void setBanmi(BanmiBean banmi) {
            this.banmi = banmi;
        }

        public int getReviewsCount() {
            return reviewsCount;
        }

        public void setReviewsCount(int reviewsCount) {
            this.reviewsCount = reviewsCount;
        }

        public List<String> getCarousel() {
            return carousel;
        }

        public void setCarousel(List<String> carousel) {
            this.carousel = carousel;
        }

        public List<ReviewsBean> getReviews() {
            return reviews;
        }

        public void setReviews(List<ReviewsBean> reviews) {
            this.reviews = reviews;
        }

        public static class RouteBean {
            /**
             * id : 196
             * banmiID : 49
             * cityID : 52
             * priceInCents : 190
             * title : 濑户内海艺术双岛
             * intro : 直岛·丰岛美术馆巡礼
             * cardURL : http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg
             * videoURL :
             * sequence : -898
             * description : 你好！我是Charlie，是一个超级日范，每年至少会在日本生活仨月。日本什么好玩、什么好吃、哪里最值得去，问我就没错！

             日本有一句俗话：想看山，有「富士」，要泡温泉到「别府」，看海就请到「濑户内」。在日本这个岛国，濑户内海能被列为海的代表，足见其魅力，而这里除了天然的海景，最让人心驰神往的，还要数充满了艺术气息的各个岛屿。这次我就要带你到其中两座具有代表性的艺术之岛，来一场清新舒缓的人文之旅。

             这次旅程，我们要改变通常的交通方式，乘坐渡轮或汽船往来于海岛。在濑户内海这片与世隔绝的文艺净域之上，绝不走马观花，放弃打卡血拼；不看繁华，不寻名胜。而是去和安藤忠雄、草间弥生、莫奈等伟大的名字相遇，呼吸带着艺术的空气，把时间挥霍在对美的探求之中。
             * shareTitle : 濑户内内海艺术巡礼
             * shareContent : 直岛·丰岛美术馆巡礼
             * purchasedTimes : 1974
             * price : 1.9
             * isPurchased : false
             * isCollected : false
             * city : 日本·四国地区
             * shareURL : http://banmi.com/app2017/route3.html?id=196
             * shareImageWechat : http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg
             */

            private int id;
            private int banmiID;
            private int cityID;
            private int priceInCents;
            private String title;
            private String intro;
            private String cardURL;
            private String videoURL;
            private int sequence;
            private String description;
            private String shareTitle;
            private String shareContent;
            private int purchasedTimes;
            private String price;
            private boolean isPurchased;
            private boolean isCollected;
            private String city;
            private String shareURL;
            private String shareImageWechat;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getBanmiID() {
                return banmiID;
            }

            public void setBanmiID(int banmiID) {
                this.banmiID = banmiID;
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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public String getShareContent() {
                return shareContent;
            }

            public void setShareContent(String shareContent) {
                this.shareContent = shareContent;
            }

            public int getPurchasedTimes() {
                return purchasedTimes;
            }

            public void setPurchasedTimes(int purchasedTimes) {
                this.purchasedTimes = purchasedTimes;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }

            public String getShareImageWechat() {
                return shareImageWechat;
            }

            public void setShareImageWechat(String shareImageWechat) {
                this.shareImageWechat = shareImageWechat;
            }
        }

        public static class BanmiBean {
            /**
             * id : 49
             * name : CharlieChee
             * location : 北京
             * occupation : 互联网公司高管 多元文化爱好者
             * introduction : 你好！我是Charlie，是一个超级日范，每年至少会在日本生活仨月，日本哪里好玩，什么好吃，问我就没错！
             我很喜欢记录我在旅行中的点点滴滴，更喜欢收集不同地方的故事，每次和朋友们分享那些我在旅途中的见闻，还有那些他们听都没听过的故事，都能刷新他们的三观~
             我希望能够把我更多的见闻分享给你，一起看看世界多大多美好！
             * photo4 : http://cdn.banmi.com/banmiapp/rahdna/1521227637951_18ec1e9841d98c62b39b5e132ddafe6a.jpg
             * photo : http://cdn.banmi.com/banmiapp/rahdna/1521227637951_18ec1e9841d98c62b39b5e132ddafe6a.jpg
             */

            private int id;
            private String name;
            private String location;
            private String occupation;
            private String introduction;
            private String photo4;
            private String photo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getPhoto4() {
                return photo4;
            }

            public void setPhoto4(String photo4) {
                this.photo4 = photo4;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }

        public static class ReviewsBean {
            /**
             * reviewID : 2830
             * userName : 柳吱吱
             * userPhoto : http://media.banmi.com/photos/1451977372348_ecafb592064dc3a57adb4f0d9de6a03c
             * content : 冲着心脏音博物馆去的，很早之前就看过介绍，真得值得
             * createdAt : 2月前
             * images : []
             */

            private int reviewID;
            private String userName;
            private String userPhoto;
            private String content;
            private String createdAt;
            private List<?> images;

            public int getReviewID() {
                return reviewID;
            }

            public void setReviewID(int reviewID) {
                this.reviewID = reviewID;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }
        }
    }
}
