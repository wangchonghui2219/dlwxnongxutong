package com.example.administrator.dlwxnongxutong.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/4/14/014.
 */

public class WXOrderBean {

    /**
     * code : 200
     * info : {"appid":"wxa84af97e00ffba6b","noncestr":"dJJuruIB6RGUbAJq6AGweI9qEkRP5mft","package":"Sign=WXPay","partnerid":"1411184102","prepayid":"wx201704141514017fe84d387c0226477365","sign":"2929A9786ACB9B36EC3084B536FA4AA2","timestamp":1492154041}
     * message : 下单成功
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
         * appid : wxa84af97e00ffba6b
         * noncestr : dJJuruIB6RGUbAJq6AGweI9qEkRP5mft
         * package : Sign=WXPay
         * partnerid : 1411184102
         * prepayid : wx201704141514017fe84d387c0226477365
         * sign : 2929A9786ACB9B36EC3084B536FA4AA2
         * timestamp : 1492154041
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private String sign;
        private int timestamp;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }
    }
}
