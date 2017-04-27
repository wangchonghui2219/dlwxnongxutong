package com.example.administrator.dlwxnongxutong.activitys;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.adabter.AllEvauateAdapter;
import com.example.administrator.dlwxnongxutong.adabter.AllPingjiaAdapter;
import com.example.administrator.dlwxnongxutong.bean.AllPingjiaBean;
import com.example.administrator.dlwxnongxutong.bean.AllevauateBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
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
import okhttp3.Call;
import okhttp3.Response;

import static android.R.attr.id;
import static android.R.attr.layout_height;
import static android.R.attr.layout_width;
import static com.example.administrator.dlwxnongxutong.R.id.lv_allevaluate;
import static com.example.administrator.dlwxnongxutong.R.id.rcl_date;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 全部评价
 */
public class AllEvauateActivity extends BaseActiVity {
    @BindView(R.id.tb_toolbar)
    Toolbar toolbar;
    @BindView(R.id.lv_allevaluate)
    LRecyclerView lv_allevaluate;
    private String goodsid;
    private Button btn_submit;
    private List<AllPingjiaBean.InfoBean> list = new ArrayList<>();
    private AllPingjiaAdapter pingjiaAdapter;
    @Override
    public void initView() {
        setContentView(R.layout.activity_all_evauate);
        goodsid = getIntent().getStringExtra("goodsid");
        btn_submit = (Button) findViewById(R.id.btn_submit);
        ButterKnife.bind(this);
    }
    @Override
    public void initData() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        LinearLayoutManager manager = new LinearLayoutManager(ctx, 1, false);
        lv_allevaluate.setLayoutManager(manager);

        pingjiaAdapter = new AllPingjiaAdapter(ctx, list);
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(pingjiaAdapter);
        lv_allevaluate.setAdapter(lRecyclerViewAdapter);
        netWorking();

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
    protected void onResume() {


        super.onResume();
    }
    private int page = 1;
    private void netWorking() {

        OkGo.get(NetUtils.Get_ALLevaluate)
                .params("godsid",goodsid)
                .params("page",page)
                .execute(new StringCallback() {



                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        wch("前三条评价"+s);
                        Gson gson = new Gson();
                        AllPingjiaBean allPingjiaBean = gson.fromJson(s, AllPingjiaBean.class);

                        if (allPingjiaBean.getCode() == 200) {
                            list.addAll(allPingjiaBean.getInfo());
                            pingjiaAdapter.notifyDataSetChanged();
                            if (list.size() == 0) {
                                lv_allevaluate.setVisibility(View.GONE);
                                btn_submit.setVisibility(View.VISIBLE);
                            }

                        }else{
                            toast(allPingjiaBean.getMessage());
                        }


                    }
                });

    }

    @Override
    public void initListener() {
        /**
         * 下拉刷新
         */
        lv_allevaluate.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                list = new ArrayList<AllPingjiaBean.InfoBean>();
                new RefishTask().execute();
            }
        });
        lv_allevaluate.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        lv_allevaluate.setArrowImageView(R.drawable.iconfont_downgrey);

        /**
         * 上拉加载
         */
        lv_allevaluate.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new MoreTask().execute();
            }
        });
    }

    private class RefishTask extends AsyncTask<Void,Void,Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {

                Thread.sleep(2000);
                page = 1;
                netWorking();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            lv_allevaluate.refreshComplete();

        }
    }
    private class MoreTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {

            try {
                page++;
                netWorking();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv_allevaluate.refreshComplete();
            pingjiaAdapter.notifyDataSetChanged();
        }
    }
}
