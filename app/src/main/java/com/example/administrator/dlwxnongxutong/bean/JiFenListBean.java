package com.example.administrator.dlwxnongxutong.bean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 积分列表
 */
public class JiFenListBean {

    /**
     * code : 200
     * info : [{"gname":"大桥鸡精","orderno":"201703131717524669433520","orderprice":"2400.00","otime":"2017-03","reintl":"500"},{"gname":"大桥鸡精","orderno":"201703131717525913635226","orderprice":"2400.00","otime":"2017-03","reintl":"500"},{"gname":"大桥鸡精","orderno":"201703131717527733520528","orderprice":"2400.00","otime":"2017-03","reintl":"500"},{"gname":"大桥鸡精","orderno":"201703131717527351745622","orderprice":"2400.00","otime":"2017-03","reintl":"500"},{"gname":"大桥鸡精","orderno":"201703131717525084716722","orderprice":"2400.00","otime":"2017-03","reintl":"500"},{"gname":"大桥鸡精","orderno":"201703131717526592590321","orderprice":"2400.00","otime":"2017-03","reintl":"500"},{"gname":"大桥鸡精","orderno":"201703131717517129272427","orderprice":"2400.00","otime":"2017-03","reintl":"500"},{"gname":"大桥鸡精","orderno":"201703131717512792419423","orderprice":"2400.00","otime":"2017-03","reintl":"500"},{"gname":"大桥鸡精","orderno":"201703131717511773437524","orderprice":"2400.00","otime":"2017-03","reintl":"500"},{"gname":"大桥鸡精","orderno":"201703131717518607482917","orderprice":"2400.00","otime":"2017-03","reintl":"500"}]
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
         * gname : 大桥鸡精
         * orderno : 201703131717524669433520
         * orderprice : 2400.00
         * otime : 2017-03
         * reintl : 500
         */

        private String gname;
        private String orderno;
        private String orderprice;
        private String otime;
        private String reintl;

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public String getOrderprice() {
            return orderprice;
        }

        public void setOrderprice(String orderprice) {
            this.orderprice = orderprice;
        }

        public String getOtime() {
            return otime;
        }

        public void setOtime(String otime) {
            this.otime = otime;
        }

        public String getReintl() {
            return reintl;
        }

        public void setReintl(String reintl) {
            this.reintl = reintl;
        }
    }
}
