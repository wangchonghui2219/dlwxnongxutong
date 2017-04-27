package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.MyApplition;
import com.example.administrator.dlwxnongxutong.adabter.StandardAdapter;
import com.example.administrator.dlwxnongxutong.bean.NewProductBean;
import com.example.administrator.dlwxnongxutong.bean.ScBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.CartBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.InfoBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 商品属性
 */
public class ProductAttributeActivity extends BaseActiVity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_selete)
    TextView tvSelete;
    @BindView(R.id.iv_jian)
    ImageView ivJian;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.iv_jia)
    ImageView ivJia;
    @BindView(R.id.btn_join)
    Button btn_join;
    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;
    private GridView gvGuige;
    private List<ScBean> sc;
    private String gname;
    private double gp;
    private String gpo;
    private String godsid;
    private int num  = 1;
    private StandardAdapter adapter;
    private String gsid;
    private SharedPreferences sp;
    private String way;
    private SharedPreferences sp1;
    private int gnum;
    private double intlpay;//可以使用多少积分
    private int isintpay;//是否可以使用积分
    private String seller;

    @Override
    public void initView() {

        Intent intent = getIntent();
        way = intent.getStringExtra("way");
        gname = intent.getStringExtra("gname");
        gp = intent.getDoubleExtra("gp",0.0);
        gpo = intent.getStringExtra("gpo");
        godsid = intent.getStringExtra("godsid");
        gnum = intent.getIntExtra("gnum", 0);
        intlpay = intent.getDoubleExtra("intlpay",0.0);
        isintpay = intent.getIntExtra("isintpay",0);
        seller = intent.getStringExtra("seller");
        Log.i("seller",intlpay+"");
        setContentView(R.layout.activity_product_attribute);
        ButterKnife.bind(this);
        gvGuige = (GridView) findViewById(R.id.gv_guige);
        sc = MyApplition.sc;
        sc.toString();
        wch(sc.size()+"");
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);
        if ("立即购买".equals(way)) {
            btn_join.setText("立即购买");
            sp.edit().putString(SPUtils.Product_TAG,"tt").commit();
        }else{
            btn_join.setText("加入购物车");
            sp.edit().putString(SPUtils.Product_TAG,"tag").commit();
        }
        tvNum.setText(num+"");
    }

    @Override
    public void initData() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        Glide.with(ctx).load(gpo).into(ivPic);
        tvTitle.setText(gname);
        gp = sc.get(0).getGprice();
        double jfnum = sc.get(0).getJfnum();
        List<InfoBean> info = MyApplition.info;
        if (info != null) {

            List<CartBean> cart = info.get(0).getCart();
            cart.get(0).setIntlpay(gp*jfnum);
        }
        tvPrice.setText("￥"+gp );
        if (sc != null) {
        gsid = sc.get(0).getGsid();
        }
        adapter = new StandardAdapter(ctx,sc);
        gvGuige.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getItemId()){
                    case android.R.id.home:
                        finish();
                        break;
                }
        return true;
    }

    @Override
    public void initListener() {
        gvGuige.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setBackgroupColor(position);
                gsid = sc.get(position).getGsid();
                gp = sc.get(position).getGprice();
                intlpay = sc.get(position).getJfnum();
                if ("立即购买".equals(way)) {
                List<InfoBean> info = MyApplition.info;
                CartBean cartBean = info.get(0).getCart().get(0);
                cartBean.setIntlpay(gp*intlpay);
                }
                tvPrice.setText("￥"+gp);
            }
        });

    }

    @OnClick({R.id.iv_jian, R.id.iv_jia,R.id.btn_join})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_jian:
                if (num == 1) {
                    num = 1;
                }else {
                    num--;
                }
                tvNum.setText(num+"");
                break;
            case R.id.iv_jia:
                num++;
                tvNum.setText(num+"");
                break;
            case R.id.btn_join:
                if (TextUtils.isEmpty(sp.getString(SPUtils.USER_ID,""))){
                    Intent intent = new Intent(ctx,LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                if ("立即购买".equals(way)) {
                    List<InfoBean> info = MyApplition.info;
                    Intent intent = new Intent(ctx,AffirmDingdanActivity.class);
                    CartBean cartBean = info.get(0).getCart().get(0);
                    cartBean.setGp(gp);
                    cartBean.setGnum(num);
                    cartBean.setIntlpay(gp*intlpay);
//                    cartBean.setSeller(seller);

                    wch(intlpay+"wwww");
                    intent.putExtra("gsid",gsid);
                    intent.putExtra("isintpay",isintpay);
                    intent.putExtra("intlpay",intlpay);
                    intent.putExtra("totalPrice",gp);
                    intent.putExtra("seller",seller);
                    intent.putExtra("isintpay",isintpay);
                    startActivity(intent);
                }else{

                    joinShopCat();
                }


                break;
        }
    }
    /**
     * 加入购物车
     */
    private void joinShopCat() {
        OkGo.post(NetUtils.Join_Cat)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .params("gid",godsid)
                .params("gnum",num)
                .params("spid",gsid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        wch(s);
                        Gson gson = new Gson();
                        com.example.administrator.dlwxnongxutong.bean.InfoBean infoBean = gson.fromJson(s, com.example.administrator.dlwxnongxutong.bean.InfoBean.class);
                            toast(infoBean.message);
                            finish();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });

    }
}