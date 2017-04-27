package com.example.administrator.dlwxnongxutong.adabter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activitys.ProductDetailActivity;
import com.example.administrator.dlwxnongxutong.bean.NewProductBean;
import com.example.administrator.dlwxnongxutong.bean.ShangJiaBean;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @nam店铺
 */

public class DianpuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context ctx;
    private List<ShangJiaBean.InfoBean.CimgBean> cimg;
    private List<String> pics= new ArrayList<>();
    private String bname;
    private List<NewProductBean.InfoBean> products;
    private Activity proactivity;
    private String seller;
    public DianpuAdapter(Context ctx, List<NewProductBean.InfoBean> products, Activity proactivity,List<ShangJiaBean.InfoBean.CimgBean> cimg,String bname,String seller) {
        this.ctx = ctx;
        this.products = products;
        this.proactivity = proactivity;
        this.cimg = cimg;
        this.bname = bname;
        this.seller = seller;
        for (int i = 0; i <cimg.size() ; i++) {
            pics.add(cimg.get(i).getImg());
        }

    }
    public static enum ITEM_TYPE{
        ITEM_TYPE_ADD_HEAD,
        ITEM_TYPE_REMOVE_TYPE
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_ADD_HEAD.ordinal()) {
                View view  = View.inflate(ctx,R.layout.dianpu_head_item,null);
            return new AddHEadADapter(view);
        }

        View view = View.inflate(parent.getContext(),R.layout.item_dianpu,null);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {

        return products.size();
    }
    /**
     * banner图的图片加载器
     */
    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(ctx).load(path).into(imageView);
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof AddHEadADapter){//头布局信息
            ((AddHEadADapter)holder).banner.setImageLoader(new GlideImageLoader());
            ((AddHEadADapter)holder).banner.setImages(pics);

            ((AddHEadADapter)holder).banner.setBannerAnimation(Transformer.Default);
            ((AddHEadADapter)holder).banner.start();
            ((AddHEadADapter)holder).tv_name.setText(seller);

        }else{


        ((MyViewHolder) holder).tv_product_jifen.setText(products.get(position).getIntl()+"积分");
        ((MyViewHolder) holder).tv_product_name.setText(products.get(position).getGname()+"");
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
                proactivity.finish();
                ctx.startActivity(intent);

            }
        });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0? ITEM_TYPE.ITEM_TYPE_ADD_HEAD.ordinal():ITEM_TYPE.ITEM_TYPE_REMOVE_TYPE.ordinal();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {


                    return getItemViewType(position) == ITEM_TYPE.ITEM_TYPE_ADD_HEAD.ordinal()
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }

    }
    private class AddHEadADapter extends RecyclerView.ViewHolder{
        private LinearLayout ll_head;
        private Banner banner;
        private TextView tv_name;
        public AddHEadADapter(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            ll_head = (LinearLayout) itemView.findViewById(R.id.ll_head);
        }
    }
    private class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_product_icon;
        private TextView tv_product_name;
        private TextView tv_product_price;
        private TextView tv_product_jifen;
        private CardView cardView;

        public MyViewHolder(View itemview) {
            super(itemview);
            tv_product_price = (TextView) itemView.findViewById(R.id.tv_product_price);
            cardView = (CardView) itemView;

            iv_product_icon = (ImageView) itemView.findViewById(R.id.iv_product_icon);
            tv_product_name = (TextView) itemView.findViewById(R.id.tv_product_name);
            tv_product_jifen = (TextView) itemView.findViewById(R.id.tv_product_jifen);
        }

    }


}
