package com.example.administrator.dlwxnongxutong.bean;/**
 * Created by scy on 2017/2/16.
 */

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 全部评价$
 */
public class AllevauateBean {

    /**
     * code : 200
     * info : {"list":[{"phe":"186****1978","rateinfo":"很不错,你值得拥有","ratelevel":"3","ratetime":"2017-02-15"},{"phe":"186****1978","rateinfo":"很不错,你值得拥有","ratelevel":"3","ratetime":"2017-02-15"},{"phe":"186****1978","rateinfo":"养的狗狗，自从吃了你们家的宠物粮，天天睡","ratelevel":"3","ratetime":"2017-02-15"}],"num":"6"}
     * message : 获取成功
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
         * list : [{"phe":"186****1978","rateinfo":"很不错,你值得拥有","ratelevel":"3","ratetime":"2017-02-15"},{"phe":"186****1978","rateinfo":"很不错,你值得拥有","ratelevel":"3","ratetime":"2017-02-15"},{"phe":"186****1978","rateinfo":"养的狗狗，自从吃了你们家的宠物粮，天天睡","ratelevel":"3","ratetime":"2017-02-15"}]
         * num : 6
         */

        private String num;
        private List<ListBean> list;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * phe : 186****1978
             * rateinfo : 很不错,你值得拥有
             * ratelevel : 3
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
}
