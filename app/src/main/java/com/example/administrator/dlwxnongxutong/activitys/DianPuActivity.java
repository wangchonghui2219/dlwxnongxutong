package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.adabter.DianpuAdapter;
import com.example.administrator.dlwxnongxutong.adabter.ProductDetailAdapter;
import com.example.administrator.dlwxnongxutong.adabter.SeachAdapter;
import com.example.administrator.dlwxnongxutong.bean.AllGoodsBean;
import com.example.administrator.dlwxnongxutong.bean.NewProductBean;
import com.example.administrator.dlwxnongxutong.bean.ShangJiaBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.views.CustomLinearLayoutManager;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.example.administrator.dlwxnongxutong.R.id.rlv_product_detail;
/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 店铺
 */
public class DianPuActivity extends BaseActiVity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    private RecyclerView rcl_product;
    Banner banner;
  
    TextView tvName;
    @BindView(R.id.rcy_product)
    RecyclerView rcyProduct;
    private String bid;
    private ArrayList<String> pics;
    private View headView;
    private String seller;

    @Override
    public void initView() {
        seller = getIntent().getStringExtra("seller");
        setContentView(R.layout.activity_dian_pu);
        rcl_product = (RecyclerView) findViewById(R.id.rcl_product);
        ButterKnife.bind(this);
        bid = getIntent().getStringExtra("bid");
        pics = getIntent().getStringArrayListExtra("pics");
        inithead();
    }

    /**
     * 初始化头布局
     */
    private void inithead() {
        headView = View.inflate(ctx, R.layout.dianpu_head_item,null);
        banner = (Banner) headView.findViewById(R.id.banner);
        tvName = (TextView) headView.findViewById(R.id.tv_name);
        tvName.setText(seller);
    }


    @Override
    public void initData() {
        tbToolbar.setTitle("");
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(ctx,2, LinearLayoutManager.VERTICAL,false);
        manager.setScrollEnabled(false);
        rcyProduct.setLayoutManager(manager);
        CustomLinearLayoutManager layoutManager1 = new CustomLinearLayoutManager(ctx, 2,LinearLayoutManager.VERTICAL,false);
        layoutManager1.setScrollEnabled(false);
        rcl_product.setLayoutManager(layoutManager1);
        manager.getItemCount();
        layoutManager1.getItemCount();

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

    }

    @Override
    protected void onResume() {
       getShangjiaDate();

        super.onResume();
    }

    /**
     * 获得商品信息
     */
    private void netWorking() {

        OkGo.get(NetUtils.NEW_PRODUCT)
                .params("bid",bid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();

                        NewProductBean newProductBean = gson.fromJson(s, NewProductBean.class);
                        if (newProductBean.getCode() == 200) {
                            List<NewProductBean.InfoBean> info = newProductBean.getInfo();
                            DianpuAdapter productDetailAdapter = new DianpuAdapter(ctx, info, new DianPuActivity(),cimg,bname,seller);
                            toolbarTitle.setText(seller);
                            rcyProduct.setAdapter(productDetailAdapter);
                                getAllproduct();
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
    private List<ShangJiaBean.InfoBean.CimgBean> cimg;
    private String bname;
    public void getShangjiaDate() {
        OkGo.get(NetUtils.SHANGJIA)
                .params("bid",bid)
                .execute(new StringCallback() {



                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        wch(s);
                        Gson gson = new Gson();
                        ShangJiaBean shangJiaBean = gson.fromJson(s, ShangJiaBean.class);
                        if (shangJiaBean.getCode() == 200) {
                            ShangJiaBean.InfoBean info = shangJiaBean.getInfo();
                            bname = info.getBname();
                            cimg = info.getCimg();
                            netWorking();
                        }
                    }
                });
    }

    /**
     * 获取全部商品
     */
    public void getAllproduct() {
            OkGo.get(NetUtils.ALL_GOODS)
                    .params("bid",bid)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                                 wch(s);
                            Gson gson = new Gson();
                            NewProductBean newProductBean = gson.fromJson(s, NewProductBean.class);
                            if (newProductBean.getCode() == 200) {
                                List<NewProductBean.InfoBean> info = newProductBean.getInfo();
                                SeachAdapter adapter = new SeachAdapter(ctx,info);
                                rcl_product.setAdapter(adapter);

                            }else{
                                Toast.makeText(ctx, newProductBean.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
                        }
                    });
    }
}
