package com.example.apcnl.travel.bean;

/**
 * Created by apcnl on 2019/5/7.
 */

public class MeInfoBean {


    /**
     * code : 0
     * desc :
     * result : {"uid":7835,"description":"lll","balance":"99719.30","userName":"伴大米","photo":"http://cdn.banmi.com/banmiapp/user/logo/1557221974950.jpg","gender":"F","email":"ran@banmi.com","phone":"15818549324"}
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
         * uid : 7835
         * description : lll
         * balance : 99719.30
         * userName : 伴大米
         * photo : http://cdn.banmi.com/banmiapp/user/logo/1557221974950.jpg
         * gender : F
         * email : ran@banmi.com
         * phone : 15818549324
         */

        private int uid;
        private String description;
        private String balance;
        private String userName;
        private String photo;
        private String gender;
        private String email;
        private String phone;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }


    }


}
