package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.dlwxnongxutong.R;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 选择配送方式
 */
public class Dis extends BaseFastAdapter {
    private Context ctx;
    public Dis(Context ctx) {
        super();
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(ctx,R.layout.item_popu_address_item,null);

        return view;
    }
}
