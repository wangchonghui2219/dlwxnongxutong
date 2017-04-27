package com.example.administrator.dlwxnongxutong.adabter;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activitys.NewAddressActivity;
import com.example.administrator.dlwxnongxutong.bean.AddressListBean;
import com.example.administrator.dlwxnongxutong.bean.InfoBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 上午 11:20
 * @name 收货地址适配器
 */

public class ShippingAddressAdapter extends FastBaseAdapter {
    private List<String> mList;
    private Context ctx;
    private int tag = -1;
    private  String user_id;
    private  List<AddressListBean.InfoBean> info;
    public ShippingAddressAdapter(Context ctx, List<AddressListBean.InfoBean> info,String user_id) {
        this.ctx = ctx;
        this.info = info;
        this.user_id = user_id;
    }

    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        final ViewHolder vh;
        if (convertView == null) {
            view = View.inflate(ctx, R.layout.item_address, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();

        }
        final AddressListBean.InfoBean infoBean = info.get(position);
        vh.tv_name.setText(infoBean.getConsignee_name());
        vh.tv_number.setText(infoBean.getConsignee_phe());
        vh.tv_address.setText(infoBean.getConsignee_address());
        if ("0".equals(infoBean.getFlag())) {//默认
            vh.cb_moren.setChecked(true);
            tag = position;
        }else{
            vh.cb_moren.setChecked(false);
        }
        vh.ll_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAddress(infoBean.getAddress_id(),position);
            }


        });
        vh.ll_seting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, NewAddressActivity.class);
                intent.putExtra("tag","修改");
                intent.putExtra("name",infoBean.getConsignee_name());
                intent.putExtra("phe",infoBean.getConsignee_phe());
                intent.putExtra("address",infoBean.getConsignee_address());
                intent.putExtra("remarks",infoBean.getRemarks());
                intent.putExtra("flag",infoBean.getFlag());
                intent.putExtra("address_id",infoBean.getAddress_id());
                ctx.startActivity(intent);
            }
        });

        vh.iv_gouxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infoBean.getFlag().equals("0")){

                }else{
                    setMoren(infoBean.getAddress_id(),position);
                }
            }
        });

        return view;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView tv_name,tv_address;
        public TextView tv_number;
        public LinearLayout iv_gouxuan;
        public LinearLayout ll_seting;
        public LinearLayout ll_delete;
        public CheckBox cb_moren;
        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_number = (TextView) rootView.findViewById(R.id.tv_number);
            this.tv_address = (TextView) rootView.findViewById(R.id.tv_address);
            this.iv_gouxuan = (LinearLayout) rootView.findViewById(R.id.iv_gouxuan);
            this.ll_seting = (LinearLayout) rootView.findViewById(R.id.ll_seting);
            this.ll_delete = (LinearLayout) rootView.findViewById(R.id.ll_delete);
            this.cb_moren = (CheckBox) rootView.findViewById(R.id.cb_moren);
        }

    }
    /**
     * 删除收货地址
     */
    private void deleteAddress( final String address_id,final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.create();
        builder.setMessage("确定删除该收货地址吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("wch",address_id+"address");
                OkGo.get(NetUtils.Delete_ADDress)
                        .params("address_id",address_id)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                Log.i("wch",s);
                                Gson gson = new Gson();
                                InfoBean infoBean = gson.fromJson(s, InfoBean.class);
                                if (infoBean.code == 200){
                                    Toast.makeText(ctx,infoBean.message,Toast.LENGTH_SHORT).show();
                                        info.remove(position);
                                    notifyDataSetChanged();

                                }else{
                                    Toast.makeText(ctx,infoBean.message,Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                            }
                        });
            }
        });
        builder.setNegativeButton("取消",null);

        builder.show();



    }
    /**
     * 设置默认地址
     */
    private void setMoren(String address_id,final int position) {
        Log.i("wch",address_id+"");
        OkGo.post(NetUtils.SetDefault)
                .params("adid",address_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("wch",s+"sdfsdfsd");
                        OkGo.get(NetUtils.Address_list)
                                .params("userid",user_id)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        Gson gson = new Gson();
                                        AddressListBean addressListBean = gson.fromJson(s, AddressListBean.class);
                                        if (addressListBean.getCode() == 200) {
                                            info = addressListBean.getInfo();
                                           notifyDataSetChanged();
                                        }else{

                                        }

                                    }

                                    @Override
                                    public void onError(Call call, Response response, Exception e) {
                                        super.onError(call, response, e);
                                    }
                                });

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
}
