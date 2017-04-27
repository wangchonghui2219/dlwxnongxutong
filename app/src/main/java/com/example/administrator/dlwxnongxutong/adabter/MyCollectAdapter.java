package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.MyApplition;
import com.example.administrator.dlwxnongxutong.activitys.ProductDetailActivity;
import com.example.administrator.dlwxnongxutong.bean.MyCollectBean;
import com.example.administrator.dlwxnongxutong.bean.NewProductBean;
import com.example.administrator.dlwxnongxutong.bean.ScBean;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.id.list;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 上午 8:53
 * @name 我的收藏Adapter
 */
public class MyCollectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context ctx;
    private List<NewProductBean.InfoBean> infoBeen;


    public MyCollectAdapter(Context ctx, List<NewProductBean.InfoBean> infoBeen) {
        this.ctx = ctx;
        this.infoBeen = infoBeen;
    }

    @Override
    public int getItemCount() {
        return infoBeen.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final NewProductBean.InfoBean infoBean = infoBeen.get(position);
        Glide.with(ctx).load(infoBean.getGpo()).into(((MyViewHolder)holder).iv_pic);
        ((MyViewHolder)holder).tv_name.setText(infoBean.getGname());
        ((MyViewHolder)holder).tv_price.setText("￥"+infoBean.getGp());
        if (infoBean.getSc() == null) {

        }else{
            ((MyViewHolder)holder).tv_shuoming.setText( infoBean.getSc().get(0).getGsdesc());
        }


        ((MyViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> pics = new ArrayList<String>();
                for (int i = 0; i<infoBean.getGc().size();i++){
                    pics.add(infoBean.getGc().get(i).getImg());
                }
                Intent intent = new Intent(ctx,ProductDetailActivity.class);
                intent.putStringArrayListExtra("pics",pics);
                intent.putExtra("gname",infoBean.getGname());
                intent.putExtra("gp",infoBean.getGp());
                intent.putExtra("intl",infoBean.getIntl());
                intent.putExtra("sales",infoBean.getSales());
                intent.putExtra("godsid",infoBean.getGid());
                intent.putExtra("godshtml",infoBean.getGodshtml());
                intent.putExtra("gpo",infoBean.getGpo());
                intent.putExtra("bid",infoBean.getBid());
                intent.putExtra("gcname",infoBean.getGcname());
                List<ScBean> sc = infoBean.getSc();
                MyApplition applition = new MyApplition();
                applition.setSc(sc);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(ctx, R.layout.item_collect, null);

        return new MyViewHolder(view);
    }

     public class MyViewHolder extends RecyclerView.ViewHolder {
         public View rootView;
         public ImageView iv_pic;
         public TextView tv_name;
         public TextView tv_shuoming;
         public TextView tv_price;
        public MyViewHolder(View itemView) {
            super(itemView);
            if (onItemcleckListener != null) {


//                onItemcleckListener.onItemcleck(itemView, infoBeen.get(getLayoutPosition()));
            }
            this.rootView = itemView;
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_shuoming = (TextView) rootView.findViewById(R.id.tv_shuoming);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
        }
    }

    private OnItemCleckListener onItemcleckListener;

    public  interface OnItemCleckListener {
        public abstract void onItemcleck(View itemView, NewProductBean.InfoBean infoBean);
    }

    public void setOnItemCleckListener(OnItemCleckListener onItemceckListener) {
        this.onItemcleckListener = onItemceckListener;
    }




}
