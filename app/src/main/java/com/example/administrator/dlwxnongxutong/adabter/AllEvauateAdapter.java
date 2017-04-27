package com.example.administrator.dlwxnongxutong.adabter;/**
 * Created by scy on 2017/2/16.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.AllevauateBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name   商品评价$
 */
public class AllEvauateAdapter extends BaseFastAdapter {
    private Context ctx;
    private  List<AllevauateBean.InfoBean.ListBean> list;
    public AllEvauateAdapter(Context ctx, List<AllevauateBean.InfoBean.ListBean> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        MyViewHolder vh;
        if (convertView == null) {
            vh = new MyViewHolder();
            view = View.inflate(ctx, R.layout.item_allevauate,null);
            vh.tv_number = (TextView) view.findViewById(R.id.tv_number);
            vh.tv_time = (TextView) view.findViewById(R.id.tv_time);
            vh.tv_content = (TextView) view.findViewById(R.id.tv_content);
            view.setTag(vh);
        }else{
            view = convertView;
            vh = (MyViewHolder) view.getTag();
        }
        AllevauateBean.InfoBean.ListBean listBean = list.get(position);
        vh.tv_number.setText(listBean.getPhe());

        vh.tv_time.setText(listBean.getRatetime());
        vh.tv_content.setText(listBean.getRateinfo());
        return view;
    }
    private class MyViewHolder {
        TextView tv_number,tv_time,tv_content;
    }
}
