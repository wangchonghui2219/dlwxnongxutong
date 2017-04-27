package com.example.administrator.dlwxnongxutong.activitys;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
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
import com.example.administrator.dlwxnongxutong.adabter.YuEAdpater;
import com.example.administrator.dlwxnongxutong.bean.YuEFanLiBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
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

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 余额界面
 */
public class YuEActivity extends BaseActiVity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    @BindView(R.id.rl_balance)
    RelativeLayout rlBalance;
    @BindView(R.id.rl_tixian)
    RelativeLayout rlTixian;
    @BindView(R.id.rl_tuikuan)
    RelativeLayout rlTuikuan;
    @BindView(R.id.rcl_date)
    RecyclerView rclDate;
    @BindView(R.id.tv_yue)
    TextView tvyue;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.tv_tuikuan)
    TextView tv_Tuikuan;
    private LinearLayout ll_progress;
    private SharedPreferences sp;
    private YuEAdpater adabter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_yu_e);
        ButterKnife.bind(this);
        ll_progress = (LinearLayout) findViewById(R.id.ll_progress);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);

    }

    @Override
    public void initData() {
        tbToolbar.setTitle("");
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        LinearLayoutManager manager = new LinearLayoutManager(ctx, LinearLayout.VERTICAL,false);
        rclDate.setLayoutManager(manager);
        adabter = new YuEAdpater(ctx, info);
        rclDate.setAdapter(adabter);
        netWorking(NetUtils.Redate_List);

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
                if (style == 0) {
                    netWorking(NetUtils.Redate_List);
                }else{
                    netWorking(NetUtils.Draw_money);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            loading = false;

            adabter.notifyDataSetChanged();

        }
    }

    private int style = 0; //0为返利，1为提现
    @OnClick({R.id.rl_balance, R.id.rl_tixian, R.id.rl_tuikuan})
    public void onClick(View view) {
        page = 1;
        switch (view.getId()) {
            case R.id.rl_balance://返利
                style = 0;

                info.clear();
                netWorking(NetUtils.Redate_List);
                rlBalance.setBackgroundResource(R.drawable.shape_yu_e_all1);
                rlTixian.setBackgroundResource(R.color.white);
                rlTixian.setBackgroundResource(R.drawable.shape_editext_line);
                rlTuikuan.setBackgroundResource(R.drawable.shape_yu_e_tuikuan2);
                tvyue.setTextColor(getResources().getColor(R.color.white));
                tvTixian.setTextColor(getResources().getColor(R.color.textcolor));
                tv_Tuikuan.setTextColor(getResources().getColor(R.color.textcolor));
                break;
            case R.id.rl_tixian://已删除

                rlBalance.setBackgroundResource(R.drawable.shape_yu_e_all2);
                rlTixian.setBackgroundResource(R.color.palered);
                rlTuikuan.setBackgroundResource(R.drawable.shape_yu_e_tuikuan2);
                tvyue.setTextColor(getResources().getColor(R.color.textcolor));
                tvTixian.setTextColor(getResources().getColor(R.color.white));
                tv_Tuikuan.setTextColor(getResources().getColor(R.color.textcolor));
                break;
            case R.id.rl_tuikuan://提现
                style = 1;
                info.clear();
                netWorking(NetUtils.Draw_money);
                rlBalance.setBackgroundResource(R.drawable.shape_yu_e_all2);
                rlTixian.setBackgroundResource(R.color.white);
                rlTixian.setBackgroundResource(R.drawable.shape_editext_line);
                rlTuikuan.setBackgroundResource(R.drawable.shape_yu_e_tuikuan1);
                tvyue.setTextColor(getResources().getColor(R.color.textcolor));
                tvTixian.setTextColor(getResources().getColor(R.color.textcolor));
                tv_Tuikuan.setTextColor(getResources().getColor(R.color.white));
                break;
        }
    }
    private List<YuEFanLiBean.InfoBean> info = new ArrayList<>();
    /**返利获取数据
     */
    private void netWorking(String url) {

        OkGo.get(url)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                            wch(s);
                        Gson gson = new Gson();
                        YuEFanLiBean yuEFanLiBean = gson.fromJson(s, YuEFanLiBean.class);
                        if (yuEFanLiBean.getCode() == 200) {
                            ll_progress.setVisibility(View.GONE);
                            info.addAll(yuEFanLiBean.getInfo());

                                adabter.notifyDataSetChanged();
                        }else{
                            toast(yuEFanLiBean.getMessage());
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        toast("网络连接失败...");
                        adabter = new YuEAdpater(ctx, info);
                        rclDate.setAdapter(adabter);
                    }
                });

    }
}
