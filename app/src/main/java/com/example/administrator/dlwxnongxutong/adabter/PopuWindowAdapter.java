package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 省市区联动
 */

public class PopuWindowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context ctx;
    private List<String> names;
    public PopuWindowAdapter(Context ctx,List<String> names) {
       this.ctx = ctx;
        this.names = names;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(ctx, R.layout.item_popu_address_item,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        String s = names.get(position);
        ((MyViewHolder)holder).tv_name.setText(s);
        ((MyViewHolder)holder).rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.setOnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
    private class MyViewHolder extends RecyclerView.ViewHolder{
        public RelativeLayout rl_item;
        public TextView tv_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            rl_item = (RelativeLayout) itemView;
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
private OnItemClickListener onItemClickListener;
    public interface  OnItemClickListener{
       public abstract void setOnClick(int position);
    }
    public void setOnItemClickListener (OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
