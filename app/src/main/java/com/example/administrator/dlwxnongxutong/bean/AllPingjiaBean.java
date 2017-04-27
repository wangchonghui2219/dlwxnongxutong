package com.example.administrator.dlwxnongxutong.bean;/**
 * Created by scy on 2017/2/17.
 */

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name $
 */
public class AllPingjiaBean {


    /**
     * code : 200
     * info : [{"phe":"186****1978","rateinfo":"养的狗狗，自从吃了你们家的宠物粮，大小便 失禁","ratelevel":"4","ratetime":"2017-02-15"},{"phe":"186****1978","rateinfo":"不错，不错，疫苗不错，鸡场的鸡 第二天都没起来","ratelevel":"4","ratetime":"2017-02-15"},{"phe":"186****1978","rateinfo":"不错，不错，疫苗不错，鸡场的鸡 第二天都没起来","ratelevel":"4","ratetime":"2017-02-15"},{"phe":"186****1978","rateinfo":"不错，不错，疫苗不错，鸡场的鸡 第二天都没起来","ratelevel":"4","ratetime":"2017-02-15"}]
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
         * phe : 186****1978
         * rateinfo : 养的狗狗，自从吃了你们家的宠物粮，大小便 失禁
         * ratelevel : 4
         * ratetime : 2017-02-15
         */

        private String phe;
        private String rateinfo;
        private String ratelevel;
        private String ratetime;

        public String getPhe() {
            return phe;
        }

        public void setPhe(String phe) {
            this.phe = phe;
        }

        public String getRateinfo() {
            return rateinfo;
        }

        public void setRateinfo(String rateinfo) {
            this.rateinfo = rateinfo;
        }

        public String getRatelevel() {
            return ratelevel;
        }

        public void setRatelevel(String ratelevel) {
            this.ratelevel = ratelevel;
        }

        public String getRatetime() {
            return ratetime;
        }

        public void setRatetime(String ratetime) {
            this.ratetime = ratetime;
        }
    }
}
