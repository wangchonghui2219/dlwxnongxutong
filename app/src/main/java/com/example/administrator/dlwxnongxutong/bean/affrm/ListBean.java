package com.example.administrator.dlwxnongxutong.bean.affrm;

import java.util.List;



public  class ListBean {
    /**
     * bid : ss
     * gcname : nn
     * lis : [{"gname":"name","gp":22,"gpo":"","gid":"3"}]
     */

    private String bid;
    private String gcname;
    private List<LisBean> lis;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getGcname() {
        return gcname;
    }

    public void setGcname(String gcname) {
        this.gcname = gcname;
    }

    public List<LisBean> getLis() {
        return lis;
    }

    public void setLis(List<LisBean> lis) {
        this.lis = lis;
    }


}
