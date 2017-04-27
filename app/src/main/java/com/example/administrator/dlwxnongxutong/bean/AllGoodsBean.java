package com.example.administrator.dlwxnongxutong.bean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 全部商品$
 */
public class AllGoodsBean {


    /**
     * code : 200
     * info : [{"bid":"4","gc":[{"img":"http://www.nongmut.com/Uploads/carousel/2017-04-13/58ef186489cb0.jpg"},{"img":"http://www.nongmut.com/Uploads/carousel/2017-04-13/58ef1862b44cb.jpg"}],"gcid":"5","gcname":"保育料","gid":"4","gname":"智能折叠车17款","godshtml":"http://www.nongmut.com/admin.php?c=Index&a=Godsdesc&gid=4","gp":"6000.00","gpo":"http://www.nongmut.com/Uploads/goods/thumb/2017-04-13/58ef185e9411f.jpg","intl":"0.9","intlpay":"0.8","isintpay":"1","mobile":"13220138448","pid":"1","sales":"6","sc":[{"gprice":"8888.00","gsdesc":"珍珠粉","gsid":"12","gstock":"15","jfnum":"7999"}],"seller":"科技最前沿"},{"bid":"4","gc":[{"img":"http://www.nongmut.com/Uploads/carousel/2017-04-13/58eed4d72247b.jpg"},{"img":"http://www.nongmut.com/Uploads/carousel/2017-04-13/58eed4d20f718.jpg"}],"gcid":"12","gcname":"中大猪","gid":"3","gname":"折叠自行车16款","godshtml":"http://www.nongmut.com/admin.php?c=Index&a=Godsdesc&gid=3","gp":"30000.00","gpo":"http://www.nongmut.com/Uploads/goods/thumb/2017-04-13/58eed4cd61856.jpg","intl":"0.5","intlpay":"0.5","isintpay":"1","mobile":"13220138448","pid":"1","sales":"4","sc":[{"gprice":"35000.00","gsdesc":"沧桑金","gsid":"8","gstock":"496","jfnum":"17500"}],"seller":"科技最前沿"},{"bid":"1","gc":[{"img":"http://www.nongmut.com/Uploads/carousel/2017-04-11/58ec521ee21f3.jpg"},{"img":"http://www.nongmut.com/Uploads/carousel/2017-04-11/58ec522385ee5.jpg"},{"img":"http://www.nongmut.com/Uploads/carousel/2017-04-11/58ec5227163fe.jpg"}],"gcid":"1","gcname":"家畜饲料","gid":"2","gname":"Sada折叠自行车10款","godshtml":"http://www.nongmut.com/admin.php?c=Index&a=Godsdesc&gid=1","gp":"4000.00","gpo":"http://www.nongmut.com/Uploads/goods/thumb/2017-04-11/58ec521a67945.jpg","intl":"0.5","intlpay":"0.5","isintpay":"1","mobile":"13126808448","pid":"1","sales":"4","sc":[{"gprice":"4888.00","gsdesc":"土豪金","gsid":"3","gstock":"5","jfnum":"2444"},{"gprice":"4686.00","gsdesc":"静谧蓝","gsid":"4","gstock":"15","jfnum":"2343"},{"gprice":"4888.00","gsdesc":"土豪金","gsid":"9","gstock":"4","jfnum":"0"},{"gprice":"4686.00","gsdesc":"静谧蓝","gsid":"13","gstock":"12","jfnum":"2343"},{"gprice":"4699.00","gsdesc":"静谧蓝","gsid":"14","gstock":"15","jfnum":"2349"}],"seller":"潮流街"},{"bid":"1","gc":[{"img":"http://www.nongmut.com/Uploads/carousel/2017-04-13/58eed4d72247b.jpg"},{"img":"http://www.nongmut.com/Uploads/carousel/2017-04-13/58eed4d20f718.jpg"}],"gcid":"1","gcname":"家畜饲料","gid":"1","gname":"Sada折叠自行车08款","godshtml":"http://www.nongmut.com/admin.php?c=Index&a=Godsdesc&gid=1","gp":"4000.00","gpo":"http://www.nongmut.com/Uploads/goods/thumb/2017-04-11/58ec521a67945.jpg","intl":"0.2","intlpay":"0.4","isintpay":"1","mobile":"13126808448","pid":"1","sales":"1","sc":[{"gprice":"4888.00","gsdesc":"土豪金","gsid":"1","gstock":"4","jfnum":"978"},{"gprice":"4686.00","gsdesc":"静谧蓝","gsid":"2","gstock":"15","jfnum":"937"},{"gprice":"4888.00","gsdesc":"土豪金","gsid":"5","gstock":"5","jfnum":"0"},{"gprice":"4686.00","gsdesc":"静谧蓝","gsid":"15","gstock":"15","jfnum":"937"},{"gprice":"6666.00","gsdesc":"土豪金","gsid":"16","gstock":"5","jfnum":"1333"}],"seller":"潮流街"}]
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
         * bid : 4
         * gc : [{"img":"http://www.nongmut.com/Uploads/carousel/2017-04-13/58ef186489cb0.jpg"},{"img":"http://www.nongmut.com/Uploads/carousel/2017-04-13/58ef1862b44cb.jpg"}]
         * gcid : 5
         * gcname : 保育料
         * gid : 4
         * gname : 智能折叠车17款
         * godshtml : http://www.nongmut.com/admin.php?c=Index&a=Godsdesc&gid=4
         * gp : 6000.00
         * gpo : http://www.nongmut.com/Uploads/goods/thumb/2017-04-13/58ef185e9411f.jpg
         * intl : 0.9
         * intlpay : 0.8
         * isintpay : 1
         * mobile : 13220138448
         * pid : 1
         * sales : 6
         * sc : [{"gprice":"8888.00","gsdesc":"珍珠粉","gsid":"12","gstock":"15","jfnum":"7999"}]
         * seller : 科技最前沿
         */

