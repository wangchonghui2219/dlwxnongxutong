package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.adabter.AdminPopuAdapter;
import com.example.administrator.dlwxnongxutong.adabter.PopuAdapter;
import com.example.administrator.dlwxnongxutong.adabter.SettingAdapter;
import com.example.administrator.dlwxnongxutong.bean.AdminListBean;
import com.example.administrator.dlwxnongxutong.bean.BankManageBean;
import com.example.administrator.dlwxnongxutong.bean.DisTypeBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.alipay.sdk.app.statistic.c.s;
import static com.alipay.sdk.app.statistic.c.w;


/**
 * @作者 wch
 * @create at 2017/1/11 0011 上午 11:33
 * @name 设置界面
 */
public class SettingActivity extends BaseActiVity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar mTbToolbar;
    @BindView(R.id.btn_logout)
    Button btnLogout;

    private ListView lv_setting;
    private SettingAdapter mAdapter;
    private SharedPreferences sp;


    @Override
    public void initView() {
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        lv_setting = (ListView) findViewById(R.id.lv_setting);

        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);
        String adminid = sp.getString(SPUtils.Adminid, "");
        mAdapter = new SettingAdapter(ctx,adminid);

        lv_setting.setAdapter(mAdapter);
        mToolbarTitle.setText("设置");
    }

    @Override
    public void initData() {
        mTbToolbar.setTitle("");
        setSupportActionBar(mTbToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);

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
        btnLogout.setOnClickListener(this);
        lv_setting.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private Intent mIntent;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mIntent = new Intent(ctx, ChangePasActivity.class);
                        mIntent.putExtra("changepas", "changepas");
                        startActivity(mIntent);
                        break;
                    case 1:
                        mIntent = new Intent(ctx, ChangePasActivity.class);

                        mIntent.putExtra("changepas", "pas");
                        startActivity(mIntent);
                        break;
                    case 2:
                        netWorking();
                        break;
                }
            }


        });
    }
    @Override
    public void onClick(View v) {
         switch (v.getId()){
                    case R.id.btn_logout:
                        sp.edit().putString(SPUtils.USER_ID,"").commit();
                        Intent intent = new Intent(ctx,LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
    }

    /**
     * 联网获取绑定列表
     */
    private void netWorking() {
        OkGo.get(NetUtils.AddminIdList)
               .execute(new StringCallback() {
                   @Override
                   public void onSuccess(String s, Call call, Response response) {
                        wch(s);
                       Gson gson = new Gson();
                       AdminListBean adminListBean = gson.fromJson(s, AdminListBean.class);
                       if (adminListBean.getCode() == 200) {
                           List<AdminListBean.InfoBean> info = adminListBean.getInfo();
                            showPopuWindow(info);
                       }else{
                           toast(adminListBean.getMessage());
                       }

                   }

                   @Override
                   public void onError(Call call, Response response, Exception e) {
                       super.onError(call, response, e);
                       toast("网络连接失败");
                   }
               });
    }
    /**
     * 显示配送方式窗体
     */
    private void showPopuWindow(final List<AdminListBean.InfoBean> info) {
        View view = View.inflate(ctx,R.layout.item_popu_distype,null);
        final PopupWindow window = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ColorDrawable drawablw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(drawablw);
        window.setBackgroundDrawable(drawablw);
        final ListView lv_date = (ListView) view.findViewById(R.id.lv_list);
        final RelativeLayout rl_popu = (RelativeLayout) view.findViewById(R.id.rl_popu);
        final AdminPopuAdapter madapter = new AdminPopuAdapter(ctx,info);
        lv_date.setAdapter(madapter);
        window.showAtLocation(findViewById(R.id.activity_setting), Gravity.BOTTOM|Gravity.CENTER,0,0);
        lv_date.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdminListBean.InfoBean infoBean = info.get(position);
                String adminid = infoBean.getAdminid();
                window.dismiss();
                upAdminid(adminid);

            }


        });
        rl_popu.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = rl_popu.findViewById(R.id.rl_popu).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y>height){
                        window.dismiss();
                    }
                }
                return true;
            }
        });
    }

    /**
     * 上传管理员id
     */
    private void upAdminid(String adminid) {
        OkGo.post(NetUtils.UpAdminId)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .params("adminid",adminid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                            wch(s);
                        Gson gson = new Gson();
                        BankManageBean bankManageBean = gson.fromJson(s, BankManageBean.class);
                        if (bankManageBean.getCode() == 200) {
                                      toast(bankManageBean.getMessage());
                            toast("如需更换请重新选择，下次进来将会消失");
                            BankManageBean.InfoBean info = bankManageBean.getInfo();
                            sp.edit().putString(SPUtils.Adminid,info.getAdminid()).commit();
                        }else{
                            toast(bankManageBean.getMessage());
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        toast("网络不好，请重新选择");
                    }
                });
    }
}
