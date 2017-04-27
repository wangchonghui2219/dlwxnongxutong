package com.example.administrator.dlwxnongxutong.bean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 支付方式
 */
public class PayTypeBean {

    /**
     * code : 200
     * info : [{"payid":"1","payname":"支付宝","pimg":"http://192.168.3.186/nmt/Uploads/pay/alipay.png"},{"payid":"2","payname":"微信支付","pimg":"http://192.168.3.186/nmt/Uploads/pay/wechat.png"},{"payid":"3","payname":"银联支付","pimg":"http://192.168.3.186/nmt/Uploads/pay/unionpay.png"}]
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
         * payid : 1
         * payname : 支付宝
         * pimg : http://192.168.3.186/nmt/Uploads/pay/alipay.png
         */

        private int payid;
        private String payname;
        private String pimg;

        public int getPayid() {
            return payid;
        }

        public void setPayid(int payid) {
            this.payid = payid;
        }

        public String getPayname() {
            return payname;
        }

        public void setPayname(String payname) {
            this.payname = payname;
        }

        public String getPimg() {
            return pimg;
        }

        public void setPimg(String pimg) {
            this.pimg = pimg;
        }
    }
}
