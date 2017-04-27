package com.example.administrator.dlwxnongxutong.adabter;/**
 * Created by scy on 2017/2/13.
 */

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name BaseFastAdapter$
 */
public abstract class BaseFastAdapter extends BaseAdapter {
    public BaseFastAdapter() {
        super();
    }

    @Override
    public abstract int getCount();

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);



}

