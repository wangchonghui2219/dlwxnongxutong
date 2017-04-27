package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.administrator.dlwxnongxutong.R;
import com.bumptech.glide.Glide;
import com.example.administrator.dlwxnongxutong.activity.MyApplition;
import com.example.administrator.dlwxnongxutong.activity.SearchResultActivity;
import com.example.administrator.dlwxnongxutong.activitys.ProductDetailActivity;

import com.example.administrator.dlwxnongxutong.bean.HomeClassBean;
import com.example.administrator.dlwxnongxutong.bean.HomeLunBoBean;
import com.example.administrator.dlwxnongxutong.bean.NewProductBean;

import com.example.administrator.dlwxnongxutong.bean.ScBean;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.R.attr.data;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.administrator.dlwxnongxutong.R.id.iv_pic;
import static com.example.administrator.dlwxnongxutong.R.id.iv_pic1;


/**
 * Created by scy on 2017/1/11.
 */

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<NewProductBean.InfoBean> products;
    private List<HomeClassBean.InfoBean> homeclass;
    Context ctx;
    private List<String> images = new ArrayList<>();
    private int tag;
    private Intent intent;
    private String gcid;

    public static enum ITEM_TYPE {
        ITEM_TYPE_Theme,
        ITEM_TYPE_Video,
        TYPE_FOOTER
    }
    //数据集
    public List<Integer> mdatas;
    private TextView themeTitle;
    private ImageView iv_pic;
    List<HomeLunBoBean.InfoBean> infopics;
    public ProductAdapter(List<NewProductBean.InfoBean> products, Context ctx, int tag, List<HomeClassBean.InfoBean> homeclass,List<HomeLunBoBean.InfoBean> infopics) {
        this.products = products;
        this.ctx = ctx;
        this.tag = tag;
        this.homeclass = homeclass;
        this.infopics = infopics;
        if (infopics != null) {


        //设置图片集合
        for (int i = 0;i<infopics.size();i++){
            images.add(infopics.get(i).getCimg());
        }
        }
    }
    /**
     * banner图的图片加载器
     */
    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            Glide.with(context).load(path).into(imageView);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_Theme.ordinal()){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_message,parent,false);

            return new ThemeVideoHolder(view);

        }else if (viewType == ITEM_TYPE.TYPE_FOOTER.ordinal()){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_recycle_pross,parent,false);
            return  new FooterViewHolder(view);
        }

        else if(viewType == ITEM_TYPE.ITEM_TYPE_Video.ordinal()) {
            View view = View.inflate(parent.getContext(), R.layout.home_product_item, null);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ThemeVideoHolder){
            if (flag == false) {
//                themeTitle.setText("新品推荐");
                themeTitle.setVisibility(View.GONE);
                iv_pic.setVisibility(View.GONE);
            }else{
                themeTitle.setVisibility(View.VISIBLE);
                iv_pic.setVisibility(View.VISIBLE);
                themeTitle.setText("热品推荐");
            }
            if (position == 0) {
                ((ThemeVideoHolder) holder).view.setVisibility(View.VISIBLE);
                //设置图片加载器
                ((ThemeVideoHolder) holder).banner.setImageLoader(new GlideImageLoader());

                ((ThemeVideoHolder) holder).banner.setImages(images);
                //设置banner动画效果
                ((ThemeVideoHolder) holder).banner.setBannerAnimation(Transformer.DepthPage);
                //banner设置方法全部调用完毕时最后调用
                ((ThemeVideoHolder) holder).banner.start();
                                Glide.with(ctx).load(homeclass.get(0).getGimg()).into(((ThemeVideoHolder) holder).iv_pic1);
                                ((ThemeVideoHolder) holder).tv_text1.setText(homeclass.get(0).getGcname());

                                 Glide.with(ctx).load(homeclass.get(1).getGimg()).into(((ThemeVideoHolder) holder).iv_pic2);
                                 ((ThemeVideoHolder) holder).tv_text2.setText(homeclass.get(1).getGcname());


                                 Glide.with(ctx).load(homeclass.get(2).getGimg()).into(((ThemeVideoHolder) holder).iv_pic3);
                                 ((ThemeVideoHolder) holder).tv_text3.setText(homeclass.get(2).getGcname());


                                 Glide.with(ctx).load(homeclass.get(3).getGimg()).into(((ThemeVideoHolder) holder).iv_pic4);
                                 ((ThemeVideoHolder) holder).tv_text4.setText(homeclass.get(3).getGcname());


                                 Glide.with(ctx).load(homeclass.get(4).getGimg()).into(((ThemeVideoHolder) holder).iv_pic5);
                                 ((ThemeVideoHolder) holder).tv_text5.setText(homeclass.get(4).getGcname());

                ((ThemeVideoHolder) holder).ll_home_class1.setOnClickListener(this);
                ((ThemeVideoHolder) holder).ll_home_class2.setOnClickListener(this);
                ((ThemeVideoHolder) holder).ll_home_class3.setOnClickListener(this);
                ((ThemeVideoHolder) holder).ll_home_class4.setOnClickListener(this);
                ((ThemeVideoHolder) holder).ll_home_class5.setOnClickListener(this);

                
            }else{

                ((ThemeVideoHolder) holder).view.setVisibility(View.GONE);
            }
        }else if (holder instanceof FooterViewHolder){

        }
        else if (holder instanceof MyViewHolder) {
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
                    intent.putExtra("gname",products.get(position).getGname());
                    intent.putExtra("gp",products.get(position).getGp());
                    intent.putExtra("intl",products.get(position).getIntl());
                    intent.putExtra("sales",products.get(position).getSales());
                    intent.putExtra("godsid",products.get(position).getGid());
                    intent.putExtra("godshtml",products.get(position).getGodshtml());
                    intent.putExtra("gpo",products.get(position).getGpo());
                    intent.putExtra("bid",products.get(position).getBid());
                    intent.putExtra("gcname",products.get(position).getGcname());
                    intent.putExtra("intlpay",products.get(position).getIntlpay());//最多可以使用多少积分
                    intent.putExtra("isintpay",products.get(position).getIsintpay());//是否可以使用积分
                    List<ScBean> sc = products.get(position).getSc();
                    MyApplition applition = new MyApplition();
                    applition.setSc(sc);
                    ctx.startActivity(intent);
                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    private boolean flag = true;
    public int getItemViewType(int position){

                 if(position  == tag){
                     flag = true;
                     return ITEM_TYPE.ITEM_TYPE_Theme.ordinal();
                }

                else if (position == 0){
                     flag = false;
                     return ITEM_TYPE.ITEM_TYPE_Theme.ordinal();
                 }
            else if (position == products.size()-1){
            return ITEM_TYPE.TYPE_FOOTER.ordinal();
        }else{
            return  ITEM_TYPE.ITEM_TYPE_Video.ordinal();
        }


    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == ITEM_TYPE.ITEM_TYPE_Theme.ordinal()) {
                        return gridManager.getSpanCount();
                    }else
                    if (getItemViewType(position) == ITEM_TYPE.TYPE_FOOTER.ordinal()) {
                        return gridManager.getSpanCount();
                    }else{
                        return  1;
                    }

//                    return getItemViewType(position) == ITEM_TYPE.ITEM_TYPE_Theme.ordinal()
//                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }
    /**
     * 底部加载更多
     */
    class FooterViewHolder extends RecyclerView.ViewHolder {

        ProgressBar rcvLoadMore;

        public FooterViewHolder(View itemView) {
            super(itemView);
            rcvLoadMore = (ProgressBar) itemView.findViewById(R.id.rcv_load_more);
        }
    }
    public class ThemeVideoHolder extends RecyclerView.ViewHolder{
            private LinearLayout view,ll_home_class1,ll_home_class2,ll_home_class3,ll_home_class4,ll_home_class5;
            private ImageView iv_pic1,iv_pic2,iv_pic3,iv_pic4,iv_pic5;
            private TextView tv_text1, tv_text2, tv_text3, tv_text4, tv_text5;
            private Banner banner;
        public ThemeVideoHolder(View itemView) {
            super(itemView);
            themeTitle = (TextView) itemView.findViewById(R.id.tv_mess);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
            banner = (Banner) itemView.findViewById(R.id.banner);
            view = (LinearLayout) itemView.findViewById(R.id.view);
            ll_home_class1  = (LinearLayout) itemView.findViewById(R.id.ll_home_class1);
            ll_home_class2  = (LinearLayout) itemView.findViewById(R.id.ll_home_class2);
            ll_home_class3  = (LinearLayout) itemView.findViewById(R.id.ll_home_class3);
            ll_home_class4  = (LinearLayout) itemView.findViewById(R.id.ll_home_class4);
            ll_home_class5  = (LinearLayout) itemView.findViewById(R.id.ll_home_class5);
            tv_text1 = (TextView) itemView.findViewById(R.id.tv_text1);
            tv_text2 = (TextView) itemView.findViewById(R.id.tv_text2);
            tv_text3 = (TextView) itemView.findViewById(R.id.tv_text3);
            tv_text4 = (TextView) itemView.findViewById(R.id.tv_text4);
            tv_text5 = (TextView) itemView.findViewById(R.id.tv_text5);
            iv_pic1 = (ImageView) itemView.findViewById(R.id.iv_pic1);
            iv_pic2 = (ImageView) itemView.findViewById(R.id.iv_pic2);
            iv_pic3 = (ImageView) itemView.findViewById(R.id.iv_pic3);
            iv_pic4 = (ImageView) itemView.findViewById(R.id.iv_pic4);
            iv_pic5 = (ImageView) itemView.findViewById(R.id.iv_pic5);
        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_product_icon;
        private TextView tv_product_name;
        private TextView tv_product_price;
        private TextView tv_product_jifen;
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_product_price = (TextView) itemView.findViewById(R.id.tv_product_price);
            cardView = (CardView) itemView;

            iv_product_icon = (ImageView) itemView.findViewById(R.id.iv_product_icon);
            tv_product_name = (TextView) itemView.findViewById(R.id.tv_product_name);
            tv_product_jifen = (TextView) itemView.findViewById(R.id.tv_product_jifen);
        }
    }

    @Override
    public void onClick(View v) {

         switch (v.getId()){
                    case R.id.ll_home_class1:
                        intent = new Intent(ctx, SearchResultActivity.class);
                        gcid = homeclass.get(0).getGcid();
                        intent.putExtra("gcid",gcid);
                        ctx.startActivity(intent);
                        break;
             case R.id.ll_home_class2:
                 intent = new Intent(ctx, SearchResultActivity.class);
                 gcid = homeclass.get(1).getGcid();
                       intent.putExtra("gcid",gcid);
                 ctx.startActivity(intent);
                 break;
             case R.id.ll_home_class3:
                 gcid = homeclass.get(2).getGcid();
                 intent = new Intent(ctx, SearchResultActivity.class);
                 intent.putExtra("gcid",gcid);
                 ctx.startActivity(intent);
                 break;
             case R.id.ll_home_class4:
                 gcid = homeclass.get(3).getGcid();
                 intent = new Intent(ctx, SearchResultActivity.class);
                 intent.putExtra("gcid",gcid);
                 ctx.startActivity(intent);
                 break;
             case R.id.ll_home_class5:
                 gcid = homeclass.get(4).getGcid();
                 intent = new Intent(ctx, SearchResultActivity.class);
                 intent.putExtra("gcid",gcid);
                 ctx.startActivity(intent);
                 break;

                }
    }
}
