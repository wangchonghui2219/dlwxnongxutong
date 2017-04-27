package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.InfoBean;
import com.example.administrator.dlwxnongxutong.bean.Register;
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
 * @name 注册
 */
public class RegisterActivity extends BaseActiVity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar mTbToolbar;
    @BindView(R.id.btn_autecode)
    Button mBtnAutecode;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.ll_aggrement)
    LinearLayout mLlAggrement;
    private EditText et_phonenumber;
    private EditText et_autecode;
    private EditText et_password;
    private EditText et_paypas;
    private TextView tv_protocol;

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        et_phonenumber = (EditText) findViewById(R.id.et_phonenumber);
        et_phonenumber.setOnClickListener(this);
        et_autecode = (EditText) findViewById(R.id.et_autecode);
        et_autecode.setOnClickListener(this);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password.setOnClickListener(this);
        et_paypas = (EditText) findViewById(R.id.et_paypas);
        et_paypas.setOnClickListener(this);
        tv_protocol = (TextView) findViewById(R.id.tv_protocol);
        tv_protocol.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mTbToolbar.setTitle("");
        setSupportActionBar(mTbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        mToolbarTitle.setText("注册");
    }

    @Override
        public void initListener() {

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

    @OnClick({R.id.btn_autecode, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_autecode: //获取验证码
                if (AuteCodeTime.tag == 0) {
                    getAutecode();
                }
                break;
            case R.id.btn_register://注册
                submit();
                break;
            case R.id.tv_protocol://农牧通协议
                Intent intent = new Intent(ctx, ProdocolActivity.class);
                startActivity(intent);
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

        String paypas = et_paypas.getText().toString().trim();
        if (TextUtils.isEmpty(paypas)) {
            Toast.makeText(this, "请输入支付密码", Toast.LENGTH_SHORT).show();
            return;
        }


        OkGo.post(NetUtils.REGISTER)
                .params("phe",phonenumber)
                .params("code",autecode)
                .params("pwd",password)
                .params("paypwd",paypas)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        wch(s);
                        Gson gson = new Gson();
                        Register register = gson.fromJson(s, Register.class);
                        if (register.code == 200) {
                            Toast.makeText(ctx,register.message,Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(ctx,register.message,Toast.LENGTH_SHORT).show();
                        }
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
