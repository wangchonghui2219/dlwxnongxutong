package com.example.administrator.dlwxnongxutong.adabter;/**
 * Created by scy on 2017/2/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.MyCollectBean;
import com.example.administrator.dlwxnongxutong.bean.NewProductBean;
import com.example.administrator.dlwxnongxutong.bean.ScBean;

import java.util.List;

import static android.R.string.no;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 商品属性$
 */
public class StandardAdapter extends BaseFastAdapter{
    private List<ScBean> sc;
    private Context ctx;
    public StandardAdapter(Context ctx,List<ScBean> sc) {
        super();
        this.sc = sc;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return sc.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        MyViewHolder vh;
        if (convertView == null) {
            vh = new MyViewHolder();
            view = View.inflate(ctx,R.layout.item_product,null);
            vh.btn_name = (Button) view.findViewById(R.id.btn_name);
            view.setTag(vh);
        }else {
            view = convertView;
            vh = (MyViewHolder) view.getTag();
        }
        ScBean scBean = sc.get(position);
        vh.btn_name.setText(scBean.getGsdesc());
        if (position == tag) {
            vh.btn_name.setBackgroundResource(R.drawable.shap_button_red);
        }else{
            vh.btn_name.setBackgroundResource(R.drawable.shap_button_line);
        }

        return view;
    }
    private int tag;
    public void setBackgroupColor(int position) {
        this.tag = position;
        this.notifyDataSetChanged();
    }

    private  class MyViewHolder {
        Button btn_name;
    }
}
