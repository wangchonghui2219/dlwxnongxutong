package com.example.administrator.dlwxnongxutong.pay.wechatpay;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;


import com.example.administrator.dlwxnongxutong.bean.*;
import com.example.administrator.dlwxnongxutong.bean.WXOrderBean;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


/**
 * Created by Administrator on 2017/4/14/014.
 */

public class WeChatPayUtiles {
    private Context ctx;
    private static String APP_ID = "";
    private IWXAPI wxapi;

    public WeChatPayUtiles(Context ctx) {
        super();
        this.ctx = ctx;
            regToWx();
        }

    /**
     * 注册微信
     */
    private void regToWx() {
        //通过WXAPIFactory工厂，获取IWXAPI的实例
        wxapi = WXAPIFactory.createWXAPI(ctx, APP_ID, true);

        //讲应用的appid注册到微信
        wxapi.registerApp(APP_ID);
    }
    /**
     * 微信支付
     *
     * @param
     */
    public void WXPay(WXOrderBean.InfoBean info) {
        PayReq req = new PayReq();
        req.appId = info.getAppid();
        req.partnerId = info.getPartnerid();
        req.prepayId = info.getPrepayid();
        req.packageValue = "Sign=WXPay";
        req.nonceStr = info.getNoncestr();
        req.timeStamp = String.valueOf(info.getTimestamp());
        req.sign = info.getSign();
        if (wxapi.isWXAppInstalled()) {
            wxapi.sendReq(req);
            ((Activity)ctx).finish();
        } else {

            Toast.makeText(ctx, "没有安装微信", Toast.LENGTH_SHORT).show();
        }
    }
}
