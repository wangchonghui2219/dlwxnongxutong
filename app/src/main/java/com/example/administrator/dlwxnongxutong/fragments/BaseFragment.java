package com.example.administrator.dlwxnongxutong.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 *
 * @作者 wch
 *
 * @create at 2017/1/7 0007 下午 1:39
 *
 * @name 父类Fragment
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener,Serializable{
    public Context ctx;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ctx = getActivity();
        View v = inflater.inflate(getLayoutResID(),null);
        initView(v);
        initData();
        initListener();
        return v;

    }
    /**
     * 获取Activity显示的布局：
     *
     * @return：布局id
     */
    public abstract int getLayoutResID();

    /**
     * 初始化View：findViewById 参数:view,通过View查找控件
     */
    public abstract void initView(View view);

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化监听：点击监听、设置适配器、设置条目点击监听
     */
    public abstract void initListener();

    @Override
    public void onClick(View v) {

    }
    public void wch(Object objects){
        Log.i("wch",objects+"");
    }
}
