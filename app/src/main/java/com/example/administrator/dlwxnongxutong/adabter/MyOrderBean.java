package com.example.administrator.dlwxnongxutong.adabter;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 我的订单
 */
public class MyOrderBean {

    /**
     * code : 200
     * info : [{"number":60,"order":[{"bid":"1","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"},{"bid":"1","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"},{"bid":"1","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"},{"bid":"1","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"},{"bid":"1","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"},{"bid":"1","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"}],"seller":"邦威","sellerid":"1","total":14400},{"number":40,"order":[{"bid":"20","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"},{"bid":"20","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"},{"bid":"20","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"},{"bid":"20","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"}],"seller":"123123","sellerid":"20","total":9600}]
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
         * number : 60
         * order : [{"bid":"1","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"},{"bid":"1","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"},{"bid":"1","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"},{"bid":"1","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"},{"bid":"1","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"},{"bid":"1","gname":"大桥鸡精","gnum":"10","godsid":"7","gphoto":"http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg","gprices":"200.00","orderprice":"2400.00"}]
         * seller : 邦威
         * sellerid : 1
         * total : 14400
         */

        private int number;
        private String seller;
        private String sellerid;
        private int total;
        private String goid;

        public String getGoid() {
            return goid;
        }

        public void setGoid(String goid) {
            this.goid = goid;
        }

        private List<OrderBean> order;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getSeller() {
            return seller;
        }

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public String getSellerid() {
            return sellerid;
        }

        public void setSellerid(String sellerid) {
            this.sellerid = sellerid;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<OrderBean> getOrder() {
            return order;
        }

        public void setOrder(List<OrderBean> order) {
            this.order = order;
        }

        public static class OrderBean {
            /**
             * bid : 1
             * gname : 大桥鸡精
             * gnum : 10
             * godsid : 7
             * gphoto : http://192.168.3.186/nmt/Uploads/goods/thumb/2017-03-08/58bf6e485f7fb.jpg
             * gprices : 200.00
             * orderprice : 2400.00
             */

            private String bid;
            private String gname;
            private String gnum;
            private String godsid;
            private String gphoto;
            private String gprices;
            private String orderprice;

            public String getBid() {
                return bid;
            }

            public void setBid(String bid) {
                this.bid = bid;
            }

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            public String getGnum() {
                return gnum;
            }

            public void setGnum(String gnum) {
                this.gnum = gnum;
            }

            public String getGodsid() {
                return godsid;
            }

            public void setGodsid(String godsid) {
                this.godsid = godsid;
            }

            public String getGphoto() {
                return gphoto;
            }

            public void setGphoto(String gphoto) {
                this.gphoto = gphoto;
            }

            public String getGprices() {
                return gprices;
            }

            public void setGprices(String gprices) {
                this.gprices = gprices;
            }

            public String getOrderprice() {
                return orderprice;
            }

            public void setOrderprice(String orderprice) {
                this.orderprice = orderprice;
            }
        }
    }
}
