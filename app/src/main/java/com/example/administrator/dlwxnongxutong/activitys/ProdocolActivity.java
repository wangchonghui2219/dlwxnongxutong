package com.example.administrator.dlwxnongxutong.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.utils.LoadWEBUtiles;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;

public class ProdocolActivity extends AppCompatActivity {

    private WebView web_aboutus;
    private ProgressBar progress;
    private Toolbar tb_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodocol);
        initView();
    }

    private void initView() {
        web_aboutus = (WebView) findViewById(R.id.web_aboutus);
        progress = (ProgressBar) findViewById(R.id.progress);
        tb_toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        tb_toolbar.setTitle("");
        setSupportActionBar(tb_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        LoadWEBUtiles utiles = new LoadWEBUtiles(this);
        utiles.setListViewData(NetUtils.User_Prodo,web_aboutus,progress);
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
}
