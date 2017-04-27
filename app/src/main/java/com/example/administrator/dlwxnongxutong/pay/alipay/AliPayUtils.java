package com.example.administrator.dlwxnongxutong.pay.alipay;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


import com.alipay.sdk.app.PayTask;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * 支付宝支付
 * 
 * @author scy
 * 
 */
public class AliPayUtils {

	private static final int SDK_PAY_FLAG = 1;

    public AliPayUtils(Context ctx) {
        super();
        this.ctx = ctx;
    }

    private Context ctx;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		// @SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				Log.i("wch",msg.obj+"");
				PayResult payResult = new PayResult((String) msg.obj);
				/**
				 * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
				 * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
				 * docType=1) 建议商户依赖异步通知
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息
				Log.i("wch", "同步返回需要验证的信息>>" + resultInfo);
				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(ctx, "支付成功", Toast.LENGTH_SHORT).show();
//					sendYanqianRequest(resultInfo);
                    ((Activity)ctx).finish();
				} else {
					// 判断resultStatus 为非"9000"则代表可能支付失败
					// "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(ctx, "支付结果确认中", Toast.LENGTH_SHORT)
								.show();

					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(ctx, "支付失败", Toast.LENGTH_SHORT).show();

					}
				}
				break;
			}
			default:
				break;
			}
		}

	};
	public void pay(final Context context, String gid, String address) {
		ctx = context;
		SharedPreferences sp = ctx.getApplicationContext()
				.getSharedPreferences("LoginMsg", Activity.MODE_PRIVATE);
		String userid = sp.getString("userid", "");
		OkGo.post(NetUtils.PAY_URL)
		.params("user_id", userid)
				.params("goods_id", gid).params("address_id", address)
				.params("pay_type", "0")
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {

					}
				});

	}

	// 将支付宝同步返回信息发送给服务器验签
	protected void sendYanqianRequest(String resultInfo) {
		OkGo.post(NetUtils.CHECKRSASIGN_URL)
				.params("signstr", resultInfo)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {

					}

					@Override
					public void onError(Call call, Response response, Exception e) {
						super.onError(call, response, e);
					}
				});

	}

	protected void parseJson(String response) {
		Gson gson = new Gson();
//		AliPayBean json = gson.fromJson(response, AliPayBean.class);
//		if ("1".equals(json.type)) {
//			alipay(json.info);
//		} else {
//			Toast.makeText(ctx, "服务器繁忙，请稍后重试", Toast.LENGTH_SHORT).show();
//		}

	}

	public void alipay(String sign_order) {

		final String payInfo = sign_order;
		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask((Activity) ctx);

				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo, true);
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();

	}

}
