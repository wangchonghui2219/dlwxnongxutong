package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.affrm.LisBean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 搜索界面排序
 */
public class SearchPopuAdapter extends BaseFastAdapter {
    private Context ctx;
    private List<String> paixu;
    public SearchPopuAdapter(Context ctx, List<String> paixu) {
        this.ctx= ctx;
        this.paixu = paixu;
    }

    @Override
    public int getCount() {
        return paixu.size();
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
        vh.tv_title.setText(paixu.get(position));
        return view;
    }
    private class MyViewHolder {
        public TextView tv_title;
    }
}
