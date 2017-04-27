package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.adabter.AffrmDingdanAdapter;
import com.example.administrator.dlwxnongxutong.adabter.BnakListAdapter;
import com.example.administrator.dlwxnongxutong.bean.BankListBean;
import com.example.administrator.dlwxnongxutong.bean.InfoBean;
import com.example.administrator.dlwxnongxutong.listener.MonyEdiTextListner;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.alipay.sdk.app.statistic.c.B;
import static com.alipay.sdk.app.statistic.c.c;
import static com.alipay.sdk.app.statistic.c.s;


public class TiXianActivity extends BaseActiVity {

    private TextView toolbar_title;
    private Toolbar tb_toolbar;
    private EditText et_name;
    private TextView et_cartnumber;
    private ImageView iv_xiala;
    private Button btn_add;
    private EditText et_cartname;
    private TextView tv_mony;
    private TextView tv_shouxufei;
    private TextView tv_shimony;
    private Button btn_queren;
    private RelativeLayout rl_selete_cart;
    private SharedPreferences sp;
    private LinearLayout ll_main;
    private ListView lv_bank;
    private EditText et_money;
    @Override
    public void initView() {
        setContentView(R.layout.activity_ti_xian);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        tb_toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        et_name = (EditText) findViewById(R.id.et_name);
        et_cartnumber = (TextView) findViewById(R.id.et_cartnumber);
        iv_xiala = (ImageView) findViewById(R.id.iv_xiala);
        btn_add = (Button) findViewById(R.id.btn_add);
        et_cartname = (EditText) findViewById(R.id.et_cartname);
        tv_mony = (TextView) findViewById(R.id.tv_mony);
        tv_shouxufei = (TextView) findViewById(R.id.tv_shouxufei);
        tv_shimony = (TextView) findViewById(R.id.tv_shimony);
        btn_queren = (Button) findViewById(R.id.btn_queren);
        rl_selete_cart = (RelativeLayout) findViewById(R.id.rl_selete_cart);
        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        et_money = (EditText) findViewById(R.id.et_money);
        btn_add.setOnClickListener(this);
        btn_queren.setOnClickListener(this);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);
    }

    @Override
    public void initData() {
        tb_toolbar.setTitle("");
        setSupportActionBar(tb_toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        rl_selete_cart.setOnClickListener(this);
        et_money.addTextChangedListener(new MonyEdiTextListner(tv_shimony,tv_shouxufei));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                Intent intent = new Intent(ctx,AddBankCardActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_queren:
                    submit();
                break;
            case R.id.rl_selete_cart:

                getBankDate();

                break;
        }
    }



    private void submit() {

        String cartnumber = et_cartnumber.getText().toString().trim();
        if (TextUtils.isEmpty(cartnumber)) {
            Toast.makeText(this, "银行卡号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String money = et_money.getText().toString().trim();
        if (TextUtils.isEmpty(money)) {
            Toast.makeText(this, "提现金额不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        float f   =   Float.parseFloat(money);
         float st = f*(0.85f);
        // TODO validate success, do something
        OkGo.post(NetUtils.UserWithDraw)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .params("bankid",bank_id)
                .params("txtotal",f)
                .params("sjtotal",st)
                .params("sxtotal",st)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        wch(s);
                        Gson gson = new Gson();
                        InfoBean infoBean = gson.fromJson(s, InfoBean.class);
                        toast(infoBean.message);
                        et_money.setText("");
                        et_cartnumber.setText("");
                        et_money.setHint("请输入提现金额");
                        tv_shimony.setText("00.00元");
                        tv_shouxufei.setText("0.00元(折算率15%)");
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        toast("网络连接失败！！");
                    }
                });


    }
    private String bank_card;
    private String bank_id;
    /**
     * 展示获取到的银行卡列表
     */
    private void showPopu(final List<BankListBean.InfoBean> info) {
        View view = View.inflate(ctx,R.layout.item_bank_list,null);
        final PopupWindow popu  = new PopupWindow( view, LinearLayout.LayoutParams.MATCH_PARENT, getWindowManager().getDefaultDisplay().getHeight()/3);
        popu.setFocusable(true);
        ColorDrawable drawable = new ColorDrawable(0xb0000000);
        popu.setBackgroundDrawable(drawable);
        popu.showAtLocation(ll_main, Gravity.CENTER|Gravity.BOTTOM,0,0);
        lv_bank = (ListView) view.findViewById(R.id.lv_bank);
        BnakListAdapter adapter = new BnakListAdapter(ctx,info);
        lv_bank.setAdapter(adapter);
        lv_bank.setOnItemClickListener(new AdapterView.OnItemClickListener() {




            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bank_card = info.get(position).getBank_card();
                bank_id = info.get(position).getBank_id();
                String substring = bank_card.substring(0, 5);
                String substring1 = bank_card.substring(bank_card.length() - 4, bank_card.length() - 1);
                et_cartnumber.setText(substring+"******"+substring1);
                popu.dismiss();
            }
        });
    }

    /**
     * 连网获得银行卡列表
     */
    public void getBankDate() {
        OkGo.get(NetUtils.Bank_List)
                .params("user_id",sp.getString(SPUtils.USER_ID,""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                            wch(s);
                        Gson gson = new Gson();
                        BankListBean bankListBean = gson.fromJson(s, BankListBean.class);
                        if (bankListBean.getCode() == 200) {
                            List<BankListBean.InfoBean> info = bankListBean.getInfo();
                            showPopu(info);

                        }else{
                               toast(bankListBean.getMessage());
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                       super.onError(call, response, e);
                        toast("网络连接失败..");
                    }
                });
    }
}
