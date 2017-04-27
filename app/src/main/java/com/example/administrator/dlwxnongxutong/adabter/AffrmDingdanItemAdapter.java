package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.affrm.LisBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.CartBean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name
 */
public class AffrmDingdanItemAdapter extends BaseFastAdapter {
    private Context ctx;
    private List<CartBean> cart;
    public AffrmDingdanItemAdapter(Context ctx,List<CartBean> cart) {
        super();
        this.ctx = ctx;
        this.cart = cart;
    }
    @Override
    public int getCount() {
        return cart.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            view = View.inflate(ctx, R.layout.item_affrm_dingdan_item,null);
            vh.iv_pic = (ImageView) view.findViewById(R.id.iv_pic);
            vh.tv_guigename = (TextView) view.findViewById(R.id.tv_guigename);
            vh.tv_name = (TextView) view.findViewById(R.id.tv_name);
            vh.tv_num = (TextView) view.findViewById(R.id.tv_num);
            vh.tv_price = (TextView) view.findViewById(R.id.tv_price);
            view.setTag(vh);
        }else{
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
        CartBean cartBean = cart.get(position);
        vh.tv_price.setText("￥"+cartBean.getGp()+"");
//        vh.tv_guigename.setText("");
        vh.tv_name.setText(cartBean.getGname());
        Glide.with(ctx).load(cartBean.getGpo()).into(vh.iv_pic);
        vh.tv_num.setText("X"+cartBean.getGnum());
        vh.tv_guigename.setText(cartBean.getGsdesc());
        return view;
    }

    private class ViewHolder{
        ImageView iv_pic;
        TextView tv_name,tv_guigename,tv_price,tv_num;
    }

    /**
     * 显示配送方式
     */
    private void showDisTypePopuWindow() {
        View view = View.inflate(ctx,R.layout.item_popu_distype,null);

        PopupWindow window = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        window.getAnimationStyle();
        //个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);
        ListView lv_list = (ListView) view.findViewById(R.id.lv_list);
        Dis adapter = new Dis(ctx);
        lv_list.setAdapter(adapter);
//        window.showAtLocation(Activity.findViewById(R.id.main), Gravity.BOTTOM|Gravity.CENTER_VERTICAL,0,0);

    }
}
