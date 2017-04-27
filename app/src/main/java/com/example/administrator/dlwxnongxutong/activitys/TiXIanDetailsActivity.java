package com.example.administrator.dlwxnongxutong.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.MyApplition;
import com.example.administrator.dlwxnongxutong.bean.YuEFanLiBean;

public class TiXIanDetailsActivity extends BaseActiVity {

    private TextView tv_orderno;
    private TextView tv_style;
    private TextView tv_time;
    private TextView tv_flprice;
    private TextView tv_jifen;
    private TextView tv_account;
    private YuEFanLiBean.InfoBean infos;
    private Toolbar tb_toolbar;
    private TextView tv_title,tv_style1;
    @Override
    public void initView() {
        setContentView(R.layout.activity_ti_xian_details);
        tv_orderno = (TextView) findViewById(R.id.tv_orderno);
        tv_style = (TextView) findViewById(R.id.tv_style);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_flprice = (TextView) findViewById(R.id.tv_flprice);
        tv_jifen = (TextView) findViewById(R.id.tv_jifen);
        tv_account = (TextView) findViewById(R.id.tv_account);
        tb_toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        tv_title = (TextView) findViewById(R.id.toolbar_title);
        tv_style1 = (TextView) findViewById(R.id.tv_style1);
    }

    @Override
    public void initData() {
        infos = MyApplition.infos;
        tb_toolbar.setTitle("");
        setSupportActionBar(tb_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        if (infos.getFlprice() != null) {
            tv_title.setText("返利详情");
            tv_style.setText("返利");
            tv_time.setText(infos.getFftime());
            tv_flprice.setText(infos.getFlprice());
            tv_style1.setText("返利");
        }else{
            tv_title.setText("提现详情");
            tv_style.setText("提现");
            tv_time.setText(infos.getTxtime());
            tv_flprice.setText(infos.getTx());
            tv_style1.setText("提现");
        }
        tv_account.setText(infos.getAccount());
        tv_orderno.setText(infos.getOrderno());

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

    }
}
