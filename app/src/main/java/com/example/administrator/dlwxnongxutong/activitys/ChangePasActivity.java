package com.example.administrator.dlwxnongxutong.activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.InfoBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * @作者 wch
 * @create at 2017/1/11 0011 下午 4:27
 * @name 修改密码
 */

public class ChangePasActivity extends Activity implements View.OnClickListener {
    private EditText et_nowpas;
    private EditText et_newpas;
    private EditText et_newpass;
    private Button btn_confirm;
    private ImageView iv_back;
    private TextView toolbar_title;
    private String mChangepas;
    private SharedPreferences sp;
    private EditText et_phonenumber;
    private Button btn_autecode;
    private EditText et_autecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mChangepas = intent.getStringExtra("changepas");
        setContentView(R.layout.activity_change_pas);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        et_nowpas = (EditText) findViewById(R.id.et_nowpas);
        et_newpas = (EditText) findViewById(R.id.et_newpas);

        btn_confirm = (Button) findViewById(R.id.btn_confirm);

        btn_confirm.setOnClickListener(this);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setOnClickListener(this);
        sp = getSharedPreferences(SPUtils.SP_MODE, MODE_PRIVATE);


        et_phonenumber = (EditText) findViewById(R.id.et_phonenumber);
        et_phonenumber.setOnClickListener(this);
        btn_autecode = (Button) findViewById(R.id.btn_autecode);
        btn_autecode.setOnClickListener(this);
        et_autecode = (EditText) findViewById(R.id.et_autecode);
        et_autecode.setOnClickListener(this);
    }

    public void initData() {
        if (mChangepas.equals("changepas")) {
            toolbar_title.setText("修改密码");
        } else {
            toolbar_title.setText("修改支付密码");
        }
    }


    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm://提交
                submit();
                break;
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.btn_autecode: //获取验证码
                    getVerify();

                break;
        }
    }


    private void submit() {
        // validate
        String nowpas = et_nowpas.getText().toString().trim();
        if (TextUtils.isEmpty(nowpas)) {
            Toast.makeText(this, "输入当前密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String newpas = et_newpas.getText().toString().trim();
        if (TextUtils.isEmpty(newpas)) {
            Toast.makeText(this, "输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String phonenumber = et_phonenumber.getText().toString().trim();
        if (TextUtils.isEmpty(phonenumber)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String autecode = et_autecode.getText().toString().trim();
        if (TextUtils.isEmpty(autecode)) {
            Toast.makeText(this, "输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mChangepas.equals("changepas")) {
            toolbar_title.setText("修改密码");
            upPass(nowpas,newpas,phonenumber,autecode);
        } else {
            toolbar_title.setText("修改支付密码");
            upPayPass(nowpas,newpas,phonenumber,autecode);
        }


    }
    /**
     * 修改支付密码
     * @param nowpas 当前密码
     * @param newpas 新密码
     * @param phonenumber 手机号
     * @param autecode 验证码
     */
    private void upPayPass(String nowpas, String newpas, String phonenumber, String autecode) {
        OkGo.post(NetUtils.CHANGE_PAY_PWD)
                .params("userid", sp.getString(SPUtils.USER_ID, ""))
                .params("cpwd", nowpas)
                .params("pwd",newpas)
                .params("phe",phonenumber)
                .params("code",autecode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("wch",s);
                        gsonJson(s);
                    }



                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });


    }

    /**
     * 修改密码
     * @param nowpas 当前密码
     * @param newpas 新密码
     * @param phonenumber 手机号
     * @param autecode 验证码
     */
    private void upPass(String nowpas,String newpas,String phonenumber,String autecode){
        // TODO validate success, do something
        OkGo.post(NetUtils.CHANGE_PWD)
                .params("userid", sp.getString(SPUtils.USER_ID, ""))
                .params("cpwd", nowpas)
                .params("pwd",newpas)
                .params("phe",phonenumber)
                .params("code",autecode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("wch",s);
                        gsonJson(s);
                        sp.edit().putString(SPUtils.USER_ID,"").commit();
                        Intent intent = new Intent(ChangePasActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

    /**
     * 解析修改密码结果
     * @param s
     */
    private void gsonJson(String s) {
        Gson gson = new Gson();
        InfoBean infoBean = gson.fromJson(s,InfoBean.class);
        if (infoBean.code == 200) {
            Toast.makeText(ChangePasActivity.this,infoBean.message,Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(ChangePasActivity.this,infoBean.message,Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取验证码
     */
    public void getVerify() {

    }
}
