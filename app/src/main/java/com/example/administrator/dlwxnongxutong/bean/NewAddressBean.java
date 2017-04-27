package com.example.administrator.dlwxnongxutong.bean;/**
 * Created by scy on 2017/2/14.
 */

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name $
 */
public class NewAddressBean {

    /**
     * code : 200
     * info : {"address_id":1,"consignee_address":"郑州市金水区","consignee_name":"王崇辉","consignee_phe":"18637051978","flag":"0","remarks":"安华大厦"}
     * message : 新增收货地址成功
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
         * address_id : 1
         * consignee_address : 郑州市金水区
         * consignee_name : 王崇辉
         * consignee_phe : 18637051978
         * flag : 0
         * remarks : 安华大厦
         */

        private int address_id;
        private String consignee_address;
        private String consignee_name;
        private String consignee_phe;
        private String flag;
        private String remarks;

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
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
    }
}
