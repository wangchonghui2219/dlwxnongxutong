package com.example.administrator.dlwxnongxutong.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.MyApplition;
import com.example.administrator.dlwxnongxutong.activitys.AffirmDingdanActivity;
import com.example.administrator.dlwxnongxutong.activitys.ConfirmanOrder;
import com.example.administrator.dlwxnongxutong.adabter.ShopcartExpandableListViewAdapter;
import com.example.administrator.dlwxnongxutong.bean.affrm.AffrmDingdanBean;
import com.example.administrator.dlwxnongxutong.bean.affrm.LisBean;
import com.example.administrator.dlwxnongxutong.bean.affrm.ListBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.CartBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.InfoBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.ShoppCatListBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.UpJsonCarBean;
import com.example.administrator.dlwxnongxutong.utils.MyJsonWriter;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Response;

import static android.R.id.list;


/**
 * @作者 wch
 * @create at 2017/1/9 0009 下午 1:42
 * @name 购物车
 */

public class ShoppingCatFragment extends BaseFragment implements View.OnClickListener,ShopcartExpandableListViewAdapter.CheckInterface, ShopcartExpandableListViewAdapter.ModifyCountInterface {

    private List<CartBean> base_info;

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        InfoBean infoBean = info.get(groupPosition);
        List<CartBean> cart = infoBean.getCart();
        for (int i = 0; i < cart.size(); i++)
        {
            cart.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            cb_xuanzhong.setChecked(true);
        else
            cb_xuanzhong.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        InfoBean infoBean = info.get(groupPosition);
        List<CartBean> cart = infoBean.getCart();
        for (int i = 0; i < cart.size(); i++)
        {
            if (cart.get(i).isChoosed() != isChecked)
            {
                allChildSameState = false;
                break;
            }
        }
        if (allChildSameState)
        {

            infoBean.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else
        {
            infoBean.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck()) {

            cb_xuanzhong.setChecked(true);
        }
            else {
            cb_xuanzhong.setChecked(false);
        }

            selva.notifyDataSetChanged();
        calculate();

    }
    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {

    }
    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
    }
    private boolean isAllCheck()
    {
        for ( InfoBean infoBean : info)
        {
            if (!infoBean.isChoosed())
                return false;
        }
        return true;
    }
    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate()
    {
        if (!isXiugai) {



        totalCount = 0;
            totalPrice = 0.00;
            intlpay = 0;
            for (int i = 0; i < info.size(); i++)
            {
                InfoBean infoBean = info.get(i);
                List<CartBean> cart = infoBean.getCart();
                for (int j = 0; j < cart.size(); j++)
                {
                    CartBean cartBean = cart.get(j);
                    if (cartBean.isChoosed())
                    {
                        totalCount++;
                        totalCount += cartBean.getGnum()-1;
                        totalPrice += cartBean.getGp() * cartBean.getGnum();
                        Log.i("wch",totalPrice+"价格");
                        tv_totalpri.setText("￥"+totalPrice); //选定的商品总价格
                        btn_settleaccounts.setText("结算("+(totalCount+cartBean.getGnum()-1)+")");
                        if (cartBean.getIsintpay() == 1) {
                            intlpay += cartBean.getIntlpay();
                        }
                        //TODO
                        Log.i("wch", "结算("+totalCount+")");
                        Log.i("wch","积分("+intlpay+")");
                    }
                }
        }
        tv_totalpri.setText("￥" + totalPrice);
        btn_settleaccounts.setText("结算(" + totalCount + ")");
        }
    }
    private void writeDate(){
        for (int i = 0; i < info.size(); i++) {

            InfoBean infoBean = info.get(i);
            InfoBean infoBea = info.get(i);
            infoBea.setSeller(infoBean.getSeller());
            infoBea.setSellerid(infoBean.getSellerid());
            List<CartBean> cart = infoBean.getCart();
            base_info = new ArrayList<>();
            for (int j = 0; j < cart.size(); j++) {

                CartBean cartBean = cart.get(j);



                    base_info.add(cart.get(j));
                    Log.i("wch", base_info.size()+"");

            }
        }   
            Log.i("wch",info.size()+"wwwww");
        MyJsonWriter writer=new MyJsonWriter(base_info);
        Log.i("wch","sssssssss"+writer.getJsonData()+"sssssssss");
        upModifyMata(writer.getJsonData());
    }



