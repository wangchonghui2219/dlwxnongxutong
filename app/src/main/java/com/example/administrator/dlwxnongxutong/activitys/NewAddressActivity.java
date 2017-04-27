package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.InfoBean;
import com.example.administrator.dlwxnongxutong.bean.NewAddressBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class NewAddressActivity extends BaseActiVity {


    private TextView toolbar_title;
    private Toolbar tb_toolbar;
    private EditText et_name;
    private EditText et_phonenumber;
    private EditText et_addrss;
    private EditText et_detailedaddress;
    private LinearLayout ll_gouxuan;
    private MenuItem mToolbar_right;
    private SharedPreferences sp;
    private CheckBox cb_moren;
    private String tag;
    private String addname;
    private String phe;
    private String address;
    private String remarks;
    private String flag;
    private String address_id;

    @Override
    public void initView() {
        setContentView(R.layout.activity_new_address);
        Intent intent = getIntent();
        tag = intent.getStringExtra("tag");
        addname = intent.getStringExtra("name");
        phe = intent.getStringExtra("phe");
        address = intent.getStringExtra("address");
        remarks = intent.getStringExtra("remarks");
        flag = intent.getStringExtra("flag");
        address_id = intent.getStringExtra("address_id");


        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setOnClickListener(this);
        tb_toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        tb_toolbar.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_name.setOnClickListener(this);
        et_phonenumber = (EditText) findViewById(R.id.et_phonenumber);
        et_phonenumber.setOnClickListener(this);
        et_addrss = (EditText) findViewById(R.id.et_addrss);
        et_addrss.setOnClickListener(this);
        et_detailedaddress = (EditText) findViewById(R.id.et_detailedaddress);
        et_detailedaddress.setOnClickListener(this);
        ll_gouxuan = (LinearLayout) findViewById(R.id.ll_gouxuan);
        ll_gouxuan.setOnClickListener(this);
        cb_moren = (CheckBox) findViewById(R.id.cb_moren);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);




    }

    @Override
    public void initData() {
        tb_toolbar.setTitle("");
        setSupportActionBar(tb_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        toolbar_title.setText("新增收货地址");
        if ("修改".equals(tag)) {


        et_name.setText(addname);
        et_addrss.setText(address);
        et_phonenumber.setText(phe);
        et_detailedaddress.setText(remarks);
        if (flag.equals("0")) {
               cb_moren.setChecked(true);
            isMoren = 0;
        }else{
            isMoren = 1;
            cb_moren.setChecked(false);
        }
        }
    }
    private int isMoren = 1;
    @Override
    public void initListener() {
        cb_moren.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked = true){
                        isMoren = 0;
                }else {
                    isMoren = 1;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_right,menu);
        mToolbar_right = menu.findItem(R.id.toolbar_right);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
                   case android.R.id.home:
                       finish();
                   break;
                   case R.id.toolbar_right://完成
                       submit();
                    break;
               }

        return true;
    }

    private void submit() {
        // validate
        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String phonenumber = et_phonenumber.getText().toString().trim();
        if (TextUtils.isEmpty(phonenumber)) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String addrss = et_addrss.getText().toString().trim();
        if (TextUtils.isEmpty(addrss)) {
            Toast.makeText(this, "地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String detailedaddress = et_detailedaddress.getText().toString().trim();
        if (TextUtils.isEmpty(detailedaddress)) {
            Toast.makeText(this, "详细地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("修改".equals(tag)) {
            OkGo.post(NetUtils.RenewAddress)
                    .params("adid",address_id)
                    .params("cphe",phonenumber)
                    .params("cname",name)
                    .params("caddress",addrss)
                    .params("remarks",detailedaddress)
                    .params("flag" ,isMoren)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                                wch(s);
                            Gson gson = new Gson();
                            InfoBean infoBean = gson.fromJson(s, InfoBean.class);
                            toast(infoBean.message);
                            finish();
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                        }
                    });
        }else {
            // TODO validate success, do something
            OkGo.post(NetUtils.New_ADDRESS)
                    .params("userid", sp.getString(SPUtils.USER_ID, ""))
                    .params("cphe", phonenumber)
                    .params("cname", name)
                    .params("caddress", addrss)
                    .params("remarks", detailedaddress)
                    .params("flag", isMoren)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            wch(s);
                            Gson gson = new Gson();
                            NewAddressBean newAddressBean = gson.fromJson(s, NewAddressBean.class);
                            if (newAddressBean.getCode() == 200) {
                                toast(newAddressBean.getMessage());

                            } else {
                                toast(newAddressBean.getMessage());
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                        }
                    });
        }

    }
}
