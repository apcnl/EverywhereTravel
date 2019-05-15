package com.example.apcnl.travel.bean;

import java.util.List;

/**
 * Created by apcnl on 2019/5/12.
 */

public class MeFollowBean {


    /**
     * code : 0
     * desc :
     * result : {"page":1,"limit":20,"count":5,"banmi":[{"id":57,"name":"吴晓波","location":"杭州","occupation":"著名财经作家 青年领袖","introduction":"大家好，我是吴晓波。作为一个写字的人，深知\"读万卷书\"的重要，不过读书读到我这个年龄，有时候会生出无书可读的感叹，这时我常会选择\"行万里路\"，在旅程中寻找书本中无法获得的感受。\n\n无论目的和方式如何多变，旅行最终将落脚在体验二字，这是我和伴米旅行的共识，也是我们力求达到的效果。就算和千万人去了同一个地方，你也能通过我们的分享获得独有的体验，而这些体验会积累成为收获和知识，让旅行从一种短暂的休闲方式变成一种持久且让人成长的爱好。","following":5631,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1520393823697_c68268595efc905fc397781b6cde03c7.jpg","isFollowed":true},{"id":28,"name":"李炜","location":"北京","occupation":"蜻蜓FM名嘴，知名文化记者","introduction":"你好，我是李炜，知名文化记者。闲来无事最喜欢与两三好友对坐闲侃，聊聊历史八卦、文化趣事。我向来讲求有枣没枣儿，先打三杆子，搂草打兔子，咱们讲哪算哪儿。我不是专家，不谈之乎者也洋洋洒洒。或沉土中千载，或睹世间沧桑穿朝越代，它们背后的故事远比宫斗精彩。浩渺的历史文化中，总有有趣的故事让人折服与感悟，拿出一点时间，我们一起去看世界之大。觉得有趣，图您一乐呵，万一不妥，请您多包涵。","following":4646,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1513046399561_9d3b02cad0eddee4347c93f43b3e5a7a.jpg","isFollowed":true},{"id":25,"name":"王自如","location":"深圳","occupation":"中国电子产品测评第一人","introduction":"Hello你好，我是王自如。借着在日本转机的机会，我来到了东京\u2014\u2014这个全球非常重要的科技之都。我个人呢也没来过日本，所以此次顺道前来朝拜一下。两天的时间，我几乎逛遍了东京所有与高新科技相关的景点，的确大开眼界，这次也希望通过伴米旅行，将这段行程分享给大家。","following":1480,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511748843296_eb8d5337fa7223727eb35fae8b29416e.jpg","isFollowed":true},{"id":1,"name":"榎本伸也","location":"东京","occupation":"东京著名花艺师","introduction":"大家好，我是老夏，东京人，一个讲汉语带点北京味儿的花艺师。研习花道不仅修心养性，更让我对生活中的美有着独到的见解与嗅觉。旅途之中，我同样善于用一名花艺师的特有的目光，去发现并勾勒那些细微处的美好。","following":3296,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511748145275_1313268db35f9fd931c523ddacd9292b.jpg","isFollowed":true},{"id":9,"name":"Isa","location":"长野县轻井泽","occupation":"互联网公司CEO 世界旅行家","introduction":"大家好，我叫Isa，是一家互联网创业公司创始人兼CEO。千万不要一听我头衔，就以为我是个公务缠身的工作狂。工作之余，去世界各地旅行也是我的挚爱，至今，我已经去过56个国家。我是个彻头彻尾的行动派，风景没有捷径，即刻出发也不该有任何借口。跟上我的脚步，我会带你重新认识这个世界。","following":3470,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511750046703_ae0388aa6532e8f3d9765c1ca4a5971d.png","isFollowed":true}]}
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
         * count : 5
         * banmi : [{"id":57,"name":"吴晓波","location":"杭州","occupation":"著名财经作家 青年领袖","introduction":"大家好，我是吴晓波。作为一个写字的人，深知\"读万卷书\"的重要，不过读书读到我这个年龄，有时候会生出无书可读的感叹，这时我常会选择\"行万里路\"，在旅程中寻找书本中无法获得的感受。\n\n无论目的和方式如何多变，旅行最终将落脚在体验二字，这是我和伴米旅行的共识，也是我们力求达到的效果。就算和千万人去了同一个地方，你也能通过我们的分享获得独有的体验，而这些体验会积累成为收获和知识，让旅行从一种短暂的休闲方式变成一种持久且让人成长的爱好。","following":5631,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1520393823697_c68268595efc905fc397781b6cde03c7.jpg","isFollowed":true},{"id":28,"name":"李炜","location":"北京","occupation":"蜻蜓FM名嘴，知名文化记者","introduction":"你好，我是李炜，知名文化记者。闲来无事最喜欢与两三好友对坐闲侃，聊聊历史八卦、文化趣事。我向来讲求有枣没枣儿，先打三杆子，搂草打兔子，咱们讲哪算哪儿。我不是专家，不谈之乎者也洋洋洒洒。或沉土中千载，或睹世间沧桑穿朝越代，它们背后的故事远比宫斗精彩。浩渺的历史文化中，总有有趣的故事让人折服与感悟，拿出一点时间，我们一起去看世界之大。觉得有趣，图您一乐呵，万一不妥，请您多包涵。","following":4646,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1513046399561_9d3b02cad0eddee4347c93f43b3e5a7a.jpg","isFollowed":true},{"id":25,"name":"王自如","location":"深圳","occupation":"中国电子产品测评第一人","introduction":"Hello你好，我是王自如。借着在日本转机的机会，我来到了东京\u2014\u2014这个全球非常重要的科技之都。我个人呢也没来过日本，所以此次顺道前来朝拜一下。两天的时间，我几乎逛遍了东京所有与高新科技相关的景点，的确大开眼界，这次也希望通过伴米旅行，将这段行程分享给大家。","following":1480,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511748843296_eb8d5337fa7223727eb35fae8b29416e.jpg","isFollowed":true},{"id":1,"name":"榎本伸也","location":"东京","occupation":"东京著名花艺师","introduction":"大家好，我是老夏，东京人，一个讲汉语带点北京味儿的花艺师。研习花道不仅修心养性，更让我对生活中的美有着独到的见解与嗅觉。旅途之中，我同样善于用一名花艺师的特有的目光，去发现并勾勒那些细微处的美好。","following":3296,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511748145275_1313268db35f9fd931c523ddacd9292b.jpg","isFollowed":true},{"id":9,"name":"Isa","location":"长野县轻井泽","occupation":"互联网公司CEO 世界旅行家","introduction":"大家好，我叫Isa，是一家互联网创业公司创始人兼CEO。千万不要一听我头衔，就以为我是个公务缠身的工作狂。工作之余，去世界各地旅行也是我的挚爱，至今，我已经去过56个国家。我是个彻头彻尾的行动派，风景没有捷径，即刻出发也不该有任何借口。跟上我的脚步，我会带你重新认识这个世界。","following":3470,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511750046703_ae0388aa6532e8f3d9765c1ca4a5971d.png","isFollowed":true}]
         */

