package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;

/**
 * @作者 wch
 * @create at 2017/1/11 0011 上午 11:01
 * @name 用戶功能列表適配器
 */

public class UserListAdapter extends FastBaseAdapter {
    private String[] strs = {
            "全部订单", "个人中心", "我的收藏", "收货地址", "关于我们", "清空缓存", "检查版本"
    };

    private int [] pics = {
            R.mipmap.icon_dingdan,R.mipmap.icon_geren,R.mipmap.icon_wdshoucang,
            R.mipmap.icon_shouhuo,R.mipmap.icon_guanyu,R.mipmap.icon_qingchu,R.mipmap.icon_banben
    };
    private Context ctx;

    public UserListAdapter(Context ctx) {
        this.ctx = ctx;
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
}
