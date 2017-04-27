package com.example.administrator.dlwxnongxutong.adabter;/**
 * Created by scy on 2017/2/16.
 */

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.SearchResultActivity;
import com.example.administrator.dlwxnongxutong.bean.ClassGoodsBean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name $
 */
public class ClassAdapterTwo extends BaseFastAdapter {
    private Context ctx;
    private List<ClassGoodsBean.InfoBean.LowerBeanX> lower;
    public ClassAdapterTwo(Context ctx,List<ClassGoodsBean.InfoBean.LowerBeanX> lower) {
        this.ctx = ctx;
        this.lower = lower;
    }

    @Override
    public int getCount() {
        if (lower == null){
            return 0;
        }
        return lower.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder vh;
        if (convertView == null) {
            view = View.inflate(ctx, R.layout.item_class_two,null);
            vh = new ViewHolder();
            vh.tv_title = (TextView) view.findViewById(R.id.tv_title);
            vh.gv_title = (GridView) view.findViewById(R.id.gv_title);
            view.setTag(vh);
        }else{
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
        final ClassGoodsBean.InfoBean.LowerBeanX lowerBeanX = lower.get(position);
        vh.tv_title.setText(lowerBeanX.getGcname());

        vh.gv_title.setAdapter(new MyGridAdapter(ctx,lowerBeanX.getLower()));
        vh.gv_title.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ctx, SearchResultActivity.class);
                intent.putExtra("classid",lowerBeanX.getGcid());
                ctx.startActivity(intent);
            }
        });
        return view;
    }
    private  class ViewHolder {
        TextView tv_title;
        GridView gv_title;
    }

    private class MyGridAdapter extends BaseFastAdapter{
        private Context ctx;
        private List<ClassGoodsBean.InfoBean.LowerBeanX.LowerBean> lower;
        public MyGridAdapter(Context ctx,List<ClassGoodsBean.InfoBean.LowerBeanX.LowerBean> lower) {
           this.ctx = ctx;
            this.lower = lower;
        }

        @Override
        public int getCount() {
            if (lower == null){
                return  0;
            }
            return lower.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                view= View.inflate(ctx, R.layout.item_class_title,null);
                vh.tv_title = (TextView) view.findViewById(R.id.tv_title);
                view.setTag(vh);
            }else{
                view = convertView;
                vh = (ViewHolder) view.getTag();
            }
            ClassGoodsBean.InfoBean.LowerBeanX.LowerBean lowerBean = lower.get(position);
            vh.tv_title.setText(lowerBean.getGcname());

            return view;
        }
    }

}
