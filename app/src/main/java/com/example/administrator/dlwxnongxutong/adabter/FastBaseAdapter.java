package com.example.administrator.dlwxnongxutong.adabter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 *
 *
 * @作者 wch
 *
 * @create at 2017/1/10 0010 上午 8:28
 *
 * @name 父类adapter
 */

public abstract class FastBaseAdapter extends BaseAdapter {

    public FastBaseAdapter() {
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
