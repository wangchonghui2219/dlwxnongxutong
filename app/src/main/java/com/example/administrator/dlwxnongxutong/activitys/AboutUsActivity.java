package com.example.administrator.dlwxnongxutong.activitys;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.utils.LoadWEBUtiles;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 关于我们
 */
public class AboutUsActivity extends BaseActiVity {


    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar mTbToolbar;
    @BindView(R.id.web_aboutus)
    WebView mWebAboutus;
    private ProgressBar progress;
    @Override
    public void initView() {
        setContentView(R.layout.activity_about_us);
        progress = (ProgressBar) findViewById(R.id.progress);
        ButterKnife.bind(this);
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
    public void initData() {
        mTbToolbar.setTitle("");
        setSupportActionBar(mTbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        mToolbarTitle.setText("关于我们");
        LoadWEBUtiles utiles = new LoadWEBUtiles(ctx);
        utiles.setListViewData(NetUtils.About_ME,mWebAboutus,progress);
    }

    @Override
    public void initListener() {

    }


}
