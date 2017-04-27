package com.example.administrator.dlwxnongxutong.adabter;/**
 * Created by scy on 2017/2/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.AllPingjiaBean;
import com.example.administrator.dlwxnongxutong.bean.AllevauateBean;

import java.util.List;

import static com.example.administrator.dlwxnongxutong.R.id.view;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name $
 */
public class AllPingjiaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context ctx;
    private List<AllPingjiaBean.InfoBean> list;
    public AllPingjiaAdapter(Context ctx, List<AllPingjiaBean.InfoBean> list) {
        this.ctx = ctx;
        this.list = list;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_allevauate,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AllPingjiaBean.InfoBean infoBean = list.get(position);
        ((MyViewholder) holder).tv_number.setText(infoBean.getPhe());

        ((MyViewholder) holder).tv_time.setText(infoBean.getRatetime());
        ((MyViewholder) holder).tv_content.setText(infoBean.getRateinfo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private class MyViewholder extends RecyclerView.ViewHolder{
        TextView tv_number,tv_time,tv_content;
        public MyViewholder(View itemView) {
            super(itemView);
            this.tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            this.tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
