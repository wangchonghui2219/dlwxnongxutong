package com.example.administrator.dlwxnongxutong.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activitys.BaseActiVity;
import com.example.administrator.dlwxnongxutong.activitys.MainActivity;
import com.example.administrator.dlwxnongxutong.adabter.PopuAdapter;
import com.example.administrator.dlwxnongxutong.adabter.SeachAdapter;
import com.example.administrator.dlwxnongxutong.adabter.SearchPopuAdapter;
import com.example.administrator.dlwxnongxutong.bean.AllGoodsBean;
import com.example.administrator.dlwxnongxutong.bean.NewAddressBean;
import com.example.administrator.dlwxnongxutong.bean.NewProductBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

import static android.R.attr.x;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.os.Build.VERSION_CODES.M;
import static com.alipay.sdk.app.statistic.c.s;
import static com.example.administrator.dlwxnongxutong.R.id.all;
import static com.example.administrator.dlwxnongxutong.R.id.et_seach;
import static com.example.administrator.dlwxnongxutong.R.id.rlv_seach;
import static com.example.administrator.dlwxnongxutong.R.id.tb_toolbar;

/**
 * Created by scy on 2017/1/10.
 * 搜索结果页
 */
public class SearchResultActivity extends BaseActiVity {


    @BindView(tb_toolbar)
    Toolbar tbToolbar;
    private RelativeLayout rl_zonghe, rl_xiaoliang, rl_price;
    private LinearLayout ll_fenlei;
    RecyclerView rlv_seach;
    private String searchContent;
    //弹出PopupWindow时背景变暗
    private View darkView;
    private List<NewProductBean.InfoBean> info = new ArrayList<>();
    private List<String> paisxu = new ArrayList<>();
    private String classid;
    private EditText et_seach;
    private ImageView iv_seach;
    private SeachAdapter adapter;
    private LinearLayout ll_progress;
    @Override
    public void initView() {
        setContentView(R.layout.activity_searchresults);
        ButterKnife.bind(this);
        rlv_seach = (RecyclerView) findViewById(R.id.rlv_seach);
        searchContent = getIntent().getStringExtra(SearchManager.QUERY);
        classid = getIntent().getStringExtra("classid");
        if (TextUtils.isEmpty(classid)) {

            classid = getIntent().getStringExtra("gcid");
        }
        rl_zonghe = (RelativeLayout) findViewById(R.id.rl_zonghe);
        rl_xiaoliang = (RelativeLayout) findViewById(R.id.rl_xiaoliang);
        rl_price = (RelativeLayout) findViewById(R.id.rl_price);
        ll_fenlei = (LinearLayout) findViewById(R.id.ll_fenlei);
        et_seach = (EditText) findViewById(R.id.et_seach);
        iv_seach = (ImageView) findViewById(R.id.iv_seach);
        ll_progress = (LinearLayout) findViewById(R.id.ll_progress);
        et_seach.setText(searchContent);
        iv_seach.setOnClickListener(this);
    }

    @Override
    protected void onResume() {

        getAllGoods();

        super.onResume();
    }

    @Override
    public void initData() {
        tbToolbar.setTitle("");
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);

