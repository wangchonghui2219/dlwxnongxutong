package com.example.administrator.dlwxnongxutong.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.InfoBean;
import com.example.administrator.dlwxnongxutong.utils.AuteCodeTime;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
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
 * @create at 2017/1/12 0012 下午 4:24
 * @name 找回密码
 */
public class FindPasActivity extends BaseActiVity {


    TextView mToolbarTitle;

    Toolbar mTbToolbar;
    @BindView(R.id.btn_autecode)
    Button mBtnAutecode;
    @BindView(R.id.btn_confirm)
    Button mBtnConfirm;
    private EditText et_phonenumber;
    private EditText et_autecode;
    private EditText et_password;
    private EditText et_confirspas;



    @Override
    public void initView() {
        setContentView(R.layout.activity_find_pas);
        ButterKnife.bind(this);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mTbToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        et_phonenumber = (EditText) findViewById(R.id.et_phonenumber);

        et_autecode = (EditText) findViewById(R.id.et_autecode);

        et_password = (EditText) findViewById(R.id.et_password);

        et_confirspas = (EditText) findViewById(R.id.et_confirspas);

    }

    @Override
    public void initData() {
        mTbToolbar.setTitle("");
        setSupportActionBar(mTbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        mToolbarTitle.setText("找回密码");
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

    @OnClick({R.id.btn_autecode, R.id.btn_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_autecode: //验证码
                if (AuteCodeTime.tag == 0) {
                    getAutecode();
                }

                break;
            case R.id.btn_confirm: //确定
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String phonenumber = et_phonenumber.getText().toString().trim();
        if (TextUtils.isEmpty(phonenumber)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String autecode = et_autecode.getText().toString().trim();
        if (TextUtils.isEmpty(autecode)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String confirspas = et_confirspas.getText().toString().trim();
        if (TextUtils.isEmpty(confirspas)) {
            Toast.makeText(this, "请输入确认密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        OkGo.post(NetUtils.Find_PWD)
                .params("phe",phonenumber)
                .params("pwd",password)
                .params("ppwd",confirspas)
                .params("code",autecode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        wch(s);

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });

    }
    /**
     * 获取验证码
     */
    public void getAutecode() {
        // validate
        String phonenumber = et_phonenumber.getText().toString().trim();
        if (TextUtils.isEmpty(phonenumber)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.get(NetUtils.AUTE_CODE)
                .params("phe",phonenumber)
                .params("state",1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        wch(s);
                        Gson gson = new Gson();
                        InfoBean infoBean = gson.fromJson(s, InfoBean.class);
                        if (infoBean.code == 200) {
                            AuteCodeTime.setTime(mBtnAutecode);
                        }else{
                            toast(infoBean.message);
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
}
