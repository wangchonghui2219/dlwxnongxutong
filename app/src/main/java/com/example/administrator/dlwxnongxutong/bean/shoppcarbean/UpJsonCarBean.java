package com.example.administrator.dlwxnongxutong.bean.shoppcarbean;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 修改购物车信息上传的数据
 */
public class UpJsonCarBean {

    private String bid;
    private String msg;//买家留言
    private String way;//配送方式
    private int num;

    public String getId() {
        return bid;
    }

    public void setId(String id) {
        this.bid = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
