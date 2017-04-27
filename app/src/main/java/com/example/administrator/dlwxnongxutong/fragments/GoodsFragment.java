package com.example.administrator.dlwxnongxutong.fragments;


import android.support.v7.widget.OrientationHelper;
import android.util.Log;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ListView;


import com.example.administrator.dlwxnongxutong.R;

import com.example.administrator.dlwxnongxutong.adabter.ClassAdapterTitle;
import com.example.administrator.dlwxnongxutong.adabter.ClassAdapterTwo;
import com.example.administrator.dlwxnongxutong.bean.ClassGoodsBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;



/**
 * @作者 wch
 * @create at 2017/1/7 0007 上午 10:48
 * @name 商品
 */
public class GoodsFragment extends BaseFragment {
    private ListView lv_class;
    private ListView lv_classmess;


    private List<ClassGoodsBean.InfoBean> info = new ArrayList<>();
    private ClassAdapterTitle adapterTitle;
    private ClassAdapterTwo adapterTwo;
    @Override
    public int getLayoutResID() {
        return R.layout.fragment_goods;
    }
    @Override
    public void initView(View view) {
        lv_class = (ListView) view.findViewById(R.id.lv_class);
        lv_classmess = (ListView) view.findViewById(R.id.lv_classmess);

    }
    @Override
    public void initData() {


        getAllData();

    }
    private List<ClassGoodsBean.InfoBean.LowerBeanX> lower = new ArrayList<>();
    @Override
    public void initListener() {
        lv_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {




            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapterTitle.setColor(position);
                lower = info.get(position).getLower();
                if (lower != null) {
                    adapterTwo = new ClassAdapterTwo(ctx,lower);
                    lv_classmess.setAdapter(adapterTwo);
                }else {
                    adapterTwo = new ClassAdapterTwo(ctx,lower);
                    lv_classmess.setAdapter(adapterTwo);

                }


            }
        });

    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    public void getAllData() {
        OkGo.get(NetUtils.Class_GOODS)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("wch","分类"+s);
                    gsonJson(s);
                    }
                });
    }

    /**
     * 解析数据
     * @param s
     */
    private void gsonJson(String s) {
        Gson gson = new Gson();
        ClassGoodsBean classGoodsBean = gson.fromJson(s, ClassGoodsBean.class);
        if (classGoodsBean.getCode() == 200) {
            info = classGoodsBean.getInfo();
            adapterTitle = new ClassAdapterTitle(ctx,info);
            lv_class.setAdapter(adapterTitle);
            adapterTwo = new ClassAdapterTwo(ctx,classGoodsBean.getInfo().get(0).getLower());
            lv_classmess.setAdapter(adapterTwo);
        }
    }
}
