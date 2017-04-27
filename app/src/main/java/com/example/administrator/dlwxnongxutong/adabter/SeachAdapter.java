package com.example.administrator.dlwxnongxutong.adabter;/**
 * Created by scy on 2017/2/16.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.MyApplition;
import com.example.administrator.dlwxnongxutong.activitys.ProductDetailActivity;
import com.example.administrator.dlwxnongxutong.bean.AllGoodsBean;
import com.example.administrator.dlwxnongxutong.bean.NewAddressBean;
import com.example.administrator.dlwxnongxutong.bean.NewProductBean;
import com.example.administrator.dlwxnongxutong.bean.ScBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 搜索页的全部商品$
 */
public class SeachAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context ctx;
    private List<NewProductBean.InfoBean> info;
    public SeachAdapter(Context ctx,List<NewProductBean.InfoBean> info) {
        this.ctx = ctx;
        this.info = info;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder) holder).tv_product_jifen.setText(info.get(position).getIntl()+"积分");
        ((MyViewHolder) holder).tv_product_name.setText(info.get(position).getGname());
        ((MyViewHolder) holder).tv_product_price.setText("￥"+info.get(position).getGp());

        Glide.with(ctx).load(info.get(position).getGpo()).into(((MyViewHolder) holder).iv_product_icon);
        ((MyViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> pics = new ArrayList<String>();
                for (int i = 0; i<info.get(position).getGc().size();i++){
                    pics.add(info.get(position).getGc().get(i).getImg());
                }
                Intent intent = new Intent(ctx, ProductDetailActivity.class);
                intent.putStringArrayListExtra("pics",pics);
                intent.putExtra("gname",info.get(position).getGname());
                intent.putExtra("gp",info.get(position).getGp());
                intent.putExtra("intl",info.get(position).getIntl());
                intent.putExtra("sales",info.get(position).getSales());
                intent.putExtra("godsid",info.get(position).getGid());
                intent.putExtra("bid",info.get(position).getBid());


                intent.putExtra("godshtml",info.get(position).getGodshtml());
                intent.putExtra("gpo",info.get(position).getGpo());
                intent.putExtra("seller",info.get(position).getSeller());
                intent.putExtra("gcname",info.get(position).getGcname());
                intent.putExtra("intlpay",info.get(position).getIntlpay());//最多可以使用多少积分
                intent.putExtra("isintpay",info.get(position).getIsintpay());//是否可以使用积分
                intent.putExtra("mobile",info.get(position).getMobile());//客服电话
                List<ScBean> sc = info.get(position).getSc();
                MyApplition applition = new MyApplition();
                applition.setSc(sc);
                ctx.startActivity(intent);
            }
        });


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.home_product_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_product_icon;
        private TextView tv_product_name;
        private TextView tv_product_price;
        private TextView tv_product_jifen;
        private LinearLayout cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_product_price = (TextView) itemView.findViewById(R.id.tv_product_price);
            cardView = (LinearLayout) itemView;

            iv_product_icon = (ImageView) itemView.findViewById(R.id.iv_product_icon);
            tv_product_name = (TextView) itemView.findViewById(R.id.tv_product_name);
            tv_product_jifen = (TextView) itemView.findViewById(R.id.tv_product_jifen);
        }
    }
}
