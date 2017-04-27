package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.dlwxnongxutong.R;

import java.util.List;

/**
 * Created by scy on 2017/3/22.
 */

public class MyOrderItemAdapter extends BaseFastAdapter {
    private Context ctx;
    private List<MyOrderBean.InfoBean.OrderBean> order;

    public MyOrderItemAdapter(Context ctx, List<MyOrderBean.InfoBean.OrderBean> order) {
        super();
        this.ctx = ctx;
        this.order = order;
    }

    @Override
    public int getCount() {
        return order.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder vh;
        if (convertView == null) {

            view = View.inflate(ctx, R.layout.item_mydingdan_item, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
        MyOrderBean.InfoBean.OrderBean orderBean = order.get(position);
        Glide.with(ctx).load(orderBean.getGphoto()).into(vh.iv_pic);
        vh.tv_goodname.setText(orderBean.getGname());
        vh.tv_price.setText(orderBean.getGprices());
        vh.tv_num.setText("x"+orderBean.getGnum());
        return view;
    }

    private  class ViewHolder {
        public View rootView;
        public ImageView iv_pic;
        public TextView tv_goodname;
        public TextView tv_price;
        public TextView tv_num;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            this.tv_goodname = (TextView) rootView.findViewById(R.id.tv_goodname);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
            this.tv_num = (TextView) rootView.findViewById(R.id.tv_num);
        }

    }
}
