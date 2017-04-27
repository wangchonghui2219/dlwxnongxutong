package com.example.administrator.dlwxnongxutong.bean;


import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 配送方式
 */
public class DisTypeBean {


    /**
     * code : 200
     * info : [{"describe":"上门自取","odyid":"1","price":"0.00","state":"0"},{"describe":"送货上门","odyid":"2","price":"900.00","state":"1"},{"describe":"物流发货","odyid":"3","price":"400.00","state":"0"}]
     * message : 获取成功
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
         * describe : 上门自取
         * odyid : 1
         * price : 0.00
         * state : 0
         */

        private String describe;
        private String odyid;
        private double price;
        private String state;

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getOdyid() {
            return odyid;
        }

        public void setOdyid(String odyid) {
            this.odyid = odyid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
