package com.example.administrator.dlwxnongxutong.activitys;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.fragments.GoodsFragment;
import com.example.administrator.dlwxnongxutong.fragments.HomeFragment;
import com.example.administrator.dlwxnongxutong.fragments.ShoppingCatFragment;
import com.example.administrator.dlwxnongxutong.fragments.UserMesFragment;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @作者 wch
 * @create at 2017/1/7 0007 上午 11:05
 * @name 主界面
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    /**
     * fragment集合
     */
    private List<Fragment> fragments = new ArrayList<>();
    private FrameLayout rl_layout;
    private RadioButton rb_home;
    private RadioButton rb_merchant;
    private RadioButton rb_usermsg;
    private RadioGroup rg_main;
    private Context ctx;
    private FrameLayout fl_count;
    private RadioButton rb_shoppingcat;
    private Toolbar tb_toolbar;
    private TextView toolbar_title;
    private MenuItem daidingItem;
    private MenuItem searchItem;
    private MenuItem mItem;
    private String seach;
    private RelativeLayout rlv_home;
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        seach = getIntent().getStringExtra("seach");
        setContentView(R.layout.activity_main);
        ctx = this;
        initView();
        initData();
        initListener();
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);
        sp.edit().putInt(SPUtils.FIRST,1).commit();
    }

    /**
     * 初始化控件
     */
    private void initView() {

        //toolbar
        tb_toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        tb_toolbar.setTitle("");
        setSupportActionBar(tb_toolbar);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("首页");

        rg_main = (RadioGroup) findViewById(R.id.rg_main);

        rb_home = (RadioButton) findViewById(R.id.rb_home);

        rb_merchant = (RadioButton) findViewById(R.id.rb_merchant);

        rb_usermsg = (RadioButton) findViewById(R.id.rb_usermsg);


        fl_count = (FrameLayout) findViewById(R.id.fl_count);

        rb_shoppingcat = (RadioButton) findViewById(R.id.rb_shoppingcat);

        rlv_home = (RelativeLayout) findViewById(R.id.rlv_home);
    }
    /**
     * 初始化数据
     */
    private void initData() {
        fragments.add(new HomeFragment());
        fragments.add(new GoodsFragment());
        fragments.add(new ShoppingCatFragment());
        fragments.add(new UserMesFragment());
        if ("搜索".equals(seach)){
            changeFragment(2);
        }else{
            changeFragment(0);
        }
    }
    /**
     * 初始化监听
     */
    private void initListener() {
        rg_main.setOnCheckedChangeListener(this);
        rb_home.setChecked(true);
    }
    private Menu menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.toolbar, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.ab_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchItem = menu.findItem(R.id.ab_search);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
//        daidingItem = menu.findItem(R.id.daiding);
        getMenuInflater().inflate(R.menu.toolbar_shopcat,menu);
        mItem = menu.findItem(R.id.toolbar_shopcat);
        mItem.setVisible(false);

        return true;
    }
    public static boolean isCleckmItem = true;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.daiding:
//                Toast.makeText(ctx, "待定", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.toolbar_shopcat:


                break;
        }

        return true;
    }
    private Fragment lastFragment;
    /**
     * 切换fragment
     *
     * @param i
     */
    private void changeFragment(int i) {
        if (i == 2){
            tb_toolbar.setVisibility(View.GONE);
        }else{
            tb_toolbar.setVisibility(View.VISIBLE);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 上一个不为空 隐藏上一个
        if (lastFragment != null) {
            transaction.hide(lastFragment);
        }
        Fragment fragment = fragments.get(i);
        // fragment不能重复添加 // 添加过 显示 没有添加过 就隐藏
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.fl_count, fragment);
        }
        transaction.commit();
        lastFragment = fragment;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // 把这一句注销掉 防止SaveInstanceState保存fragment
        // super.onSaveInstanceState(outState);
    }

    /**
     * 选择fragment
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                changeFragment(0);
                rlv_home.setVisibility(View.VISIBLE);
                tb_toolbar.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.VISIBLE);
                toolbar_title.setText("首页");
//                daidingItem.setVisible(true);
                searchItem.setVisible(true);
                mItem.setVisible(false);
                break;
            case R.id.rb_merchant:
                changeFragment(1);
                toolbar_title.setText("分类");
                rlv_home.setVisibility(View.VISIBLE);
                tb_toolbar.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.VISIBLE);
//                daidingItem.setVisible(false);
                searchItem.setVisible(false);
                mItem.setVisible(false);
                break;
            case R.id.rb_shoppingcat:
                changeFragment(2);
                rlv_home.setVisibility(View.GONE);
                toolbar_title.setText("购物车");
                tb_toolbar.setVisibility(View.GONE);
                toolbar_title.setVisibility(View.GONE);
//                daidingItem.setVisible(false);
                searchItem.setVisible(false);
               mItem.setVisible(true);

                break;
            case R.id.rb_usermsg:

              rlv_home.setVisibility(View.GONE);

//                daidingItem.setVisible(false);
                searchItem.setVisible(false);
                mItem.setVisible(false);
                changeFragment(3);
                break;


        }
    }
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
