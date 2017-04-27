package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.MyApplition;
import com.example.administrator.dlwxnongxutong.adabter.AffrmDingdanAdapter;
import com.example.administrator.dlwxnongxutong.adabter.PopuAdapter;
import com.example.administrator.dlwxnongxutong.bean.AddressListBean;
import com.example.administrator.dlwxnongxutong.bean.DisTypeBean;
import com.example.administrator.dlwxnongxutong.bean.affrm.AffrmDingdanBean;

import com.example.administrator.dlwxnongxutong.bean.affrm.LisBean;
import com.example.administrator.dlwxnongxutong.bean.affrm.ListBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.InfoBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.example.administrator.dlwxnongxutong.utils.SubmitDingdan;
import com.example.administrator.dlwxnongxutong.views.NoListView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.example.administrator.dlwxnongxutong.R.string.address;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 确认订单界面
 */
public class AffirmDingdanActivity extends BaseActiVity{

    private NoListView eb_list;
    private Toolbar tb_toolbar;
    private Button btn_submit;
    private InputMethodManager manager;
    private Intent intent;
    private  List<InfoBean> info = new ArrayList<>();
    private SharedPreferences sp;
    private TextView tv_addname,tv_address,tv_addpho,tv_addressDetail,tv_zongprice;
    private LinearLayout ll_address;
    private AffrmDingdanAdapter adapter;
    private String odyid;//配送方式ID
    private String gsid;   //规格id
    private CheckBox cb_jifen;
    private String way;
    private int isintpay;
    private Double intlpay;
    private TextView tv_jifen;
    private RelativeLayout rl_jifen;
    private double price;

    private double totalPrice;
    private double vall;
    private String seller;
    private int anInt;
    private double jifenprice;

    @Override
    public void initView() {
        setContentView(R.layout.activity_affirm_dingdan);
        gsid = getIntent().getStringExtra("gsid");
        way = getIntent().getStringExtra("way");
        isintpay = getIntent().getIntExtra("isintpay",0);
        intlpay = getIntent().getDoubleExtra("intlpay",0.0);
        seller = getIntent().getStringExtra("seller");
        totalPrice = getIntent().getDoubleExtra("totalPrice", 0.0);
        tb_toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        eb_list = (NoListView) findViewById(R.id.eb_list);
        tv_addname = (TextView) findViewById(R.id.tv_addname);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_addpho = (TextView) findViewById(R.id.tv_addpho);
        tv_addressDetail = (TextView) findViewById(R.id.tv_addressDetail);
        ll_address = (LinearLayout) findViewById(R.id.ll_address);
        tv_zongprice = (TextView) findViewById(R.id.tv_zongprice);
        cb_jifen = (CheckBox) findViewById(R.id.cb_jifen);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        tv_jifen = (TextView) findViewById(R.id.tv_jifen);
        rl_jifen = (RelativeLayout) findViewById(R.id.rl_jifen);
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);
        //用户积分
        anInt = sp.getInt(SPUtils.Integral, 0);
        jifenprice = 0.0;
        tv_zongprice.setText("￥"+totalPrice);
        if (isintpay == 0) {//是否可以使用积分支付【0否1是】”,
                rl_jifen.setVisibility(View.GONE);
        }else{
            if (anInt >=convert(intlpay*totalPrice)) {
                jifenprice = convert((intlpay*totalPrice)/100);
                tv_jifen.setText("可用"+(convert(intlpay*totalPrice))+"积分抵用"+(convert(((intlpay*totalPrice)/100))+"元"));
            }else{
                tv_jifen.setText("可用"+(anInt)+"积分抵用"+(convert(anInt /100)+"元"));
                jifenprice = convert(anInt/100);
            }


        }

