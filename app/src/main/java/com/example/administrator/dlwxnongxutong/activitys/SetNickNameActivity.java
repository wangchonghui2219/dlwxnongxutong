package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * @作者 wch
 * @create at 2017/1/12 0012 上午 10:08
 * @name 设置昵称
 */
public class SetNickNameActivity extends BaseActiVity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar mTbToolbar;
    @BindView(R.id.ed_nikename)
    EditText mEdNikename;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    private String mName;
    private String mHint;
    private String content;
    private SharedPreferences sp;
    private String nikename;


    @Override
    public void initView() {
        Intent intent = getIntent();
        mName = intent.getStringExtra("Name");
        mHint = intent.getStringExtra("hint");
        content = intent.getStringExtra("content");
        setContentView(R.layout.activity_set_nick_name);
        ButterKnife.bind(this);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);
    }

    @Override
    public void initData() {
        mTbToolbar.setTitle("");
       setSupportActionBar(mTbToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarTitle.setText(mName);
        mEdNikename.setHint(mHint);
        mEdNikename.setText(content);
    }

    @Override
    public void initListener() {

    }

    /**
     * ToolBar点击
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
                   case android.R.id.home:
                       finish();
                   break;

               }
        return true;
    }
    @OnClick(R.id.btn_save)
    public void onClick() {
        submit();
    }
    /**
     * 判断是否为空
     */
    private void submit() {
        // validate
        nikename = mEdNikename.getText().toString().trim();
        if (TextUtils.isEmpty(nikename)) {
            Toast.makeText(this, mName+"不能为空", Toast.LENGTH_SHORT).show();
            return;
        }


        if (mName.equals("昵称")) {
            upData(nikename,"username");
        }else if (mName.equals("手机号")){
// TODO validate success, do something
        }

    }

    /**
     * 上传修改完成的昵称
     * @param nikename
     */
    private void upData(String nikename,String key) {
        OkGo.post(NetUtils.CHANGE_NICKNAME)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .params(key,nikename)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                            gsonJson(s);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
    /**
     * 解析修改结果
     * @param s
     */
    private void gsonJson(String s) {
        wch(s);
        Gson gson = new Gson();
        InfoBean infoBean = gson.fromJson(s,InfoBean.class);
        if (infoBean.code == 200) {
            Toast.makeText(ctx,infoBean.message,Toast.LENGTH_SHORT).show();
                    finish();
            if (mName.equals("昵称")) {
               sp.edit().putString(SPUtils.USER_name,nikename).commit();
            }else if (mName.equals("手机号")){
                sp.edit().putString(SPUtils.USER_PHONE,nikename).commit();
            }
        }else{
            Toast.makeText(ctx,infoBean.message,Toast.LENGTH_SHORT).show();
        }
    }

}
