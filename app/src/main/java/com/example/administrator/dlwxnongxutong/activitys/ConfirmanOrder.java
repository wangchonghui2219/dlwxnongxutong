package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;

/**
 * @作者 wch
 * @create at 2017/1/16 0016 下午 5:12
 * @name 确认订单
 */
public class ConfirmanOrder extends BaseActiVity implements View.OnClickListener {


    private TextView toolbar_title;
    private Toolbar tb_toolbar;
    private Button btn_submit;

    @Override
    public void initView() {
        setContentView(R.layout.activity_confirman_order);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setOnClickListener(this);
        tb_toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        tb_toolbar.setOnClickListener(this);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void initData() {
        tb_toolbar.setTitle("");
        setSupportActionBar(tb_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        toolbar_title.setText("确认订单");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }

        return true;
    }


    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.btn_submit:
              Intent intent = new Intent(ctx,PayActivity.class);
              startActivity(intent);
              break;
      }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirman_order);
        initView();


    }
}
