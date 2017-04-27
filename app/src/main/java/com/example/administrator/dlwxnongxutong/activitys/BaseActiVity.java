package com.example.administrator.dlwxnongxutong.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * 
 *
 * @作者 wch
 *
 * @create at 2017/1/7 0007 下午 1:38
 * 
 * @name 父类Activity
 */

public abstract class BaseActiVity extends AppCompatActivity implements View.OnClickListener{
    public Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        ctx = this;
        initView();
        initData();
        initListener();

    }


    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化监听
     */
    public abstract void initListener();

    @Override
    public void onClick(View v) {

    }
    public void toast(Object mes){
        Toast.makeText(ctx,mes+"",Toast.LENGTH_SHORT).show();

    }
    public void mlog(String msg){
        Log.i("scy",msg);
    }
    public void wch(String mes){
        Log.i("wch",mes);
    }
}
