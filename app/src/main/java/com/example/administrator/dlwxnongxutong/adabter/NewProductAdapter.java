package com.example.administrator.dlwxnongxutong.adabter;

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
import com.example.administrator.dlwxnongxutong.bean.NewProductBean;
import com.example.administrator.dlwxnongxutong.bean.ScBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13/013.
 */

public class NewProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context ctx;
    private List<NewProductBean.InfoBean> products;

    public NewProductAdapter(Context ctx,List<NewProductBean.InfoBean> products) {
        super();
        this.products = products;
        this.ctx = ctx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(ctx, R.layout.home_product_item,null);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder) holder).tv_product_jifen.setText((products.get(position).getGp()*(products.get(position).getIntl()))+"积分");
        ((MyViewHolder) holder).tv_product_name.setText(products.get(position).getGname());
        ((MyViewHolder) holder).tv_product_price.setText("￥"+products.get(position).getGp());

        Glide.with(ctx).load(products.get(position).getGpo()).into(((MyViewHolder) holder).iv_product_icon);
        ((MyViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> pics = new ArrayList<String>();
                for (int i = 0; i<products.get(position).getGc().size();i++){
                    pics.add(products.get(position).getGc().get(i).getImg());
                }
                Intent intent = new Intent(ctx, ProductDetailActivity.class);
                intent.putStringArrayListExtra("pics",pics);
                intent.putExtra("gname",products.get(position).getGname());
                intent.putExtra("gp",products.get(position).getGp());
                intent.putExtra("intl",products.get(position).getIntl());
                intent.putExtra("sales",products.get(position).getSales());
                intent.putExtra("godsid",products.get(position).getGid());
                intent.putExtra("godshtml",products.get(position).getGodshtml());
                intent.putExtra("gpo",products.get(position).getGpo());
                intent.putExtra("bid",products.get(position).getBid());
                intent.putExtra("seller",products.get(position).getSeller());
                intent.putExtra("gcname",products.get(position).getGcname());
                intent.putExtra("intlpay",products.get(position).getIntlpay());//最多可以使用多少积分
                intent.putExtra("isintpay",products.get(position).getIsintpay());//是否可以使用积分
                intent.putExtra("mobile",products.get(position).getMobile());//客服电话
                List<ScBean> sc = products.get(position).getSc();
                MyApplition applition = new MyApplition();
                applition.setSc(sc);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
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
