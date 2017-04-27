package com.example.administrator.dlwxnongxutong.bean;/**
 * Created by scy on 2017/2/15.
 */

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 收货地址列表$
 */
public class AddressListBean {

    /**
     * code : 200
     * info : [{"address_id":"1","consignee_address":"郑州市金水区","consignee_name":"王崇辉","consignee_phe":"18637051978","flag":"0","remarks":"安华大厦","user_id":"1"}]
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
         * address_id : 1
         * consignee_address : 郑州市金水区
         * consignee_name : 王崇辉
         * consignee_phe : 18637051978
         * flag : 0
         * remarks : 安华大厦
         * user_id : 1
         */

        private String address_id;
        private String consignee_address;
        private String consignee_name;
        private String consignee_phe;
        private String flag;
        private String remarks;
        private String user_id;

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getConsignee_address() {
            return consignee_address;
        }

        public void setConsignee_address(String consignee_address) {
            this.consignee_address = consignee_address;
        }

        public String getConsignee_name() {
            return consignee_name;
        }

        public void setConsignee_name(String consignee_name) {
            this.consignee_name = consignee_name;
        }

        public String getConsignee_phe() {
            return consignee_phe;
        }

        public void setConsignee_phe(String consignee_phe) {
            this.consignee_phe = consignee_phe;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
