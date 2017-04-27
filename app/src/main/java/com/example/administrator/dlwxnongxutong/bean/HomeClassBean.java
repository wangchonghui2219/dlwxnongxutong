package com.example.administrator.dlwxnongxutong.bean;/**
 * Created by scy on 2017/2/14.
 */

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 首页分类$
 */
public class HomeClassBean {


    /**
     * code : 200
     * info : [{"gcid":"1","gcname":"家畜饲料","gimg":"http://192.168.3.186/nmt/Uploads/classify/icon_jxsl.png"},{"gcid":"16","gcname":"家禽饲料","gimg":"http://192.168.3.186/nmt/Uploads/classify/icon_jqsl.png"},{"gcid":"25","gcname":"兽药产品","gimg":"http://192.168.3.186/nmt/Uploads/classify/icon_sycp.png"},{"gcid":"26","gcname":"宠物粮","gimg":"http://192.168.3.186/nmt/Uploads/classify/icon_cwl.png"},{"gcid":"27","gcname":"动物疫苗","gimg":"http://192.168.3.186/nmt/Uploads/classify/icon_dwym.png"}]
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
         * gcid : 1
         * gcname : 家畜饲料
         * gimg : http://192.168.3.186/nmt/Uploads/classify/icon_jxsl.png
         */

        private String gcid;
        private String gcname;
        private String gimg;

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

        public String getGimg() {
            return gimg;
        }

        public void setGimg(String gimg) {
            this.gimg = gimg;
        }
    }
}
