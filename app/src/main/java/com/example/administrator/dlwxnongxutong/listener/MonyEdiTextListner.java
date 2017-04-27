package com.example.administrator.dlwxnongxutong.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import static java.lang.Float.parseFloat;

/**
 * 输入完成后显示实际提现的金额
 */
public class MonyEdiTextListner implements TextWatcher {
    private TextView tv_money,tv_shouxufei;
    public MonyEdiTextListner(TextView tv_money,TextView tv_shouxufei) {
        super();
        this.tv_money = tv_money;
        this.tv_shouxufei = tv_shouxufei;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    private String money;
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        money = s.toString().trim();
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (money.length()>0) {
            double f   =   Double.parseDouble(money);
            double st = f*(0.85d);
            tv_money.setText(st+"元");


            tv_shouxufei.setText(f*(0.15d)+"元(折算率12%)");
        }

    }
}
