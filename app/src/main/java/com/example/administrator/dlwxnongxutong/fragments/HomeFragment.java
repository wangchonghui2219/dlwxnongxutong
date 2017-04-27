package com.example.administrator.dlwxnongxutong.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.SearchResultActivity;
import com.example.administrator.dlwxnongxutong.adabter.HostProductAdapter;
import com.example.administrator.dlwxnongxutong.adabter.NewProductAdapter;
import com.example.administrator.dlwxnongxutong.adabter.ProductAdapter;

import com.example.administrator.dlwxnongxutong.bean.HomeClassBean;
import com.example.administrator.dlwxnongxutong.bean.HomeLunBoBean;
import com.example.administrator.dlwxnongxutong.bean.NewProductBean;

import com.example.administrator.dlwxnongxutong.utils.NetUtils;

import com.example.administrator.dlwxnongxutong.views.CustomLinearLayoutManager;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.R.attr.data;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.administrator.dlwxnongxutong.R.id.view;


/**
 * @作者 wch
 * @create at 2017/1/7 0007 上午 10:49
 * @name 首页     https://github.com/crazyfzw/RecycleViewWithHeader
 */
public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView rlv_home,rlv_home2;
    private LinearLayout ll_progress;
    private List<NewProductBean.InfoBean> products = new ArrayList<>();
    private ProductAdapter productAdapter;
    private List<HomeClassBean.InfoBean> homeClassBeanInfo  = new ArrayList<HomeClassBean.InfoBean>();
    private Banner banner;
    private ScrollView scview;
    private int tag = 0;
    private LinearLayout view,ll_home_class1,ll_home_class2,ll_home_class3,ll_home_class4,ll_home_class5;
    private ImageView iv_pic1,iv_pic2,iv_pic3,iv_pic4,iv_pic5;
    private TextView tv_text1, tv_text2, tv_text3, tv_text4, tv_text5;
    @Override
    public int getLayoutResID() {
        return R.layout.fragment_home;
    }
    @Override
    public void initView(View view) {
        rlv_home = (RecyclerView) view.findViewById(R.id.rlv_home);
        rlv_home2 = (RecyclerView) view.findViewById(R.id.rlv_home2);
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeColors(Color.RED, Color.BLUE);
        mSwipeRefreshWidget.setOnRefreshListener(this);
        banner = (Banner) view.findViewById(R.id.banner);
        banner = (Banner) view.findViewById(R.id.banner);
        scview = (ScrollView) view.findViewById(R.id.scview);
        ll_home_class1  = (LinearLayout) view.findViewById(R.id.ll_home_class1);
        ll_home_class2  = (LinearLayout) view.findViewById(R.id.ll_home_class2);
        ll_home_class3  = (LinearLayout) view.findViewById(R.id.ll_home_class3);
        ll_home_class4  = (LinearLayout) view.findViewById(R.id.ll_home_class4);
        ll_home_class5  = (LinearLayout) view.findViewById(R.id.ll_home_class5);
        ll_progress = (LinearLayout) view.findViewById(R.id.ll_progress);
        tv_text1 = (TextView) view.findViewById(R.id.tv_text1);
        tv_text2 = (TextView) view.findViewById(R.id.tv_text2);
        tv_text3 = (TextView) view.findViewById(R.id.tv_text3);
        tv_text4 = (TextView) view.findViewById(R.id.tv_text4);
        tv_text5 = (TextView) view.findViewById(R.id.tv_text5);
        iv_pic1 = (ImageView) view.findViewById(R.id.iv_pic1);
        iv_pic2 = (ImageView) view.findViewById(R.id.iv_pic2);
        iv_pic3 = (ImageView) view.findViewById(R.id.iv_pic3);
        iv_pic4 = (ImageView) view.findViewById(R.id.iv_pic4);
        iv_pic5 = (ImageView) view.findViewById(R.id.iv_pic5);
        ll_progress.setVisibility(View.VISIBLE);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));


