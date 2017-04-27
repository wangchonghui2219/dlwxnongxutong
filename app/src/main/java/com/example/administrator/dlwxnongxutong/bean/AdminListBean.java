package com.example.administrator.dlwxnongxutong.bean;

import java.util.List;
/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 管理员信息列表
 */
public class AdminListBean {

    /**
     * code : 200
     * message : 管理员信息获取成功
     * info : [{"name":"冯路远","adminphe":"13126808448","adminid":"19"},{"name":"湖南","adminphe":"13126808448","adminid":"20"},{"name":"冯绍峰","adminphe":"13126808448","adminid":"21"}]
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
         * name : 冯路远
         * adminphe : 13126808448
         * adminid : 19
         */

        private String name;
        private String adminphe;
        private String adminid;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdminphe() {
            return adminphe;
        }

        public void setAdminphe(String adminphe) {
            this.adminphe = adminphe;
        }

        public String getAdminid() {
            return adminid;
        }

        public void setAdminid(String adminid) {
            this.adminid = adminid;
        }
    }
}
