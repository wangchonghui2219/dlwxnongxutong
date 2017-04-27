package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.adabter.ShippingAddressAdapter;
import com.example.administrator.dlwxnongxutong.bean.AddressListBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.example.administrator.dlwxnongxutong.activity.MyApplition.infoBean;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 上午 10:58
 * @name 收货地址管理
 */
public class MyshippingaddressActiVIty extends BaseActiVity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar mTbToolbar;
    @BindView(R.id.lv_shippingaddress)
    ListView mLvShippingaddress;
    @BindView(R.id.btn_newaddress)
    Button mBtnNewaddress;
    private SharedPreferences sp;
    private ShippingAddressAdapter adapter;
    private int tag;

    @Override
    public void initView() {
        setContentView(R.layout.activity_myshippingaddress_acti_vity);
        ButterKnife.bind(this);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);
        tag = getIntent().getIntExtra("tag", 0);

    }

    @Override
    public void initData() {
        mTbToolbar.setTitle("");
        setSupportActionBar(mTbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        mToolbarTitle.setText("管理收货地址");

    }

    @Override
    protected void onResume() {
        netWorking();

        super.onResume();
    }

    /**
     * 获取收货地址列表
     */
    private List<AddressListBean.InfoBean> info = new ArrayList<>();
    private void netWorking() {
        OkGo.get(NetUtils.Address_list)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .execute(new StringCallback() {



                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        wch(s);
                        Gson gson = new Gson();
                        AddressListBean addressListBean = gson.fromJson(s, AddressListBean.class);
                        if (addressListBean.getCode() == 200) {
                            info = addressListBean.getInfo();
                            adapter = new ShippingAddressAdapter(ctx,info,sp.getString(SPUtils.USER_ID,""));
                            mLvShippingaddress.setAdapter(adapter);
                        }else{
                            toast(addressListBean.getMessage());
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
                   case android.R.id.home:
                       Intent intent = new Intent(ctx,AffirmDingdanActivity.class);
                       intent.putExtra("addanme","");
                       intent.putExtra("addid","");
                       intent.putExtra("addphone","");
                       intent.putExtra("address","");
                       intent.putExtra("addDetail","");
                       setResult(1,intent);
                       finish();
                   break;

               }
        return true;
    }

    @Override
    public void initListener() {
        mLvShippingaddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tag != 1) {
                    AddressListBean.InfoBean infoBean = info.get(position);
                    Intent intent = new Intent(ctx,AffirmDingdanActivity.class);
                    intent.putExtra("addanme",infoBean.getConsignee_name());
                    intent.putExtra("addid",infoBean.getAddress_id());
                    intent.putExtra("addphone",infoBean.getConsignee_phe());
                    intent.putExtra("address",infoBean.getConsignee_address());
                    intent.putExtra("addDetail",infoBean.getRemarks());
                    setResult(1,intent);
                    finish();
                }
            }
        });
    }
    @OnClick(R.id.btn_newaddress)
    public void onClick() {
        Intent intent = new Intent(ctx,NewAddressActivity.class);
        startActivity(intent);
    }
}