    ListBean listBean = new ListBean();

    private void writeList() {
        List<InfoBean> infoList = new ArrayList<>();
        for (int i = 0; i < info.size(); i++) {
            int tag = 0;
            InfoBean infoBean = info.get(i);
            InfoBean infoBea = info.get(i);
            infoBea.setSeller(infoBean.getSeller());
            infoBea.setSellerid(infoBean.getSellerid());
            List<CartBean> cart = infoBean.getCart();
            List<CartBean> base_info = new ArrayList<>();
            for (int j = 0; j < cart.size(); j++) {

                CartBean cartBean = cart.get(j);
                if (cartBean.isChoosed()) {

                tag = 1;
                 base_info.add(cart.get(j));
                    Log.i("wch",base_info.size()+"");
                }
            }

            if (tag == 1) {
                infoBea.setCart(base_info);
                infoList.add(infoBea);
            }
        }
        if (infoList.size()==0) {
            Toast.makeText(ctx,"没有选择商品",Toast.LENGTH_SHORT).show();
            return;
        }
        MyApplition applition = new MyApplition();
        applition.setList(infoList);
        Log.i("wch",infoList.size()+"");
        Intent intent = new Intent(ctx, AffirmDingdanActivity.class);
//        intent.putExtra("list",(Serializable)infoList);
        intent.putExtra("way","购物车");
        intent.putExtra("intlpay",intlpay);
        intent.putExtra("totalPrice",totalPrice);
        startActivity(intent);
    }
    private ExpandableListView lv_shoppingcat;
    private Toolbar tb_toolbar;
    private CheckBox cb_xuanzhong;
    private TextView tv_totalpri,tv_bianji,tv_heji;
    private Button btn_settleaccounts;
    private SharedPreferences sp;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private List<InfoBean> info = new ArrayList<>();
    private List<CartBean> children = new ArrayList<CartBean>();// 子元素数据列表
    private ShopcartExpandableListViewAdapter selva;
    @Override
    public int getLayoutResID() {
        return R.layout.fragment_shoppingcat;
    }