        GridLayoutManager manager = new GridLayoutManager(ctx, 2, LinearLayoutManager.VERTICAL, false);
        rlv_seach.setLayoutManager(manager);
        manager.getItemCount();
        adapter = new SeachAdapter(ctx, info);
        rlv_seach.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_seachright, menu);
        menu.findItem(R.id.item_seach_right);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_seach_right:
                Intent intent = new Intent(ctx, MainActivity.class);
                intent.putExtra("seach", "搜索");
                startActivity(intent);
                break;
            case android.R.id.home:
                finish();

                break;
        }

        return true;
    }
    private boolean loading = false;
    @Override
    public void initListener() {
        rl_zonghe.setOnClickListener(this);
        rl_xiaoliang.setOnClickListener(this);
        rl_price.setOnClickListener(this);
        rlv_seach.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int totalItemCount = layoutManager.getItemCount();

                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (!loading && totalItemCount < (lastVisibleItem + 3)) {
                    new ArticleTask().execute();
                    loading = true;

                }
            }
        });
    }
    /**
     * 上拉加载
     */
    private class ArticleTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(100);
                Log.i("wch","下拉刷新");
                page++;
                getAllGoods();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            loading = false;

            adapter.notifyDataSetChanged();

        }
    }
    private int tag = 0;//1销量，2价格
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_zonghe://综合
                    unit = 0;
                    sale = 0;
                info.clear();
                getAllGoods();
                break;
            case R.id.rl_xiaoliang:
                paisxu.clear();
                tag = 1;
                paisxu.add("从低到高");
                paisxu.add("从高到低");
                showPopu();
                break;

            case R.id.rl_price:
                paisxu.clear();
                tag = 2;
                paisxu.add("从低到高");
                paisxu.add("从高到低");
                showPopu();
                break;
            case R.id.iv_seach:
                searchContent = et_seach.getText().toString().toString().trim();
                if (!TextUtils.isEmpty(searchContent)) {
                    getAllGoods();
                }
                break;
        }
    }

    /**
     * 显示搜索的窗体
     */
    private void showPopu() {
        View view = View.inflate(ctx, R.layout.item_popu, null);
         final LinearLayout item_popu = (LinearLayout) view.findViewById(R.id.item_popu);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setFocusable(true);
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);  //设置动画
        //因为ppw提供了在某个控件下方的方法，所以有些时候需要直接定位在下方时并不用上面的这个方法
        popupWindow.showAsDropDown(ll_fenlei,Gravity.CENTER|Gravity.BOTTOM,0);    // 以触发弹出窗的view为基准，出现在view的正下方，弹出的pop_view左上角正对view的左下角  偏移量默认为0,0
//        popupWindow.showAtLocation(ll_fenlei, Gravity.CENTER_VERTICAL,0,0);
        backgroundAlpha(0.5f);

        //添加pop窗口关闭事件
        popupWindow.setOnDismissListener(new poponDismissListener());
        ListView lv_popu = (ListView) view.findViewById(R.id.lv_popu);
        SearchPopuAdapter adapter = new SearchPopuAdapter(ctx,paisxu);
                lv_popu.setAdapter(adapter);
        item_popu.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int height = item_popu.findViewById(R.id.item_popu).getBottom();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
            lv_popu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    page = 1;
                    info.clear();
                   if (tag == 1) {//销量

                       unit = 0;
                       if (position == 0) {//低到高
                           sale = 1;
                       }else{
                           sale = 2;
                       }
                    }else{//价格
                         sale = 0;
                       if (position == 0) {//低到高
                           unit = 1;
                       }else{
                           unit = 2;
                       }
                   }
                    popupWindow.dismiss();
                   getAllGoods();
                }
            });
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     * @author cg
     *
     */
    class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    ll_progress.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    /**
     * 获得全部商品
     */
    private int page=1;
    private int sale;//销量（低到高1，高到低2）
    private int unit;//价格（低到高1，高到低2）
    private int sels;//是否热销（1否2是）
    private int ptes;//是否推荐（1否2是）
    private int bid;//商家id
    public void getAllGoods() {
            handler.sendEmptyMessage(1);
        Log.i("wch",classid+"分类ID;"+page+"页码;"+sale+"销量;"+unit+"价格;"+searchContent+"搜索内容;");
        OkGo.get(NetUtils.ALL_GOODS)
                .params("classid",classid)
                .params("page", page)
                .params("search",searchContent)
                .params("sale", sale)
                .params("unit", unit)
                .params("sels", sels)
                .params("ptes", ptes)
                .params("bid", bid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        ll_progress.setVisibility(View.GONE);
                        wch(s);
                        Gson gson = new Gson();
                        NewProductBean allGoodsBean = gson.fromJson(s, NewProductBean.class);
                        if (allGoodsBean.getCode() == 200) {
                            info.addAll(allGoodsBean.getInfo());
                                adapter.notifyDataSetChanged();
                        } else {
                            toast(allGoodsBean.getMessage());
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        ll_progress.setVisibility(View.GONE);
                    }
                });
    }
}
