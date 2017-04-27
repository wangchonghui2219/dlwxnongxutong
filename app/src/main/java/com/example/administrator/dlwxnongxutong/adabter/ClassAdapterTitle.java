package com.example.administrator.dlwxnongxutong.adabter;/**
 * Created by scy on 2017/2/16.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.ClassGoodsBean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 分类一级标题$
 */
public class ClassAdapterTitle  extends BaseFastAdapter{
    private Context ctx;
    private List<ClassGoodsBean.InfoBean> info;
    public ClassAdapterTitle(Context ctx,List<ClassGoodsBean.InfoBean> info) {
        this.ctx = ctx;
        this.info = info;

    }

    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        MyViewHolder vh;
        if (convertView == null) {
            vh = new MyViewHolder();
            view= View.inflate(ctx, R.layout.item_class_title,null);
            vh.tv_title = (TextView) view.findViewById(R.id.tv_title);
            view.setTag(vh);
        }else{
            view = convertView;
            vh = (MyViewHolder) view.getTag();
        }
        ClassGoodsBean.InfoBean infoBean = info.get(position);
        vh.tv_title.setText(infoBean.getGcname());
        if (position == tag) {
            vh.tv_title.setTextColor(ctx.getResources().getColor(R.color.red));
        }else{
            vh.tv_title.setTextColor(ctx.getResources().getColor(R.color.textcolor));
        }
        return view;
    }
    private class MyViewHolder {
            TextView tv_title;
    }
    private  int tag =0;
    public void setColor(int position){
            tag = position;
        notifyDataSetChanged();
    }
}
