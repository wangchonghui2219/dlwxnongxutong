package com.example.administrator.dlwxnongxutong.activitys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.adabter.PayTypeAdapter;
import com.example.administrator.dlwxnongxutong.bean.ALiOrderBean;
import com.example.administrator.dlwxnongxutong.bean.PayTypeBean;
import com.example.administrator.dlwxnongxutong.bean.WXOrderBean;
import com.example.administrator.dlwxnongxutong.pay.alipay.AliPayUtils;
import com.example.administrator.dlwxnongxutong.pay.wechatpay.WeChatPayUtiles;
import com.example.administrator.dlwxnongxutong.utils.ButtonUtils;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.picasso.RequestHandler;
import okhttp3.Call;
import okhttp3.Response;

import static android.R.attr.value;
import static com.alipay.sdk.app.statistic.c.s;

public class PayActivity extends BaseActiVity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    @BindView(R.id.tv_sum)
    TextView tv_sum;
    private String jsonDate;
    @BindView(R.id.btn_pay)
    Button btn_pay;
    private SharedPreferences sp;
    private String addressId;
    private int isUserjifen;
    @BindView(R.id.lv_pay)
     ListView lv_pay;
    private String way;
    private double price;
    private LinearLayout ll_progress;
    private TextView tv_name;
    @Override
    public void initView() {
        jsonDate = getIntent().getStringExtra("jsonDate");
        addressId = getIntent().getStringExtra("addressId");
        isUserjifen = getIntent().getIntExtra("isUserjifen",0);
        way = getIntent().getStringExtra("way");
        price = getIntent().getDoubleExtra("totalPrice",0.0);
        setContentView(R.layout.activity_pay);
        ll_progress = (LinearLayout) findViewById(R.id.ll_progress);
        tv_name = (TextView) findViewById(R.id.tv_name);
        ButterKnife.bind(this);
        tv_sum.setText("还需支付：￥"+price);

    }

    @Override
    public void initData() {
        toolbarTitle.setText("收银台");
        tbToolbar.setTitle("");
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);
        netWorking();
    }
    private List<PayTypeBean.InfoBean> info;
    private PayTypeAdapter adapter;
    /**
     * 获取支付方式
     */
    private void netWorking() {
        ll_progress.setVisibility(View.VISIBLE);
            OkGo.get(NetUtils.PAY_TYPE)
                    .execute(new StringCallback() {




                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            ll_progress.setVisibility(View.GONE);
                            wch(s);
                            Gson gson = new Gson();
                            PayTypeBean payTypeBean = gson.fromJson(s, PayTypeBean.class);
                            if (payTypeBean.getCode() == 200) {
                                info = payTypeBean.getInfo();
                                adapter = new PayTypeAdapter(ctx, info);
                                lv_pay.setAdapter(adapter);
                                payid = info.get(0).getPayid();
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
                            ll_progress.setVisibility(View.GONE);
                        }
                    });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
            finish();
            break;
            default:
            break;
        }
        return true;
    }

    @Override
    public void initListener() {
            btn_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!ButtonUtils.isFastDoubleClick(R.id.btn_pay,1000)) {
                        tv_name.setText("去支付...");
                        ll_progress.setVisibility(View.VISIBLE);
                        upDate();
                    }



                }
            });
        lv_pay.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PayTypeBean.InfoBean infoBean = info.get(position);
                payid = infoBean.getPayid();
               adapter.setIsCheced(position);
            }
        });

    }
    private int payid;//支付方式id
        /**
     * 提交订单到服务器
    *
     */
    private void upDate() {
        Log.i("wch",jsonDate+";"+sp.getString(SPUtils.USER_ID,"")+";"+addressId+";"+isUserjifen+";"+payid);
        OkGo.post(NetUtils.ORDER_UP)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .params("addid",addressId)
                .params("isuseintl",isUserjifen)
                .params("payid",payid)
                .params("single",jsonDate)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        wch(s);
                        ll_progress.setVisibility(View.GONE);
                        Gson gson = new Gson();
                         switch (payid){

                                    case 1://支付宝
                                        ALiOrderBean aLiOrderBean = gson.fromJson(s, ALiOrderBean.class);
                                        if (aLiOrderBean.getCode() == 200) {
                                            AliPayUtils utils = new AliPayUtils(ctx);
                                            utils.alipay(aLiOrderBean.getInfo());

                                        }else{
                                            Toast.makeText(ctx, aLiOrderBean.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                        break;

                                    case 2://微信
                                        WXOrderBean wxOrderBean = gson.fromJson(s, WXOrderBean.class);
                                        if (wxOrderBean.getCode() == 200) {
                                            WXOrderBean.InfoBean info = wxOrderBean.getInfo();
                                            WeChatPayUtiles utiles = new WeChatPayUtiles(ctx);
                                            utiles.WXPay(info);

                                        }
                                        break;
                                }


                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });


    }

}