//        rlv_home.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//
//                int totalItemCount = layoutManager.getItemCount();
//
//                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
//
//                if (!loading && totalItemCount < (lastVisibleItem + 3)) {
//                    new ArticleTask().execute();
//                    loading = true;
//
//                }
//            }
//        });

    }



    @Override
    public void initData() {
        setRecycler();
    }


    /**
     * 设置recyclerview
     */
    private void setRecycler() {
         CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getActivity(), 2,LinearLayoutManager.VERTICAL,false);
        CustomLinearLayoutManager layoutManager1 = new CustomLinearLayoutManager(getActivity(), 2,LinearLayoutManager.VERTICAL,false);
        layoutManager.setScrollEnabled(false);
        layoutManager1.setScrollEnabled(false);
        rlv_home.setLayoutManager(layoutManager);
        rlv_home2.setLayoutManager(layoutManager1);
        layoutManager.getItemCount();
        layoutManager1.getItemCount();
        /**
         * 首次进入显示加载动画
         */
        mSwipeRefreshWidget.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshWidget.setRefreshing(true);
                new MoreArticleTask().execute();
            }
        });

    }
    @Override
    public void initListener() {
        ll_home_class1.setOnClickListener(this);
        ll_home_class2.setOnClickListener(this);
        ll_home_class3.setOnClickListener(this);
        ll_home_class4.setOnClickListener(this);
        ll_home_class5.setOnClickListener(this);
    }
    private Intent intent;
    private String gcid;
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_home_class1:
                intent = new Intent(ctx, SearchResultActivity.class);
                gcid = homeClassBeanInfo.get(0).getGcid();
                intent.putExtra("gcid",gcid);
                ctx.startActivity(intent);
                break;
            case R.id.ll_home_class2:
                intent = new Intent(ctx, SearchResultActivity.class);
                gcid = homeClassBeanInfo.get(1).getGcid();
                intent.putExtra("gcid",gcid);
                ctx.startActivity(intent);
                break;
            case R.id.ll_home_class3:
                gcid = homeClassBeanInfo.get(2).getGcid();
                intent = new Intent(ctx, SearchResultActivity.class);
                intent.putExtra("gcid",gcid);
                ctx.startActivity(intent);
                break;
            case R.id.ll_home_class4:
                gcid = homeClassBeanInfo.get(3).getGcid();
                intent = new Intent(ctx, SearchResultActivity.class);
                intent.putExtra("gcid",gcid);
                ctx.startActivity(intent);
                break;
            case R.id.ll_home_class5:
                gcid = homeClassBeanInfo.get(4).getGcid();
                intent = new Intent(ctx, SearchResultActivity.class);
                intent.putExtra("gcid",gcid);
                ctx.startActivity(intent);
                break;

        }
    }

    /**
     * 联网获得数据
     */
    public void getData() {
        getNewProduct();
    }
    private void getNewProduct() {
        OkGo.post(NetUtils.NEW_PRODUCT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("wch","商品"+s+"");
                        Gson gson = new Gson();
                        NewProductBean newProductBean = gson.fromJson(s, NewProductBean.class);
                        if (newProductBean.getCode() == 200){
                            products = newProductBean.getInfo();
                            tag = products.size();
                            rlv_home.setAdapter(new NewProductAdapter(ctx,products));
                           getReviewGoods();

                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void getReviewGoods() {
       OkGo.get(NetUtils.ReviewGoods)
               .execute(new StringCallback() {
                   @Override
                   public void onSuccess(String s, Call call, Response response) {
                       Gson gson =  new Gson();
                       NewProductBean newProductBean = gson.fromJson(s, NewProductBean.class);
                       Log.i("wch",s+"");
                       if (newProductBean.getCode() == 200) {
                           List<NewProductBean.InfoBean> info = newProductBean.getInfo();

                           rlv_home2.setAdapter(new HostProductAdapter(ctx,info));
                           getHomeClass();
                           scview.setVisibility(View.VISIBLE);
                       }
                   }

                   @Override
                   public void onError(Call call, Response response, Exception e) {
                       super.onError(call, response, e);
                   }
               });
    }
    private boolean loading = false;
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        new MoreArticleTask().execute();
    }

    private void getHomeClass() {
      OkGo.get(NetUtils.HOME_CLASS)
              .execute(new StringCallback() {
                  @Override
                  public void onSuccess(String s, Call call, Response response) {
                      wch(s);
                            Gson gson = new Gson();
                      HomeClassBean homeClassBean = gson.fromJson(s, HomeClassBean.class);
                      if (homeClassBean.getCode() == 200) {
                          homeClassBeanInfo = homeClassBean.getInfo();
//
                          Glide.with(ctx).load(homeClassBeanInfo.get(0).getGimg()).into(iv_pic1);
                          tv_text1.setText(homeClassBeanInfo.get(0).getGcname());

                          Glide.with(ctx).load(homeClassBeanInfo.get(1).getGimg()).into(iv_pic2);
                          tv_text2.setText(homeClassBeanInfo.get(1).getGcname());


                          Glide.with(ctx).load(homeClassBeanInfo.get(2).getGimg()).into(iv_pic3);
                          tv_text3.setText(homeClassBeanInfo.get(2).getGcname());


                          Glide.with(ctx).load(homeClassBeanInfo.get(3).getGimg()).into(iv_pic4);
                          tv_text4.setText(homeClassBeanInfo.get(3).getGcname());


                          Glide.with(ctx).load(homeClassBeanInfo.get(4).getGimg()).into(iv_pic5);
                          tv_text5.setText(homeClassBeanInfo.get(4).getGcname());
                          scview.setVisibility(View.VISIBLE);
                          getLunBoPic();
                      }else{
                          Toast.makeText(ctx,homeClassBean.getMessage(),Toast.LENGTH_SHORT).show();
                      }
                  }

                  @Override
                  public void onError(Call call, Response response, Exception e) {
                      super.onError(call, response, e);
                  }
              });
    }

    /**
     * 联网获得轮播图
     */
    private List<HomeLunBoBean.InfoBean> infopics = new ArrayList<>();
    private void getLunBoPic() {
        OkGo.get(NetUtils.Home_LUNBO)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) { 
                        Log.i("wch",s+"");
                        Gson gson = new Gson();
                        HomeLunBoBean homeLunBoBean = gson.fromJson(s, HomeLunBoBean.class);
                        if (homeLunBoBean.getCode() == 200) {
                            infopics = homeLunBoBean.getInfo();
//                            productAdapter = new ProductAdapter(products,getActivity(),tag,homeClassBeanInfo,infopics);
//                            LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(productAdapter);
//                            rlv_home.setAdapter(productAdapter);
//                            rlv_home.setHasFixedSize(true);
                            if (infopics != null) {

                                ll_progress.setVisibility(View.GONE);
                            List<String> pics = new ArrayList<String>();
                            for (int i = 0; i < infopics.size(); i++) {
                                pics.add(infopics.get(i).getCimg());
                            }
                            banner.setImageLoader(new GlideImageLoader());
                            banner.setImages(pics);
                            banner.start();
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
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
    private class MoreArticleTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            try {
                getData();
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mSwipeRefreshWidget.setRefreshing(false);
        }
    }
    /**
     * 上拉加载
     */
    private class ArticleTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
                Log.i("wch","下拉刷新");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mSwipeRefreshWidget.setRefreshing(false);
            loading = false;
            productAdapter.notifyDataSetChanged();


        }
    }

}
