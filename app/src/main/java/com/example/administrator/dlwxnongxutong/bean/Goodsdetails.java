package com.example.administrator.dlwxnongxutong.bean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 上屏详情
 */
public class Goodsdetails {


    /**
     * code : 200
     * info : [{"bid":"1","gc":[{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e6f31e5b2d.JPG"},{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e6f2b44b78.JPG"},{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e6f31e5b2d.JPG"}],"gcid":"4","gcname":"粉加粒","gid":"2","gname":"新乡机械","godshtml":"http://192.168.3.186/nmt/Goods/Godsdesc?gid=2","gp":"12.00","gpo":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-02-10/589d73435f281.jpg","intl":"10","intlpay":"0","isintpay":"1","pid":"2","sales":"0","sc":[{"gprice":"121.00","gsdesc":"紫色G356","gsid":"35","gstock":"20","jfnum":"200"},{"gprice":"120.00","gsdesc":"黑色G356","gsid":"36","gstock":"20","jfnum":"200"},{"gprice":"123.00","gsdesc":"粉色G356","gsid":"37","gstock":"20","jfnum":"200"},{"gprice":"123.00","gsdesc":"灰色G356","gsid":"38","gstock":"20","jfnum":"200"}],"seller":"邦威"}]
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
         * bid : 1
         * gc : [{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e6f31e5b2d.JPG"},{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e6f2b44b78.JPG"},{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e6f31e5b2d.JPG"}]
         * gcid : 4
         * gcname : 粉加粒
         * gid : 2
         * gname : 新乡机械
         * godshtml : http://192.168.3.186/nmt/Goods/Godsdesc?gid=2
         * gp : 12.00
         * gpo : http://192.168.3.186/nmt/Uploads/goods/thumb/2017-02-10/589d73435f281.jpg
         * intl : 10
         * intlpay : 0
         * isintpay : 1
         * pid : 2
         * sales : 0
         * sc : [{"gprice":"121.00","gsdesc":"紫色G356","gsid":"35","gstock":"20","jfnum":"200"},{"gprice":"120.00","gsdesc":"黑色G356","gsid":"36","gstock":"20","jfnum":"200"},{"gprice":"123.00","gsdesc":"粉色G356","gsid":"37","gstock":"20","jfnum":"200"},{"gprice":"123.00","gsdesc":"灰色G356","gsid":"38","gstock":"20","jfnum":"200"}]
         * seller : 邦威
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
             * img : http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-11/589e6f31e5b2d.JPG
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
             * gprice : 121.00
             * gsdesc : 紫色G356
             * gsid : 35
             * gstock : 20
             * jfnum : 200
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
