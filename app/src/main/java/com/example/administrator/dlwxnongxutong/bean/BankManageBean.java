package com.example.administrator.dlwxnongxutong.bean;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name
 */
public class BankManageBean {

    /**
     * code : 200
     * info : {"account":"915.00","adminid":"19","state":"0","user_id":"1","user_password":"aaffebecec560fec66e75f24062224ffa4e07696d2ae9a1fee3707c3f8fd9373","user_photo":"/Uploads/head/11490324641.jpg","username":"dlwxw","userphe":"18637051978"}
     * message : 管理员绑定成功
     */

    private int code;
    private InfoBean info;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class InfoBean {
        /**
         * account : 915.00
         * adminid : 19
         * state : 0
         * user_id : 1
         * user_password : aaffebecec560fec66e75f24062224ffa4e07696d2ae9a1fee3707c3f8fd9373
         * user_photo : /Uploads/head/11490324641.jpg
         * username : dlwxw
         * userphe : 18637051978
         */

        private String account;
        private String adminid;
        private String state;
        private String user_id;
        private String user_password;
        private String user_photo;
        private String username;
        private String userphe;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAdminid() {
            return adminid;
        }

        public void setAdminid(String adminid) {
            this.adminid = adminid;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }

        public String getUser_photo() {
            return user_photo;
        }

        public void setUser_photo(String user_photo) {
            this.user_photo = user_photo;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserphe() {
            return userphe;
        }

        public void setUserphe(String userphe) {
            this.userphe = userphe;
        }
    }
}
