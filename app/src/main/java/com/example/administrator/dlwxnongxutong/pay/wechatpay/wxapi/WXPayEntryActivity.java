package com.example.administrator.dlwxnongxutong.pay.wechatpay.wxapi;

/**
 * Created by Administrator on 2017/4/14/014.
 */

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler{

    // private static final String TAG =
    // "MicroMsg.SDKSample.WXPayEntryActivity";
    private IWXAPI api;
    private static final String APP_ID ="";
    private Context ctx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, APP_ID);
        ctx = this;
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                Toast.makeText(ctx, "支付成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(ctx, "支付失败", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        finish();

    }
}
