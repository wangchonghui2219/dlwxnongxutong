package com.example.administrator.dlwxnongxutong.bean.shoppcarbean;

import com.example.administrator.dlwxnongxutong.bean.ScBean;

import java.util.List;

/**
 * Created by scy on 2017/2/20.
 */

public class CartBean extends ShoppCatListBean{
    /**
     * bid : 1
     * gc : [{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-13/58a11919a9e0e.jpg"},{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-13/58a1191ce7ef8.jpg"},{"img":"http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-13/58a11922dc8b4.jpg"}]
     * gcid : 1
     * gcname : 家畜饲料
     * gctid : 5
     * gid : 7
     * gname : 中华大书房
     * gnum : 1
     * godshtml : http://192.168.3.186/nmt/Goods/Godsdesc?gid=7
     * gp : 199.00
     * gpo : http://192.168.3.186/nmt/Uploads/goods/thumb/2017-02-13/58a118f8439ae.jpg
     * intl : 0
     * intlpay : 0
     * isintpay : 1
     * pid : 1
     * sales : 0
     * sc : [{"gprice":"200.00","gsdesc":"紫色","gsid":"17","gstock":"200"},{"gprice":"250.00","gsdesc":"红色","gsid":"18","gstock":"100"}]
     */
    private String bid;
    private String gcid;
    private String gcname;
    private String gctid;
    private String gid;
    private String gname;
    private int gnum;
    private String godshtml;
    private double gp;
    private String gpo;
    private String intl;
    private double intlpay;
    private int isintpay;
    private String pid;
    private String sales;
    private List<GcBean> gc;
    private List<ScBean> sc;
    private String gsdesc;
    private String seller;

    public String getGsdesc() {
        return gsdesc;
    }

    public void setGsdesc(String gsdesc) {
        this.gsdesc = gsdesc;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

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

    public String getGctid() {
        return gctid;
    }

    public void setGctid(String gctid) {
        this.gctid = gctid;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }

    public String getGodshtml() {
        return godshtml;
    }

    public void setGodshtml(String godshtml) {
        this.godshtml = godshtml;
    }

    public double getGp() {
        return gp;
    }

    public void setGp(double gp) {
        this.gp = gp;
    }

    public String getGpo() {
        return gpo;
    }

    public void setGpo(String gpo) {
        this.gpo = gpo;
    }

    public String getIntl() {
        return intl;
    }

    public void setIntl(String intl) {
        this.intl = intl;
    }

    public double getIntlpay() {
        return intlpay;
    }

    public void setIntlpay(double intlpay) {
        this.intlpay = intlpay;
    }

    public int getIsintpay() {
        return isintpay;
    }

    public void setIsintpay(int isintpay) {
        this.isintpay = isintpay;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public List<GcBean> getGc() {
        return gc;
    }

    public void setGc(List<GcBean> gc) {
        this.gc = gc;
    }

    public List<ScBean> getSc() {
        return sc;
    }

    public void setSc(List<ScBean> sc) {
        this.sc = sc;
    }

    public static class GcBean {
        /**
         * img : http://192.168.3.186/nmt/Uploads/goods/carousel/2017-02-13/58a11919a9e0e.jpg
         */

        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
