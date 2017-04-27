package com.example.administrator.dlwxnongxutong.activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.LoginBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.example.administrator.dlwxnongxutong.R.mipmap.icon_fanhui;

public class LoginActivity extends BaseActiVity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar mTbToolbar;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_forgetpas)
    TextView mTvForgetpas;
    private EditText et_phonenumber;
    private EditText et_password;
    private MenuItem mMenuright;
    private SharedPreferences sp;

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);
        ButterKnife.bind(this);
        et_phonenumber = (EditText) findViewById(R.id.et_phonenumber);

        et_password = (EditText) findViewById(R.id.et_password);

    }

    @Override
    public void initData() {
        mTbToolbar.setTitle("");
        mToolbarTitle.setText("登录");
        setSupportActionBar(mTbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(icon_fanhui);
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
                    case R.id.toolbar_register:
                        Intent intent = new Intent(ctx,RegisterActivity.class);
                        startActivity(intent);
                        break;
               }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_register,menu);
        mMenuright = menu.findItem(R.id.toolbar_register);
        return true;
    }

    @OnClick({R.id.btn_login, R.id.tv_forgetpas})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                submit();
                break;
            case R.id.tv_forgetpas:
                Intent intent = new Intent(ctx,FindPasActivity.class);

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

        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

//        // TODO validate success, do something
//
        OkGo.post(NetUtils.LOGIN)
                .params("phe",phonenumber)
                .params("pwd",password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, okhttp3.Response response) {
                        wch(s);
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(s, LoginBean.class);
                        if (loginBean.code == 200) {
                            sp.edit().putString(SPUtils.USER_ID,loginBean.info.user_id).commit();
                            sp.edit().putString(SPUtils.USER_PHONE,loginBean.info.userphe).commit();
                            sp.edit().putString(SPUtils.USER_PHOTO,loginBean.info.user_photo).commit();
                            sp.edit().putString(SPUtils.USER_name,loginBean.info.username).commit();
                            sp.edit().putString(SPUtils.USER_ACCOUNT,loginBean.info.account).commit();
                            sp.edit().putString(SPUtils.Adminid,loginBean.info.adminid).commit();
                            sp.edit().putInt(SPUtils.Integral,loginBean.info.integral).commit();
                            finish();
                        }
                        toast(loginBean.message);
                    }

                    @Override
                    public void onError(Call call, okhttp3.Response response, Exception e) {
                        Toast.makeText(ctx, "网络不好，请检查网络连接", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
