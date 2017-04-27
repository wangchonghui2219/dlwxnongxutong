package com.example.administrator.dlwxnongxutong.activitys;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.MyApplition;
import com.example.administrator.dlwxnongxutong.adabter.AllEvauateAdapter;
import com.example.administrator.dlwxnongxutong.adabter.ProductDetailAdapter;
import com.example.administrator.dlwxnongxutong.bean.AllevauateBean;
import com.example.administrator.dlwxnongxutong.bean.Goodsdetails;
import com.example.administrator.dlwxnongxutong.bean.IsCollect;
import com.example.administrator.dlwxnongxutong.bean.NewProductBean;
import com.example.administrator.dlwxnongxutong.bean.ScBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.CartBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.InfoBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.example.administrator.dlwxnongxutong.views.NoListView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.wang.avi.AVLoadingIndicatorView;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static com.alipay.sdk.app.statistic.c.G;
import static com.example.administrator.dlwxnongxutong.activity.MyApplition.infoBean;
import static com.example.administrator.dlwxnongxutong.activity.MyApplition.sc;


/**
 * Created by scy on 2017/1/12.
 * 产品详情
 */
public class ProductDetailActivity extends BaseActiVity implements RadioGroup.OnCheckedChangeListener {
    private RecyclerView rlv_product_detail;
    private Banner banner;
    private TextView tv_buy;
    private ArrayList<String> pics = new ArrayList<String>();
    private ProductDetailAdapter adapter;
    private String gname;
    private double gp;
    private double intl;
    private String sales;
    private TextView tv_product_name;
    private TextView tv_price;
    private TextView tv_product_jifen;
    private TextView tv_product_yuexiaoliang;
    private List<NewProductBean.InfoBean> products = new ArrayList<>();
    private String godsid;
    private WebView web_pics;
    private LinearLayout ll_progress;
    private String user_id;
    private ImageView iv_pic;
    private TextView tv_name, tv_num;
    private TextView tv_joincat;
    private NoListView lv_allevaluate;
    private Button btn_check_all;
    private Button btn_dianpu, btn_kefu;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private LinearLayout ll_coller;
    private Intent intent;
    private String gpo;
    private String bid;
    private String gcname;
    private int isintpay;
    private double intlpay;
    private String seller;
    private String mobile;

    @Override
    public void initView() {
        Intent intent = getIntent();
        pics = intent.getStringArrayListExtra("pics");
        gname = intent.getStringExtra("gname");
        gp = intent.getDoubleExtra("gp", 0.0);
        intl = intent.getDoubleExtra("intl", 0.0);
        sales = intent.getStringExtra("sales");
        godsid = intent.getStringExtra("godsid");
        String godshtml = intent.getStringExtra("godshtml");
        gpo = intent.getStringExtra("gpo");
        bid = intent.getStringExtra("bid");
        gcname = intent.getStringExtra("gcname");
        isintpay = intent.getIntExtra("isintpay", 0);
        intlpay = intent.getDoubleExtra("intlpay", 0.0);
        seller = intent.getStringExtra("seller");
        mobile = intent.getStringExtra("mobile");
        setContentView(R.layout.activity_product_detail);
        //找到recyclerview
        rlv_product_detail = (RecyclerView) findViewById(R.id.rlv_product_detail);
        tv_buy = (TextView) findViewById(R.id.tv_buy);
        ll_progress = (LinearLayout) findViewById(R.id.ll_progress);
        tv_joincat = (TextView) findViewById(R.id.tv_joincat);
        lv_allevaluate = (NoListView) findViewById(R.id.lv_allevaluate);
        btn_check_all = (Button) findViewById(R.id.btn_check_all);
        btn_kefu = (Button) findViewById(R.id.btn_kefu);
        tv_num = (TextView) findViewById(R.id.tv_num);
        web_pics = (WebView) findViewById(R.id.web_pics);
        btn_dianpu = (Button) findViewById(R.id.btn_dianpu);
        ll_coller = (LinearLayout) findViewById(R.id.ll_coller);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        tv_name = (TextView) findViewById(R.id.tv_name);
        SharedPreferences sp = getSharedPreferences(SPUtils.SP_MODE, MODE_PRIVATE);
        user_id = sp.getString(SPUtils.USER_ID, "");
        if (TextUtils.isEmpty(gname)) {
            netWorking();
        } else {
            initWeb(godshtml);
            initToolbar();
            initHeadView();
            iscollect();
            getAllevaluate();
        }


    }

