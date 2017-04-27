package com.example.administrator.dlwxnongxutong.activity;/**
 * Created by scy on 2017/2/17.
 */

import android.app.Application;
import android.provider.Contacts;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.dlwxnongxutong.bean.JiFenListBean;
import com.example.administrator.dlwxnongxutong.bean.SanJiLianDBean;
import com.example.administrator.dlwxnongxutong.bean.ScBean;
import com.example.administrator.dlwxnongxutong.bean.YuEFanLiBean;
import com.example.administrator.dlwxnongxutong.bean.affrm.LisBean;
import com.example.administrator.dlwxnongxutong.bean.affrm.ListBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.InfoBean;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.R.id.list;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name $
 */
public class MyApplition extends Application {

    public static SanJiLianDBean sanJiLianDBean;

    @Override
    public void onCreate() {
        super.onCreate();
        initJsonData();
    }
    public static List<ScBean> sc;
    public static  List<InfoBean> info;
    public static JSONObject jsonObject;

    /**
     *
     * @param sc
     */
    public  void setSc (List<ScBean> sc){
       this.sc = sc;

    }

    /**
     * 保存余额跳转到详情界面的数据
     */
    public static YuEFanLiBean.InfoBean infos;
    public  void setYuEDate(YuEFanLiBean.InfoBean info){
            this.infos = info;
    }
    /**
     * 保存积分跳转到详情界面的数据
     */
    public static JiFenListBean.InfoBean infoBean;
    public void setJiFenDate(JiFenListBean.InfoBean infoBean){
        this.infoBean = infoBean;
    }


    public void setList( List<InfoBean> info){
        this.info = info;
    }
    /**
     * 从assert文件夹中获取json数据
     */
    private void initJsonData() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = getAssets().open("city.json");//打开json数据
            byte[] by = new byte[is.available()];//转字节
            int len = -1;
            while ((len = is.read(by)) != -1) {
                sb.append(new String(by, 0, len, "gb2312"));//根据字节长度设置编码
            }
            is.close();//关闭流
            //为json赋值
            jsonObject = new JSONObject(sb.toString());
            Gson gson = new Gson();
            sanJiLianDBean = gson.fromJson(jsonObject.toString(), SanJiLianDBean.class);

            Log.i("wch",jsonObject+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
