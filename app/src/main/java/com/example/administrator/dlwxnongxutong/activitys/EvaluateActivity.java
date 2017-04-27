package com.example.administrator.dlwxnongxutong.activitys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.InfoBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.alipay.sdk.app.statistic.c.s;
import static com.alipay.sdk.app.statistic.c.w;

public class EvaluateActivity extends BaseActiVity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private SharedPreferences sp;
    private String good_id;

    @Override
    public void initView() {
        good_id = getIntent().getStringExtra("good_id");
        setContentView(R.layout.activity_evaluate);

        ButterKnife.bind(this);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);
    }

    @Override
    public void initData() {
        tbToolbar.setTitle("");
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        toolbarTitle.setText("评价");
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



    @OnClick(R.id.btn_submit)
    public void onClick() {
        String message = editText.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            toast("留言内容不能为空！！！");
            return;
        }
        OkGo.post(NetUtils.Order_evaluate)
                .params("user_id",sp.getString(SPUtils.USER_ID,""))
                .params("good_id",good_id)
                .params("rate_info",message)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call cal, Response response) {
                        wch(s);
                        Gson gson = new Gson();
                        InfoBean infoBean = gson.fromJson(s, InfoBean.class);
                        if (infoBean.code == 200) {
                            editText.setHint("请输入留言...");
                        }
                        toast(infoBean.message);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        toast("网络连接失败");
                    }
                });
    }
}
