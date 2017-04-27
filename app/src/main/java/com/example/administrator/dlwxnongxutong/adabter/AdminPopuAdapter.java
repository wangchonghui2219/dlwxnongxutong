package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.AdminListBean;

import java.util.List;

import static com.alipay.sdk.app.statistic.c.s;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 管理员雷彪
 */
public class AdminPopuAdapter extends BaseFastAdapter {
    private Context ctx;
    private List<AdminListBean.InfoBean> info;
    public AdminPopuAdapter(Context ctx,List<AdminListBean.InfoBean> info) {
        super();
        this.ctx = ctx;
        this.info = info;
    }

    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.item_admin_popu, null);
           vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
                vh = (ViewHolder) convertView.getTag();
        }
        AdminListBean.InfoBean infoBean = info.get(position);
        vh.tv_adminphe.setText(infoBean.getAdminphe());
        vh.tv_name.setText(infoBean.getName());
        return convertView;
    }

    private  class ViewHolder {
        public View rootView;
        public TextView tv_adminphe;
        public TextView tv_name;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_adminphe = (TextView) rootView.findViewById(R.id.tv_adminphe);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
        }

    }
}