        getAddress();
    }
    static double convert(double value){

        long l1 = Math.round(value*100); //四舍五入

        double ret = l1/100.0; //注意：使用 100.0 而不是 100

        return ret;

    }
    @Override
    protected void onResume() {

            getDisType();


        super.onResume();
    }

    @Override
    public void initData() {
        tb_toolbar.setTitle("");
        setSupportActionBar(tb_toolbar);
        tb_toolbar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        info = MyApplition.info;

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
    private int isUserIntegral = 2;//是否使用积分，1使用积分，2不使用积分
    private double prices;
    @Override
    public void initListener() {
        btn_submit.setOnClickListener(this);
        ll_address.setOnClickListener(this);

        cb_jifen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    isUserIntegral = 1;

                    tv_zongprice.setText("￥"+(totalPrice-(jifenprice)));
                }else{
                    isUserIntegral = 2;

                    tv_zongprice.setText("￥"+(totalPrice+vall));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                //直接购买
                SubmitDingdan submitDingdan = new SubmitDingdan();
                if ("购物车".equals(way)) {
                    String cartJsonData = submitDingdan.getCartJsonData(info, odyid, gsid);
                    wch(cartJsonData);
                    intent = new Intent(ctx, PayActivity.class);
                    intent.putExtra("jsonDate", cartJsonData);
                    intent.putExtra("addressId", addressId);
                    intent.putExtra("way","购物车");
                    intent.putExtra("price",prices);
                    intent.putExtra("isUserjifen", isUserIntegral);
                    intent.putExtra("totalPrice",totalPrice+value);
                    Log.i("wch",cartJsonData+"");
                }else {
                    String jsonData = submitDingdan.getJsonData(info, odyid, gsid);
                    wch(jsonData);
                    intent = new Intent(ctx, PayActivity.class);
                    intent.putExtra("jsonDate", jsonData);
                    intent.putExtra("addressId", addressId);
                    intent.putExtra("isUserjifen", isUserIntegral);
                    intent.putExtra("price",prices+price);
                    if (isUserIntegral == 1) {
                        intent.putExtra("totalPrice", totalPrice-(jifenprice+vall));

                    }else{
                        intent.putExtra("totalPrice",totalPrice+vall);
                    }
                    Log.i("wch",jsonData+"");
                }
                startActivity(intent);
                break;
            case R.id.ll_address:
                intent = new Intent(ctx,MyshippingaddressActiVIty.class);
                startActivityForResult(intent,1);
                break;

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(getCurrentFocus()!=null && getCurrentFocus().getWindowToken()!=null){
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String addanme = data.getStringExtra("addanme");
            addressId = data.getStringExtra("addid");
            String addphone = data.getStringExtra("addphone");
            String address = data.getStringExtra("address");
            String addDetail = data.getStringExtra("addDetail");
            if (!TextUtils.isEmpty(addanme)) {
                tv_addname.setText(addanme);
                tv_addpho.setText(addphone);
                tv_address.setText(address);
                tv_addressDetail.setText(addDetail);
                setAddressV();
            }

        }else{
            setAddressG();
        }
    }
     private  String addressId;
    /**
     * 获取默认收货地址
     */
    public void getAddress() {
        OkGo.get(NetUtils.Address_list)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        AddressListBean addressListBean = gson.fromJson(s, AddressListBean.class);
                        if (addressListBean.getCode() == 200) {
                            List<AddressListBean.InfoBean> info = addressListBean.getInfo();
                            for (int i = 0;i< info.size();i++){
                                AddressListBean.InfoBean infoBean = info.get(i);
                                if ("0".equals(infoBean.getFlag())) {
                                    setAddressV();
                                    tv_addname.setText(infoBean.getConsignee_name());
                                    tv_addpho.setText(infoBean.getConsignee_phe());
                                    tv_address.setText(infoBean.getConsignee_address());
                                    tv_addressDetail.setText(infoBean.getRemarks());
                                    addressId = infoBean.getAddress_id();
                                }
                            }
                        }else{
                            setAddressG();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        setAddressG();
                    }
                });
    }

    /**
     * 获得收货地址显示对应控件
     */
    private void setAddressV(){
            tv_addpho.setVisibility(View.VISIBLE);
            tv_address.setVisibility(View.VISIBLE);
            tv_addressDetail.setVisibility(View.VISIBLE);


    }

    /**
     * 没有得到收货地址，隐藏相应控件
     */
    private void setAddressG(){
        tv_addname.setText("请选择收货地址");
        tv_addpho.setVisibility(View.GONE);
        tv_address.setVisibility(View.GONE);
        tv_addressDetail.setVisibility(View.GONE);
    }
   private Map<Integer,Double> map = new HashMap<>();
    private double value;
    /**
     * 显示配送方式窗体
     */
    private void showPopuWindow(final List<DisTypeBean.InfoBean> info, final int pos) {
        View view = View.inflate(ctx,R.layout.item_popu_distype,null);
        final PopupWindow window = new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ColorDrawable drawablw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(drawablw);
        ListView lv_date = (ListView) view.findViewById(R.id.lv_list);
        final RelativeLayout rl_popu = (RelativeLayout) view.findViewById(R.id.rl_popu);
        final PopuAdapter popuAdapter = new PopuAdapter(ctx,info);
        lv_date.setAdapter(popuAdapter);
        window.showAtLocation(findViewById(R.id.main),Gravity.BOTTOM|Gravity.CENTER,0,0);
        lv_date.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DisTypeBean.InfoBean infoBean = info.get(position);
                adapter.setDisType(infoBean,position,pos);
                odyid = infoBean.getOdyid();
                price = infoBean.getPrice();
                map.put(pos,infoBean.getPrice());
                Iterator iterator = map.entrySet().iterator();
                value= 0.0;
                while(iterator.hasNext()){
                    Map.Entry entry = (Map.Entry) iterator.next();
                    value += (Double) entry.getValue();
                    //TODO
                }
                tv_zongprice.setText("￥"+(value+totalPrice));
                window.dismiss();
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
     * 获取配送方式
     */
    public void getDisType() {
        OkGo.get(NetUtils.DIS_TYPE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("wch","配送方式"+s);
                        Gson gson = new Gson();
                        DisTypeBean disTypeBean = gson.fromJson(s, DisTypeBean.class);
                        if (disTypeBean.getCode() == 200) {
                            List<DisTypeBean.InfoBean> info1 = disTypeBean.getInfo();
                            odyid = info1.get(0).getOdyid();
                            Log.i("wch",info1.get(2).getPrice()+"");
                            adapter = new AffrmDingdanAdapter(ctx,info,info1);
                            eb_list.setAdapter(adapter);

                            adapter.setPopuWindowShow(new AffrmDingdanAdapter.PopuWindowShow() {
                                @Override
                                public void setPopuWindow(List<DisTypeBean.InfoBean> info,int position) {
                                    showPopuWindow(info,position);
                                }


                            });
                            adapter.setPrice(new AffrmDingdanAdapter.TransmitPrice() {
                                @Override
                                public void setPrice(double price) {
                                    prices = price;
                                    Toast.makeText(AffirmDingdanActivity.this, price+"", Toast.LENGTH_SHORT).show();
                                    vall = price + totalPrice;

                                        tv_zongprice.setText("￥"+ vall);

                                }
                            });

                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
}
