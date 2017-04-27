package com.example.administrator.dlwxnongxutong.bean;

/**
 * Created by scy on 2017/2/20.
 */

public class ScBean {
    /**
     * gsdesc : 大红凹面
     * gsid : 15
     * gstock : 100
     */

    private String gsdesc;
    private String gsid;
    private String gstock;
    private double gprice;
    private double jfnum;

    public double getJfnum() {
        return jfnum;
    }

    public void setJfnum(double jfnum) {
        this.jfnum = jfnum;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(double gprice) {
        this.gprice = gprice;
    }

    public String getGsdesc() {
        return gsdesc;
    }

    public void setGsdesc(String gsdesc) {
        this.gsdesc = gsdesc;
    }

    public String getGsid() {
        return gsid;
    }

    public void setGsid(String gsid) {
        this.gsid = gsid;
    }

    public String getGstock() {
        return gstock;
    }

    public void setGstock(String gstock) {
        this.gstock = gstock;
    }
}
