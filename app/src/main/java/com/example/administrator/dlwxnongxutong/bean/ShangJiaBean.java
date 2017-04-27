package com.example.administrator.dlwxnongxutong.bean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 商家
 */

public class ShangJiaBean {

    /**
     * code : 200
     * info : {"bid":"1","bname":"测试之家","cimg":[{"img":"http://192.168.3.186/nmt/Uploads/home/nm1.jpg"},{"img":"http://192.168.3.186/nmt/Uploads/home/nm2.jpg"},{"img":"http://192.168.3.186/nmt/Uploads/home/nm3.jpg"}],"phone":"13126808448","sale":"0"}
     * message : 商家不存在
     */

    private int code;
    private InfoBean info;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode( int code) {
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
         * bid : 1
         * bname : 测试之家
         * cimg : [{"img":"http://192.168.3.186/nmt/Uploads/home/nm1.jpg"},{"img":"http://192.168.3.186/nmt/Uploads/home/nm2.jpg"},{"img":"http://192.168.3.186/nmt/Uploads/home/nm3.jpg"}]
         * phone : 13126808448
         * sale : 0
         */

        private String bid;
        private String bname;
        private String phone;
        private String sale;
        private List<CimgBean> cimg;

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getBname() {
            return bname;
        }

        public void setBname(String bname) {
            this.bname = bname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSale() {
            return sale;
        }

        public void setSale(String sale) {
            this.sale = sale;
        }

        public List<CimgBean> getCimg() {
            return cimg;
        }

        public void setCimg(List<CimgBean> cimg) {
            this.cimg = cimg;
        }

        public static class CimgBean {
            /**
             * img : http://192.168.3.186/nmt/Uploads/home/nm1.jpg
             */

            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
