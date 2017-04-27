package com.example.administrator.dlwxnongxutong.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.MyApplition;
import com.example.administrator.dlwxnongxutong.bean.JiFenListBean;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 积分详情
 */
public class JiFenDetailsActivity extends BaseActiVity {

    private TextView tv_orderno;
    private TextView tv_orderprice;
    private TextView tv_reintl;
    private TextView tv_gname;
    private Toolbar toolbar;
    private TextView tv_title;
    private JiFenListBean.InfoBean infoBean;

    @Override
    public void initView() {
        setContentView(R.layout.activity_ji_fen_details);
        tv_orderno = (TextView) findViewById(R.id.tv_orderno);
        tv_orderprice = (TextView) findViewById(R.id.tv_orderprice);
        tv_reintl = (TextView) findViewById(R.id.tv_reintl);
        tv_gname = (TextView) findViewById(R.id.tv_gname);
        toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        tv_title = (TextView) findViewById(R.id.toolbar_title);
    }

    @Override
    public void initData() {
        infoBean = MyApplition.infoBean;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        tv_title.setText("积分详情");
        tv_gname.setText(infoBean.getGname());
        tv_orderno.setText(infoBean.getOrderno());
        tv_orderprice.setText(infoBean.getOrderprice());
        tv_reintl.setText(infoBean.getReintl());
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
