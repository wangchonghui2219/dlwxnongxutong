package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.MyApplition;
import com.example.administrator.dlwxnongxutong.adabter.MyCollectAdapter;
import com.example.administrator.dlwxnongxutong.bean.MyCollectBean;
import com.example.administrator.dlwxnongxutong.bean.NewProductBean;
import com.example.administrator.dlwxnongxutong.bean.PhotoBean;
import com.example.administrator.dlwxnongxutong.bean.ScBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.example.administrator.dlwxnongxutong.activity.MyApplition.sc;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 上午 8:36
 * @name 我的收藏
 */
public class MyCollectActivity extends BaseActiVity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar mTbToolbar;
    @BindView(R.id.rv_collect)
    LRecyclerView mRvCollect;

    /**
     * 存储我的收藏数据集合
     */
    private List<String> mList;
    private SharedPreferences sp;
    private MyCollectAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_my_collect);

        ButterKnife.bind(this);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);

        adapter = new MyCollectAdapter(ctx,info);
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);

        mRvCollect.setAdapter(lRecyclerViewAdapter);
        mRvCollect.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        mRvCollect.setArrowImageView(R.drawable.iconfont_downgrey);
        mRvCollect.setLayoutManager(new LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL,false));
    }
    private List<NewProductBean.InfoBean> info = new ArrayList<>();
    /**
     * 联网获得我的收藏商品
     */
    private  int page = 1;
    private void netWorking() {
        OkGo.get(NetUtils.MY_COLLECT)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .params("page",page)
                .execute(new StringCallback() {



                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        wch("收藏："+s);
                        Gson gson = new Gson();
                        NewProductBean myCollectBean = gson.fromJson(s, NewProductBean.class);
                        if (myCollectBean.getCode() == 200){
                            info.addAll(myCollectBean.getInfo());
                            adapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

    @Override
    public void initData() {
       mToolbarTitle.setText("我的收藏");

        netWorking();
    }

    @Override
    public void initListener() {
        /**
         * 下拉刷新
         */
        mRvCollect.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new ArticleTask().execute();
                }
            });
        /**
         * 上拉加载更多
         */
        mRvCollect.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
               wch("sssssssss");
                    new MoreArticleTask().execute();
            }
        });
        adapter.setOnItemCleckListener(new MyCollectAdapter.OnItemCleckListener() {
            @Override
            public void onItemcleck(View itemView, NewProductBean.InfoBean infoBean) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }


    /**
     * 返回
     */
    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();

    }

    private class  MoreArticleTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
                page++;
                netWorking();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mRvCollect.refreshComplete();
        }
    }
    private class ArticleTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
        info.clear();
                page = 1;
                netWorking();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mRvCollect.refreshComplete();
            adapter.notifyDataSetChanged();
        }
    }
}
