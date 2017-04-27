package com.example.administrator.dlwxnongxutong.bean;/**
 * Created by scy on 2017/2/14.
 */

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 首页轮播图$
 */
public class HomeLunBoBean {

    /**
     * code : 200
     * info : [{"cimg":"http://192.168.3.186/nmt/Uploads/home/nm1.jpg"},{"cimg":"http://192.168.3.186/nmt/Uploads/home/nm2.jpg"},{"cimg":"http://192.168.3.186/nmt/Uploads/home/nm3.jpg"}]
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
         * cimg : http://192.168.3.186/nmt/Uploads/home/nm1.jpg
         */

        private String img;

        public String getCimg() {
            return img;
        }

        public void setCimg(String cimg) {
            this.img = cimg;
        }
    }
}
