package com.example.apcnl.travel.bean;

import java.io.Serializable;

/**
 * Created by apcnl on 2017/11/2.
 */

public class LoginInfo implements Serializable {


    /**
     * code : 0
     * desc :
     * result : {"uid":332174,
     * "token":"8uOCGH1VZ6ElwTuTovawwYpSsjA4AsszgF26v1bl8ETGeO5IDymbIoP1zezW42q9HkbrR2VBloqP4GcPpB4LrhH38KMJsTBmS5CwIPpuSec4ys9eyP4QLKcYJHpy50fTzIPg",
     * "description":"",
     * "userName":"wechat user",
     * "gender":"U",M:男,F:女,U:妖
     * "phone":"",
     * "email":"",
     * "photo":"",
     * "accounts":{"weibo":false,"wechat":true}}
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
         * uid : 332174
         * token : 8uOCGH1VZ6ElwTuTovawwYpSsjA4AsszgF26v1bl8ETGeO5IDymbIoP1zezW42q9HkbrR2VBloqP4GcPpB4LrhH38KMJsTBmS5CwIPpuSec4ys9eyP4QLKcYJHpy50fTzIPg
         * description :
         * userName : wechat user
         * gender : U
         * phone :
         * email :
         * photo :
         * accounts : {"weibo":false,"wechat":true}
         */

        private int uid;
        private String token;
        private String description;
        private String userName;
        private String gender;
        private String phone;
        private String email;
        private String photo;
        private AccountsBean accounts;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public AccountsBean getAccounts() {
            return accounts;
        }

        public void setAccounts(AccountsBean accounts) {
            this.accounts = accounts;
        }

        public static class AccountsBean {
            /**
             * weibo : false
             * wechat : true
             */

            private boolean weibo;
            private boolean wechat;

            public boolean isWeibo() {
                return weibo;
            }

            public void setWeibo(boolean weibo) {
                this.weibo = weibo;
            }

            public boolean isWechat() {
                return wechat;
            }

            public void setWechat(boolean wechat) {
                this.wechat = wechat;
            }
        }
    }
}
