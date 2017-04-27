package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;

import static com.alipay.sdk.app.statistic.c.G;
import static com.alipay.sdk.app.statistic.c.s;

/**
 * @作者 wch
 * @create at 2017/1/11 0011 上午 11:56
 * @name 设置
 */

public class SettingAdapter extends FastBaseAdapter {
    private Context ctx;

    private int[] pics = {R.mipmap.icon_denglumi,R.mipmap.icon_zhifumi,R.mipmap.icon_bddls};
    private String adminid;
    private final String[] strs;

    public SettingAdapter(Context ctx,String adminid) {
        this.ctx = ctx;
//        this.adminid = adminid;
        if ("0".equals(adminid)) {

            strs = new String[]{"修改登录密码","修改支付密码","绑定管理员列表"};
        }else{
            strs = new String[]{"修改登录密码","修改支付密码"};

        }

    }

    @Override
    public int getCount() {
        return strs.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder vh;
        if (convertView == null) {
            view = View.inflate(ctx, R.layout.item_user, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }


            vh.tv_name.setText(strs[position]);
            vh.iv_pic.setBackgroundResource(pics[position]);

        return view;
    }
    private static class ViewHolder {
        public View rootView;
        public ImageView iv_pic;
        public TextView tv_name;
        public ImageView iv_into;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.iv_into = (ImageView) rootView.findViewById(R.id.iv_into);
        }

    }
    public void setAdminid(String aminids){
        adminid = aminids;

        this.notifyDataSetChanged();
    }
}
