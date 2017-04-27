package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.PayTypeBean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 支付方式
 */
public class PayTypeAdapter extends BaseFastAdapter {
    private Context ctx;
    private List<PayTypeBean.InfoBean> info;
    public PayTypeAdapter(Context ctx,List<PayTypeBean.InfoBean> info) {
        super();
        this.info = info;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            view = View.inflate(ctx, R.layout.item_pay_type,null);
            vh.icon_zhifuyl = (ImageView) view.findViewById(R.id.icon_zhifuyl);
            vh.tv_payname = (TextView) view.findViewById(R.id.tv_payname);
            vh.cb_gouxuany = (CheckBox) view.findViewById(R.id.cb_gouxuany);
            view.setTag(vh);
        }else{
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
        PayTypeBean.InfoBean infoBean = info.get(position);
        Glide.with(ctx).load(infoBean.getPimg()).into(vh.icon_zhifuyl);
        vh.tv_payname.setText(infoBean.getPayname());
        if (position == 0) {
            vh.cb_gouxuany.setChecked(true);
        }else{
            vh.cb_gouxuany.setChecked(false);
        }
        if (position == flag) {
            vh.cb_gouxuany.setChecked(true);
        }else{
            vh.cb_gouxuany.setChecked(false);
        }
        return view;
    }
    private int flag;
    /**
     * 设置checkBox选中状态
     * @param position
     */
    public void setIsCheced(int position) {
        this.flag = position;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        ImageView icon_zhifuyl;
        TextView tv_payname;
        CheckBox cb_gouxuany;
    }
}