    @Override
    public void initView(View view) {
        lv_shoppingcat = (ExpandableListView) view.findViewById(R.id.lv_shoppingcat);
        cb_xuanzhong = (CheckBox) view.findViewById(R.id.cb_xuanzhong);
        tv_heji = (TextView) view.findViewById(R.id.tv_heji);
        tv_totalpri = (TextView) view.findViewById(R.id.tv_totalpri);
        tv_bianji = (TextView) view.findViewById(R.id.tv_bianji);
        btn_settleaccounts = (Button) view.findViewById(R.id.btn_settleaccounts);
        sp = ctx.getSharedPreferences(SPUtils.SP_MODE, Activity.MODE_PRIVATE);
        lv_shoppingcat.setGroupIndicator(null);
        sp.edit().putString(SPUtils.Product_TAG,"tt").commit();
    }
    @Override
    public void initData() {
        initEvents();
    }
    private void initEvents()
    {
        selva = new ShopcartExpandableListViewAdapter(info,children,ctx);
        selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
        selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        lv_shoppingcat.setAdapter(selva);

        for (int i = 0; i < selva.getGroupCount(); i++)
        {
            lv_shoppingcat.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }
        cb_xuanzhong.setOnClickListener(this);
    }
    @Override
    public void onResume() {
        cb_xuanzhong.setChecked(false);
        netWorking();
        super.onResume();
    }
    @Override
    public void initListener() {
        btn_settleaccounts.setOnClickListener(this);
        cb_xuanzhong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doCheckAll();
            }
        });
        tv_bianji.setOnClickListener(this);
    }
        private boolean isXiugai;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_settleaccounts://结算
                if (isXiugai) {
                    //删除操作
                    deleteData();
                }else {
                    //结算操作
                    writeList();
                }
                break;
            case R.id.tv_bianji:
                if (info.size()==0) {
                    return;
                }

                //TODO
                if (isXiugai) {
                    writeDate();
                    isXiugai = !isXiugai;
                    tv_bianji.setText("编辑");
                    btn_settleaccounts.setText("结算("+totalCount+")");
                    tv_totalpri.setVisibility(View.VISIBLE);
                    tv_heji.setVisibility(View.VISIBLE);
                    sp.edit().putString(SPUtils.Product_TAG,"tt").commit();
                }else {
                    isXiugai = !isXiugai;
                    tv_bianji.setText("完成");
                    btn_settleaccounts.setText("删除");
                    tv_totalpri.setVisibility(View.GONE);
                    tv_heji.setVisibility(View.GONE);
                }
                selva.setIsXiuGai(isXiugai);
                break;
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    private int intlpay;//积分总数
    /** 全选与反选 */
    private void doCheckAll()
    {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < info.size(); i++)
        {


            info.get(i).setChoosed(cb_xuanzhong.isChecked());
            InfoBean infoBean = info.get(i);
            List<CartBean> cart = infoBean.getCart();

            for (int j = 0; j < cart.size(); j++)
            {
                CartBean cartBean = cart.get(j);
                cart.get(j).setChoosed(cb_xuanzhong.isChecked());

                if (cb_xuanzhong.isChecked() == true) {
                    //TODO
                    totalCount++;
                    totalCount += cartBean.getGnum()-1;
                    totalPrice += cartBean.getGp() * cartBean.getGnum();
                    Log.i("wch",totalPrice+"价格");
                    tv_totalpri.setText("￥"+totalPrice); //选定的商品总价格
                    btn_settleaccounts.setText("结算("+totalCount+")");
                    if (1 == cartBean.getIsintpay()) {
                        intlpay += cartBean.getIntlpay();
                    }


                }else{
                    tv_totalpri.setText("￥"+0.00); //选定的商品总价格
                    btn_settleaccounts.setText("结算("+0+")");
                    intlpay= 0;
                }

            }
        }

        selva.notifyDataSetChanged();
    }





    /**
     * 连网获得购物车列表
     */
    private void netWorking() {
        Log.i("wch",sp.getString(SPUtils.USER_ID,"")+"");
        OkGo.get(NetUtils.ShoppingCat_list)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("wch","购物车"+s+"");
                        Gson gson = new Gson();
                        ShoppCatListBean shoppCatListBean = gson.fromJson(s, ShoppCatListBean.class);
                        if (shoppCatListBean.getCode() == 200) {
                            info = shoppCatListBean.getInfo();
                            initEvents();
                        }else {
                            Toast.makeText(ctx, shoppCatListBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });

    }


    /**
     * 上传修改的信息数据
     * @param jsonData
     */
    private void upModifyMata(String jsonData) {
        OkGo.post(NetUtils.ModifyData)
                .params("cart",jsonData)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("wch",s+"");
                            Gson gson = new Gson();
                        com.example.administrator.dlwxnongxutong.bean.InfoBean infoBean = gson.fromJson(s, com.example.administrator.dlwxnongxutong.bean.InfoBean.class);
//                       Toast.makeText(ctx,infoBean.message,Toast.LENGTH_SHORT).show();
                        sp.edit().putString(SPUtils.Product_TAG,"tt").commit();
                        netWorking();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });

    }
    /**
     * 删除购物车信息
     */
    private void deleteData() {
        String gctids = "";
        List<InfoBean> infoList = new ArrayList<>();
        for (int i = 0; i < info.size(); i++) {
            int tag = 0;
            InfoBean infoBean = info.get(i);
            InfoBean infoBea = info.get(i);
            infoBea.setSeller(infoBean.getSeller());
            infoBea.setSellerid(infoBean.getSellerid());
            List<CartBean> cart = infoBean.getCart();
            List<CartBean> base_info = new ArrayList<>();
            for (int j = 0; j < cart.size(); j++) {

                CartBean cartBean = cart.get(j);
                if (cartBean.isChoosed()) {

                    String gctid = cartBean.getGctid();
                    if (TextUtils.isEmpty(gctids)) {
                        gctids = gctid;
                    }else {
                        gctids = gctids + "," + gctid;
                    }
                    Log.i("wch",gctids+"");
                }
            }
        }
        if (TextUtils.isEmpty(gctids)) {
            Toast.makeText(ctx,"没有选择商品",Toast.LENGTH_SHORT).show();
            return;
        }
        String[] split = gctids.split(",");
        OkGo.get(NetUtils.Delete_Cart)
                .params("gctid",gctids)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("wch",s+"");
                        netWorking();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.i("wch",response+"");
                    }
                });


    }
}