        private String bid;
        private String gcid;
        private String gcname;
        private String gid;
        private String gname;
        private String godshtml;
        private String gp;
        private String gpo;
        private String intl;
        private String intlpay;
        private String isintpay;
        private String mobile;
        private String pid;
        private String sales;
        private String seller;
        private List<GcBean> gc;
        private List<ScBean> sc;

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getGcid() {
            return gcid;
        }

        public void setGcid(String gcid) {
            this.gcid = gcid;
        }

        public String getGcname() {
            return gcname;
        }

        public void setGcname(String gcname) {
            this.gcname = gcname;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getGodshtml() {
            return godshtml;
        }

        public void setGodshtml(String godshtml) {
            this.godshtml = godshtml;
        }

        public String getGp() {
            return gp;
        }

        public void setGp(String gp) {
            this.gp = gp;
        }

        public String getGpo() {
            return gpo;
        }

        public void setGpo(String gpo) {
            this.gpo = gpo;
        }

        public String getIntl() {
            return intl;
        }

        public void setIntl(String intl) {
            this.intl = intl;
        }

        public String getIntlpay() {
            return intlpay;
        }

        public void setIntlpay(String intlpay) {
            this.intlpay = intlpay;
        }

        public String getIsintpay() {
            return isintpay;
        }

        public void setIsintpay(String isintpay) {
            this.isintpay = isintpay;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getSeller() {
            return seller;
        }

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public List<GcBean> getGc() {
            return gc;
        }

        public void setGc(List<GcBean> gc) {
            this.gc = gc;
        }

        public List<ScBean> getSc() {
            return sc;
        }

        public void setSc(List<ScBean> sc) {
            this.sc = sc;
        }

        public static class GcBean {
            /**
             * img : http://www.nongmut.com/Uploads/carousel/2017-04-13/58ef186489cb0.jpg
             */

            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }

        public static class ScBean {
            /**
             * gprice : 8888.00
             * gsdesc : 珍珠粉
             * gsid : 12
             * gstock : 15
             * jfnum : 7999
             */

            private String gprice;
            private String gsdesc;
            private String gsid;
            private String gstock;
            private String jfnum;

            public String getGprice() {
                return gprice;
            }

            public void setGprice(String gprice) {
                this.gprice = gprice;
            }

            public String getGsdesc() {
                return gsdesc;
            }

            public void setGsdesc(String gsdesc) {
                this.gsdesc = gsdesc;
            }

            public String getGsid() {
                return gsid;
            }

            public void setGsid(String gsid) {
                this.gsid = gsid;
            }

            public String getGstock() {
                return gstock;
            }

            public void setGstock(String gstock) {
                this.gstock = gstock;
            }

            public String getJfnum() {
                return jfnum;
            }

            public void setJfnum(String jfnum) {
                this.jfnum = jfnum;
            }
        }
    }
}
