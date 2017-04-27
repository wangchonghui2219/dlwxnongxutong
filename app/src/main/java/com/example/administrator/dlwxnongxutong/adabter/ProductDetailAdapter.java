package com.example.administrator.dlwxnongxutong.adabter;/**
 * Created by scy on 2017/2/13.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activitys.ProductDetailActivity;
import com.example.administrator.dlwxnongxutong.bean.NewProductBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name ProductDetailAdapter$
 */
public class ProductDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context ctx;
    private List<NewProductBean.InfoBean> products;
    private Activity proactivity;
    public ProductDetailAdapter(Context ctx, List<NewProductBean.InfoBean> products, Activity proactivity) {
        this.ctx = ctx;
        this.products = products;
        this.proactivity = proactivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.home_product_item,null);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder) holder).tv_product_jifen.setText(products.get(position).getIntl()+"积分");
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
                intent.putExtra("godsid",products.get(position).getGid());
                intent.putExtra("gname",products.get(position).getGname());
                intent.putExtra("gp",products.get(position).getGp());
                intent.putExtra("intl",products.get(position).getIntl());
                intent.putExtra("sales",products.get(position).getSales());
                    finishActivity.setFinish();
                ctx.startActivity(intent);

            }
        });

    }
    private class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_product_icon;
        private TextView tv_product_name;
        private TextView tv_product_price;
        private TextView tv_product_jifen;
        private LinearLayout cardView;
        public MyViewHolder(View itemview) {
            super(itemview);
            tv_product_price = (TextView) itemView.findViewById(R.id.tv_product_price);
            cardView = (LinearLayout) itemView;
            iv_product_icon = (ImageView) itemView.findViewById(R.id.iv_product_icon);
            tv_product_name = (TextView) itemView.findViewById(R.id.tv_product_name);
            tv_product_jifen = (TextView) itemView.findViewById(R.id.tv_product_jifen);
        }

    }
    private FinishActivity finishActivity;
    public interface FinishActivity{
        public abstract void setFinish();
    }
    public void setFinishActivity(FinishActivity finishActivity){
        this.finishActivity = finishActivity;
    }
}