    private void initWeb(String godshtml) {
        web_pics.loadUrl(godshtml);
        WebSettings settings = web_pics.getSettings();
        settings.setJavaScriptEnabled(true);

        web_pics.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    ;

    /**
     * 联网获取商品信息
     */
    private void netWorking() {
        OkGo.get(NetUtils.Goods_message)
                .params("godsid", godsid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        wch(s);
                        Gson gson = new Gson();
                        NewProductBean goodsdetails = gson.fromJson(s, NewProductBean.class);
                        if (goodsdetails.getCode() == 200) {
                            List<NewProductBean.InfoBean> info = goodsdetails.getInfo();
                            NewProductBean.InfoBean infoBean = info.get(0);
                            List<NewProductBean.InfoBean.GcBean> gc = infoBean.getGc();
                            pics = new ArrayList<String>();
                            for (int i = 0; i < gc.size(); i++) {
                                String img = gc.get(i).getImg();
                                pics.add(img);
                            }
                            gname = infoBean.getGname();
                            gp = infoBean.getGp();
                            intl = infoBean.getIntl();
                            sales = infoBean.getSales();
                            String godshtml = infoBean.getGodshtml();
                            initWeb(godshtml);
                            gpo = infoBean.getGpo();
                            bid = infoBean.getBid();
                            gcname = infoBean.getGcname();
                            isintpay = infoBean.getIsintpay();
                            intlpay = infoBean.getIntlpay();
                            mobile = infoBean.getMobile();
                            seller = infoBean.getSeller();
                            List<ScBean> sc = infoBean.getSc();
                            MyApplition applition = new MyApplition();
                            applition.setSc(sc);

                            initToolbar();
                            initHeadView();
                            iscollect();
                            getAllevaluate();
                        }
                        toast(goodsdetails.getMessage());

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        toast("网络连接失败");
                        initToolbar();
                        initHeadView();
                        iscollect();

                    }
                });

    }

    @Override
    protected void onResume() {


        super.onResume();
    }

    /**
     * 初始化toolbar
     */
    private void initToolbar() {
        //设置toolabr
        Toolbar tb_toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        tb_toolbar.setTitle("");
        setSupportActionBar(tb_toolbar);
        //显示toolbar的返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        }
    }

    /**
     * 初始化头布局
     */
    private void initHeadView() {
        banner = (Banner) findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(pics);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        tv_product_name = (TextView) findViewById(R.id.tv_product_name);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_product_jifen = (TextView) findViewById(R.id.tv_product_jifen);
        tv_product_yuexiaoliang = (TextView) findViewById(R.id.tv_product_yuexiaoliang);
        tv_product_name.setText(gname);
        tv_price.setText("￥" + gp);
        tv_product_jifen.setText((intl * gp) + "积分");
        tv_product_yuexiaoliang.setText("月销量" + sales);
        //开始轮播
        banner.startAutoPlay();
    }

    /**
     * 判断商品是否被收藏
     *
     * @return
     */
    private int state = 0;

    public void iscollect() {
        OkGo.get(NetUtils.IS_collect)
                .params("userid", user_id)
                .params("godsid", godsid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        IsCollect isCollect = gson.fromJson(s, IsCollect.class);
                        if (isCollect.code == 200) {
                            ll_progress.setVisibility(View.GONE);
                            if (isCollect.info.state == 1) {//收藏了
                                iv_pic.setBackgroundResource(R.drawable.icon_shoucanga);
                                tv_name.setTextColor(getResources().getColor(R.color.red));
                                state = 1;
                            } else {
                                state = 0;
                                iv_pic.setBackgroundResource(R.drawable.icon_shoucang);
                                tv_name.setTextColor(getResources().getColor(R.color.textcolor));
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ProductDetail Page")
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
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
    public void initData() {
        //recyclerview的属性设置
        GridLayoutManager layoutManager = new GridLayoutManager(ctx, 2);
        rlv_product_detail.setLayoutManager(layoutManager);


        getNewGoods();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_seachright, menu);
        menu.findItem(R.id.item_seach_right);
        return true;
    }


    @Override
    public void initListener() {
        tv_buy.setOnClickListener(this);
        ll_coller.setOnClickListener(this);
        tv_joincat.setOnClickListener(this);
        btn_check_all.setOnClickListener(this);
        btn_dianpu.setOnClickListener(this);
        btn_kefu.setOnClickListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.item_seach_right:
                Intent intent = new Intent(ctx, MainActivity.class);
                intent.putExtra("seach", "搜索");
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        client.connect();


        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onStop() {
        super.onStop();
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        //结束轮播
        banner.stopAutoPlay();

        client.disconnect();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_buy:
                addListDate();
                intent = new Intent(ctx, ProductAttributeActivity.class);
                intent.putExtra("way", "立即购买");
                intent.putExtra("gname", gname);
                intent.putExtra("gp", gp);
                intent.putExtra("gpo", gpo);
                intent.putExtra("intlpay", intlpay);
                intent.putExtra("isintpay", isintpay);
                intent.putExtra("seller", seller);
                intent.putExtra("godsid", godsid);
                startActivity(intent);
                break;
            case R.id.ll_coller:
                if (state == 1) {   //如果已经收藏点击不收藏该商品
                    iv_pic.setBackgroundResource(R.drawable.icon_shoucang);
                    tv_name.setTextColor(getResources().getColor(R.color.textcolor));
                    state = 0;
                } else {//未收藏事点击变为收藏

                    changeCollect();
                }

                break;
            case R.id.tv_joincat://加入购物车
                Intent intent = new Intent(ctx, ProductAttributeActivity.class);
                intent.putExtra("gname", gname);
                intent.putExtra("gp", gp);
                intent.putExtra("gpo", gpo);
                intent.putExtra("godsid", godsid);
                intent.putExtra("way", "购物车");
                startActivity(intent);
                break;
            case R.id.btn_check_all://查看全部评价
                intent = new Intent(ctx, AllEvauateActivity.class);
                intent.putExtra("goodsid", godsid);
                startActivity(intent);
                break;
            case R.id.btn_dianpu:
                intent = new Intent(ctx, DianPuActivity.class);
                intent.putExtra("bid", bid);
                intent.putExtra("seller",seller);
                intent.putStringArrayListExtra("pics", pics);
                startActivity(intent);
                break;

            case R.id.btn_kefu:
                if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(this, "当前商家暂无客服，请见谅！！", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
                break;
        }
    }

    /**
     * 把需要购买的数据加入集合
     */
    private void addListDate() {

        List<InfoBean> infoList = new ArrayList<>();
        InfoBean listBean = new InfoBean();
        List<CartBean> cartlist = new ArrayList<>();
        CartBean cartBean = new CartBean();
        cartBean.setGp(gp);
        cartBean.setGpo(gpo);
        cartBean.setGname(gname);
        cartBean.setGid(godsid);
        cartBean.setBid(bid);
        Log.i("isintpay",isintpay+"");
        cartBean.setIsintpay(isintpay);
        cartBean.setIntlpay(intlpay*gp);
        cartlist.add(cartBean);
        listBean.setSeller(seller);
        listBean.setCart(cartlist);
        infoList.add(listBean);
        MyApplition applition = new MyApplition();
        applition.setList(infoList);
    }
    /**
     * 获得商品全部评价
     */
    public void getAllevaluate() {
        OkGo.get(NetUtils.Get_ALLevaluate)
                .params("godsid",godsid)
                .params("state",1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        wch("前三条评价"+s);
                        Gson gson = new Gson();
                        AllevauateBean allevauateBean = gson.fromJson(s, AllevauateBean.class);

                        if (allevauateBean.getCode() == 200) {
                            List<AllevauateBean.InfoBean.ListBean> list = allevauateBean.getInfo().getList();
//                            setListViewHeightBasedOnChildren(lv_allevaluate,list);
                            AllEvauateAdapter evauateAdapter = new AllEvauateAdapter(ctx,list);
                            lv_allevaluate.setAdapter(evauateAdapter);
                            tv_num.setText("全部评价("+allevauateBean.getInfo().getNum()+")");
                            ll_progress.setVisibility(View.GONE);
                        }else{
                            toast(allevauateBean.getMessage());
                        }


                    }
                });
    }
    public void setListViewHeightBasedOnChildren(ListView listView,List<AllevauateBean.InfoBean.ListBean> list) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);

    }
    /**
     * 上传收藏该商品
     */
    private void changeCollect() {

        OkGo.post(NetUtils.Collect_GOODS)
                .params("userid",user_id)
                .params("godsid",godsid)
                .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    Gson gson = new Gson();
                                    IsCollect isCollect = gson.fromJson(s, IsCollect.class);
                                    if (isCollect.code == 200) {
                                        if (isCollect.info.state == 1) {
                                            iv_pic.setBackgroundResource(R.drawable.icon_shoucanga);
                                            tv_name.setTextColor(getResources().getColor(R.color.red));
                                            state = 1;
                                            toast("收藏成功");
                            }else   {
                                        toast("收藏失败");
                                        }
                        }
                                    else{
                                        toast(isCollect.message);
                                    }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {

                    }
                });

    }

    /**
     * 获取新品推荐
     */
    public void getNewGoods() {
        OkGo.get(NetUtils.NEW_PRODUCT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        wch(s);
                        NewProductBean newProductBean = gson.fromJson(s, NewProductBean.class);
                        if (newProductBean.getCode() == 200) {
                            products = newProductBean.getInfo();
                            adapter = new ProductDetailAdapter(ctx, products, new ProductDetailActivity());
                            rlv_product_detail.setAdapter(adapter);
                            /**
                             * 添加接口点击新品推荐条目跳转下一页关闭当前页
                             */
                            adapter.setFinishActivity(new ProductDetailAdapter.FinishActivity() {
                                @Override
                                public void setFinish() {
                                    finish();
                                }
                            });

                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
}
