package com.example.administrator.dlwxnongxutong.bean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name
 */

public class BankListBean {


    /**
     * code : 200
     * info : [{"bank_address":"花园路建设银行","bank_card":"6217000002507670","bank_id":"1","bank_name":"中国建设银行","card_holder":"冯晓磊","create_time":"2017-03-18 09:12:17","identity_card":"411322199502084930","update_time":"2017-03-18 09:12:22","user_id":"1"},{"bank_address":"4","bank_card":"1234567890123458","bank_id":"2","bank_name":"4","card_holder":"凤凤","create_time":"2017-03-18 09:19:53","identity_card":"411322199502084930","update_time":"2017-03-18 14:22:40","user_id":"1"},{"bank_address":"4","bank_card":"1234567890123456","bank_id":"3","bank_name":"4","card_holder":"2","create_time":"2017-03-18 09:24:36","identity_card":"411424199312079223","update_time":"2017-03-18 09:24:36","user_id":"1"},{"bank_address":"4","bank_card":"1234567890123455","bank_id":"5","bank_name":"4","card_holder":"2","create_time":"2017-03-18 10:14:01","identity_card":"411424199312079223","update_time":"2017-03-18 10:14:01","user_id":"1"},{"bank_address":"黑龙江伊春翠峦区","bank_card":"6221234567891234567","bank_id":"7","bank_name":"dsad","card_holder":"qwedas","create_time":"2017-03-20 08:58:48","identity_card":"41142219910514363X","update_time":"2017-03-20 08:58:48","user_id":"1"}]
     * message : 银行卡检索成功
     */

    private int code;
    private String message;
    private List<InfoBean> info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * bank_address : 花园路建设银行
         * bank_card : 6217000002507670
         * bank_id : 1
         * bank_name : 中国建设银行
         * card_holder : 冯晓磊
         * create_time : 2017-03-18 09:12:17
         * identity_card : 411322199502084930
         * update_time : 2017-03-18 09:12:22
         * user_id : 1
         */

        private String bank_address;
        private String bank_card;
        private String bank_id;
        private String bank_name;
        private String card_holder;
        private String create_time;
        private String identity_card;
        private String update_time;
        private String user_id;

        public String getBank_address() {
            return bank_address;
        }

        public void setBank_address(String bank_address) {
            this.bank_address = bank_address;
        }

        public String getBank_card() {
            return bank_card;
        }

        public void setBank_card(String bank_card) {
            this.bank_card = bank_card;
        }

        public String getBank_id() {
            return bank_id;
        }

        public void setBank_id(String bank_id) {
            this.bank_id = bank_id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getCard_holder() {
            return card_holder;
        }

        public void setCard_holder(String card_holder) {
            this.card_holder = card_holder;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getIdentity_card() {
            return identity_card;
        }

        public void setIdentity_card(String identity_card) {
            this.identity_card = identity_card;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
