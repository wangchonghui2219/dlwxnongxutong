package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.adabter.MyOrderBean;
import com.example.administrator.dlwxnongxutong.adabter.MyOrderFromAdapter;
import com.example.administrator.dlwxnongxutong.adabter.ProductDetailAdapter;
import com.example.administrator.dlwxnongxutong.bean.affrm.LisBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.github.jdsjlzx.progressindicator.AVLoadingIndicatorView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static com.alipay.sdk.app.statistic.c.s;
import static com.example.administrator.dlwxnongxutong.R.id.view;

/**
 * 
 *
 * @作者 wch
 *
 * @create at 2017/1/11 0011 下午 5:03
 * 
 * @name 全部订单
 */
public class MyOrderFromActivity extends AppCompatActivity implements View.OnClickListener{
    private Context ctx;
    private ImageView iv_back;
    private TextView toolbar_title;
    private LinearLayout ll_progress;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    /**
     * 存储订单数据集合
     */
    private List<String> mList;
    private RecyclerView ry_list;
    private MyOrderFromAdapter mAdapter;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.ctx = this;
        setContentView(R.layout.activity_my_order_from);

        initView();
        initData();
        initListener();


    }



    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        ll_progress = (LinearLayout) findViewById(R.id.ll_progress);
        ry_list = (RecyclerView) findViewById(R.id.ry_list);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);

        netWorking();
    }


    private boolean loading = false;
    private void initData() {
        toolbar_title.setText("我的订单");
        mAdapter = new MyOrderFromAdapter(ctx,info);
        ry_list.setAdapter(mAdapter);
    }
    private void initListener() {
        iv_back.setOnClickListener(this);

        ry_list.setLayoutManager(new LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL,false));
        ry_list.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int totalItemCount = layoutManager.getItemCount();

                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (!loading && totalItemCount < (lastVisibleItem + 3)) {
                    new ArticleTask().execute();
                    loading = true;

                }
            }
        });
    }
    /**
     * 上拉加载
     */
    private class ArticleTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(100);
                Log.i("wch","下拉刷新");
                page++;
                netWorking();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            loading = false;

            mAdapter.notifyDataSetChanged();

        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
                   case R.id.iv_back:

                       finish();
                   break;

               }
    }

    private int page = 1;
   private List<MyOrderBean.InfoBean> info = new ArrayList<>();
    /**
     * 联网获取我的订单列表
     */
    private void netWorking() {
        OkGo.get(NetUtils.MyOrderList)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ll_progress.setVisibility(View.GONE);
                        Log.i("wch",s+"");
                        Gson gson = new Gson();
                        MyOrderBean myOrderBean = gson.fromJson(s, MyOrderBean.class);

                        if (myOrderBean.getCode() == 200) {
                            if (myOrderBean.getInfo().size() != 0) {

                                info.addAll(myOrderBean.getInfo());
                                mAdapter.notifyDataSetChanged();
                            }

                        }else{

                            Toast.makeText(ctx, myOrderBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }



                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Toast.makeText(ctx, "网络连接失败！！", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
