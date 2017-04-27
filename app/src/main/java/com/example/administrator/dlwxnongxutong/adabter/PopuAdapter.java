package com.example.administrator.dlwxnongxutong.adabter;/**
 * Created by scy on 2017/2/16.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.DisTypeBean;

import java.util.List;


/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name $
 */
public class PopuAdapter extends BaseFastAdapter {
    private Context ctx;
    private List<DisTypeBean.InfoBean> info;
    public PopuAdapter(Context ctx,List<DisTypeBean.InfoBean> info) {
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
        View view ;
        MyViewHolder vh;
        if (convertView == null) {
            vh = new MyViewHolder();
            view= View.inflate(ctx, R.layout.item_popu_item,null);
            vh.tv_title = (TextView) view.findViewById(R.id.tv_title);
            view.setTag(vh);
        }else{
            view = convertView;
            vh = (MyViewHolder) view.getTag();
        }
        DisTypeBean.InfoBean infoBean = info.get(position);
        vh.tv_title.setText(infoBean.getDescribe());

        return view;
    }

    private class MyViewHolder {
        public TextView tv_title;
    }
}
