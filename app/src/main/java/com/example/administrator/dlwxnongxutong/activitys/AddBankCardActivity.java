package com.example.administrator.dlwxnongxutong.activitys;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.adabter.PopuWindowAdapter;
import com.example.administrator.dlwxnongxutong.bean.InfoBean;
import com.example.administrator.dlwxnongxutong.bean.SanJiLianDBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.view.View.Y;
import static com.alipay.sdk.app.statistic.c.G;
import static com.alipay.sdk.app.statistic.c.s;
import static com.example.administrator.dlwxnongxutong.activity.MyApplition.jsonObject;
import static com.example.administrator.dlwxnongxutong.activity.MyApplition.sanJiLianDBean;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 添加提现银行卡
 */
public class AddBankCardActivity extends BaseActiVity{

    private TextView toolbar_title;
    private Toolbar tb_toolbar;
    private EditText et_name;
    private EditText et_idcard;
    private EditText et_banknumber;
    private LinearLayout et_province;
    private LinearLayout et_city;
    private LinearLayout et_county;
    private EditText et_bankname;
    private Button btn_queren;
    private LinearLayout   ll_addbank;
    private RecyclerView rcy_popu;
    private SharedPreferences sp;
    private JSONObject object;
    private SanJiLianDBean SanJiLianD;
    private TextView tv_p,tv_c,tv_s;
    private List<SanJiLianDBean.CityList.CBean> c;//
    private List<SanJiLianDBean.CityList> citylist;
    private PopuWindowAdapter adapter;
    private PopupWindow window;

    @Override
    public void initView() {
        setContentView(R.layout.activity_add_bank_card);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        tb_toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        et_name = (EditText) findViewById(R.id.et_name);
        et_idcard = (EditText) findViewById(R.id.et_idcard);
        et_banknumber = (EditText) findViewById(R.id.et_banknumber);
        et_province = (LinearLayout) findViewById(R.id.et_province);
        et_city = (LinearLayout) findViewById(R.id.et_city);
        et_county = (LinearLayout) findViewById(R.id.et_county);
        et_bankname = (EditText) findViewById(R.id.et_bankname);
        btn_queren = (Button) findViewById(R.id.btn_queren);
        tv_p = (TextView) findViewById(R.id.tv_p);
        tv_c = (TextView) findViewById(R.id.tv_c);
        tv_s= (TextView) findViewById(R.id.tv_s);
        ll_addbank = (LinearLayout) findViewById(R.id.ll_addbank);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);
        SanJiLianD = sanJiLianDBean;
        citylist = SanJiLianD.getCitylist();
        Log.i("wch",object+"");
    }

    @Override
    public void initData() {
        tb_toolbar.setTitle("");
            setSupportActionBar(tb_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
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
    private String p;
    private String n;
    private List<SanJiLianDBean.CityList.CBean.aBean> a;
    @Override
    public void initListener() {
        btn_queren.setOnClickListener(this);
        et_province.setOnClickListener(this);
        et_city.setOnClickListener(this);
        et_county.setOnClickListener(this);

    }
    private List<String> names = new ArrayList<>();
    private int tag = 0; //0代表选择省，1代表选择市，2代表选择县区
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_queren:
                submit();
                break;
            case R.id.et_province:
                tag = 0;
                names.clear();
                for (int i = 0;i<citylist.size();i++){

                    names.add(citylist.get(i).getP());
                }

                shopPopuWindow();
                break;
            case R.id.et_city:
                et_county.setVisibility(View.VISIBLE);
                tag = 1;
                names.clear();
                if (c == null) {
                    toast("请先选择省..");
                    return;
                }
                for (int i = 0;i<c.size();i++){

                    names.add(c.get(i).getN());
                }
                shopPopuWindow();
                break;
            case R.id.et_county:
                et_county.setVisibility(View.VISIBLE);
                tag = 2;
                names.clear();
                if (c == null) {
                    toast("请先选择市..");
                    return;
                }
                if (a == null) {

                    return;
                }
                for (int i = 0;i<a.size();i++){

                    names.add(a.get(i).getS());
                }
                shopPopuWindow();
                break;
        }
    }
    private String county;
    private void submit() {
        // validate
        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String idcard = et_idcard.getText().toString().trim();
        if (TextUtils.isEmpty(idcard)) {
            Toast.makeText(this, "身份证号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String banknumber = et_banknumber.getText().toString().trim();
        if (TextUtils.isEmpty(banknumber)) {
            Toast.makeText(this, "银行卡不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(banknumber)) {
            Toast.makeText(this, "开户行不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String bankname = et_bankname.getText().toString().trim();
        if (TextUtils.isEmpty(bankname)) {
            Toast.makeText(this, "银行开户名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO valiate success, do something
        wch(name+idcard+bankname+banknumber+county);
        OkGo.post(NetUtils.Add_bank)
                .params("user_id",sp.getString(SPUtils.USER_ID,""))
                .params("card_holder",name)
                .params("identity_card",idcard)
                .params("bank_name",bankname)
                .params("bank_card",banknumber)
                .params("bank_address",county)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson =  new Gson();
                        wch(s);
                        InfoBean infoBean = gson.fromJson(s, InfoBean.class);
                        if (infoBean.code == 200) {
                            finish();
                        }
                       toast(infoBean.message);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        toast("网络连接失败");
                    }
                });

    }

    /**
     * 显示窗体
     */
    private void shopPopuWindow(){
        View view = View.inflate(ctx,R.layout.item_popu_address,null);
        rcy_popu = (RecyclerView) view.findViewById(R.id.rcy_populist);

        window = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        window.setBackgroundDrawable(dw);
        window.showAtLocation(ll_addbank, Gravity.CENTER|Gravity.BOTTOM,0,0);
        setRecyceviewDate();
    }

    private void setRecyceviewDate() {

        LinearLayoutManager manager = new LinearLayoutManager(ctx,1,false);
        rcy_popu.setLayoutManager(manager);
        adapter = new PopuWindowAdapter(ctx,names);
        rcy_popu.setAdapter(adapter);
        adapter.setOnItemClickListener(new PopuWindowAdapter.OnItemClickListener() {



            @Override
            public void setOnClick(int position) {

                switch (tag){
                    case 0://选择省
                        //选择的省名字
                        p = citylist.get(position).getP();
                        county = "";
                        tv_p.setText(p);
                        tv_c.setText("市");
                        tv_s.setText("县区");
                        c = citylist.get(position).getC();
                        break;
                    case 1://选择市
                        county = "";
                        n = c.get(position).getN();
                        a = c.get(position).getA();
                        tv_c.setText(n);
                        tv_s.setText("县区");
                        if (a == null) {
                            county= p+n;
                            et_county.setVisibility(View.GONE);
                        }
                        break;
                    case 2://选择县区
                        tv_s.setText(a.get(position).getS());
                        county= p+n+a.get(position).getS();
                        break;
                }

                window.dismiss();//关闭窗体
            }
        });

    }
//    /**
//     * 从assert文件夹中获取json数据
//     */
//    private void initJsonData() {
//        try {
//            StringBuffer sb = new StringBuffer();
//            InputStream is = getAssets().open("city.json");//打开json数据
//            byte[] by = new byte[is.available()];//转字节
//            int len = -1;
//            while ((len = is.read(by)) != -1) {
//                sb.append(new String(by, 0, len, "gb2312"));//根据字节长度设置编码
//            }
//            is.close();//关闭流
//            //为json赋值
//            jsonObject = new JSONObject(sb.toString());
//            Log.i("wch",jsonObject+"");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