        private int page;
        private int limit;
        private int count;
        private List<BanmiBean> banmi;

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

        public List<BanmiBean> getBanmi() {
            return banmi;
        }

        public void setBanmi(List<BanmiBean> banmi) {
            this.banmi = banmi;
        }

        public static class BanmiBean {
            /**
             * id : 57
             * name : 吴晓波
             * location : 杭州
             * occupation : 著名财经作家 青年领袖
             * introduction : 大家好，我是吴晓波。作为一个写字的人，深知"读万卷书"的重要，不过读书读到我这个年龄，有时候会生出无书可读的感叹，这时我常会选择"行万里路"，在旅程中寻找书本中无法获得的感受。

             无论目的和方式如何多变，旅行最终将落脚在体验二字，这是我和伴米旅行的共识，也是我们力求达到的效果。就算和千万人去了同一个地方，你也能通过我们的分享获得独有的体验，而这些体验会积累成为收获和知识，让旅行从一种短暂的休闲方式变成一种持久且让人成长的爱好。
             * following : 5631
             * photo : http://cdn.banmi.com/banmiapp/rahdna/1520393823697_c68268595efc905fc397781b6cde03c7.jpg
             * isFollowed : true
             */

            private int id;
            private String name;
            private String location;
            private String occupation;
            private String introduction;
            private int following;
            private String photo;
            private boolean isFollowed;

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

            public int getFollowing() {
                return following;
            }

            public void setFollowing(int following) {
                this.following = following;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public boolean isIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(boolean isFollowed) {
                this.isFollowed = isFollowed;
            }
        }
    }
}
