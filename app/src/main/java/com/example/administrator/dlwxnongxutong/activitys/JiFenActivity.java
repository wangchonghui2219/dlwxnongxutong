package com.example.administrator.dlwxnongxutong.activitys;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.adabter.JiFenAdapter;
import com.example.administrator.dlwxnongxutong.bean.JiFenListBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.alipay.sdk.app.statistic.c.s;

public class JiFenActivity extends BaseActiVity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.rl_balance)
    RelativeLayout rlBalance;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.rl_tixian)
    RelativeLayout rlTixian;
    @BindView(R.id.tv_tuikuan)
    TextView tvTuikuan;
    @BindView(R.id.rl_tuikuan)
    RelativeLayout rlTuikuan;
    RecyclerView rclDate;

    LinearLayout ll_progress;
    private SharedPreferences sp;
    private JiFenAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_ji_fen);
        rclDate = (RecyclerView) findViewById(R.id.rcl_date);
        ll_progress = (LinearLayout) findViewById(R.id.ll_progress);
        ButterKnife.bind(this);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);
        netWorking();
    }
    @Override
    public void initData() {
        tbToolbar.setTitle("");
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        LinearLayoutManager manager = new LinearLayoutManager(ctx,LinearLayout.VERTICAL,false);
        rclDate.setLayoutManager(manager);
        adapter = new JiFenAdapter(ctx,info);
        rclDate.setAdapter(adapter);
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
    private boolean loading = false;
    private int page = 1;
    @Override
    public void initListener() {
            rclDate.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

            adapter.notifyDataSetChanged();

        }
    }


    private List<JiFenListBean.InfoBean> info = new ArrayList<>();
    /**
     * 联网货品去数据
     */
    private void netWorking() {
        OkGo.get(NetUtils.GetUserIntl)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .params("page",1)
                .execute(new StringCallback() {



                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                            wch(s);
                        ll_progress.setVisibility(View.GONE);
                        Gson gson = new Gson();
                        JiFenListBean jiFenListBean = gson.fromJson(s, JiFenListBean.class);
                        if (jiFenListBean.getCode() == 200) {
                            info.addAll(jiFenListBean.getInfo());
                          adapter.notifyDataSetChanged();
                        }else{
                            toast(jiFenListBean.getMessage());
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        toast("网络连接失败！！");
                    }
                });
    }
    //    @OnClick({R.id.rl_balance, R.id.rl_tixian, R.id.rl_tuikuan})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.rl_balance://余额
//                rlBalance.setBackgroundResource(R.drawable.shape_yu_e_all1);
//                rlTixian.setBackgroundResource(R.drawable.shape_jifen_lingwan);
//                rlTuikuan.setBackgroundResource(R.drawable.shape_yu_e_tuikuan2);
//                tvYue.setTextColor(getResources().getColor(R.color.white));
//                tvTixian.setTextColor(getResources().getColor(R.color.textcolor));
//                tvTuikuan.setTextColor(getResources().getColor(R.color.textcolor));
//                break;
//            case R.id.rl_tixian://提现,以去掉
//                rlBalance.setBackgroundResource(R.drawable.shape_yu_e_all2);
//                rlTixian.setBackgroundResource(R.color.palered);
//                rlTuikuan.setBackgroundResource(R.drawable.shape_yu_e_tuikuan2);
//                tvYue.setTextColor(getResources().getColor(R.color.textcolor));
//                tvTixian.setTextColor(getResources().getColor(R.color.white));
//                tvTuikuan.setTextColor(getResources().getColor(R.color.textcolor));
//                break;
//            case R.id.rl_tuikuan://退款 ，以去掉
//                rlTixian.setBackgroundResource(R.drawable.shape_jifen_lingwan);
//                rlBalance.setBackgroundResource(R.drawable.shape_yu_e_all2);
//                rlBalance.setBackgroundResource(R.drawable.shape_yu_e_all2);
//                rlTuikuan.setBackgroundResource(R.drawable.shape_yu_e_tuikuan1);
//                tvYue.setTextColor(getResources().getColor(R.color.textcolor));
//                tvTixian.setTextColor(getResources().getColor(R.color.textcolor));
//                tvTuikuan.setTextColor(getResources().getColor(R.color.white));
//                break;
//        }
//    }
}
