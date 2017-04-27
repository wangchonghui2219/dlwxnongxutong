package com.example.administrator.dlwxnongxutong.bean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 余额返利
 */
public class YuEFanLiBean {

    /**
     * code : 200
     * info : [{"account":"1000.00","fftime":"2017-03-19 17:22:23","flprice":"100.00","isdz":"2","orderno":"201703131101063786132836"},{"account":"1000.00","fftime":"2017-03-19 17:22:23","flprice":"100.00","isdz":"2","orderno":"201703131101063786132836"},{"account":"1000.00","fftime":"2017-03-19 17:22:23","flprice":"100.00","isdz":"2","orderno":"201703131101063786132836"}]
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
         * account : 1000.00
         * fftime : 2017-03-19 17:22:23
         * flprice : 100.00
         * isdz : 2
         * orderno : 201703131101063786132836
         */

        private String account;
        private String fftime;
        private String flprice;
        private int isdz;
        private String orderno;
        private String txtime;
        private String tx;
        private int isstate;

        public int getIsstate() {
            return isstate;
        }

        public void setIsstate(int isstate) {
            this.isstate = isstate;
        }

        public String getTxtime() {
            return txtime;
        }

        public void setTxtime(String txtime) {
            this.txtime = txtime;
        }

        public String getTx() {
            return tx;
        }

        public void setTx(String tx) {
            this.tx = tx;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getFftime() {
            return fftime;
        }

        public void setFftime(String fftime) {
            this.fftime = fftime;
        }

        public String getFlprice() {
            return flprice;
        }

        public void setFlprice(String flprice) {
            this.flprice = flprice;
        }

        public int getIsdz() {
            return isdz;
        }

        public void setIsdz(int isdz) {
            this.isdz = isdz;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }
    }
}
