package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.MyApplition;
import com.example.administrator.dlwxnongxutong.activitys.JiFenDetailsActivity;
import com.example.administrator.dlwxnongxutong.bean.JiFenListBean;

import java.util.List;

import static com.alipay.sdk.app.statistic.c.s;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 积分列表
 */
public class JiFenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context ctx;
    private List<JiFenListBean.InfoBean> info;
    public JiFenAdapter(Context ctx,List<JiFenListBean.InfoBean> info) {
        super();
        this.ctx = ctx;
        this.info = info;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(ctx, R.layout.item_jifen_list, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final JiFenListBean.InfoBean infoBean = info.get(i);
        ((MyViewHolder)viewHolder).tv_num.setText((i+1)+"");
        ((MyViewHolder)viewHolder).tv_account.setText((infoBean.getOrderprice()));
        ((MyViewHolder)viewHolder).tv_time.setText(infoBean.getOtime());
        ((MyViewHolder)viewHolder).tv_reintl.setText(infoBean.getReintl());
        if (i != 0) {
//            ((MyViewHolder)viewHolder).ll_title.setVisibility(View.GONE);
        }else{
//            ((MyViewHolder)viewHolder).ll_title.setVisibility(View.VISIBLE);
        }
        ((MyViewHolder)viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplition applition = new MyApplition();
                applition.setJiFenDate(infoBean);
                Intent intent = new Intent(ctx,JiFenDetailsActivity.class);
                ctx.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        if (info == null) {
            return 0;
        }
        return info.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_num;
        public TextView tv_time;
        public TextView tv_account;
        public TextView tv_reintl;
        public LinearLayout ll_title;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.tv_num = (TextView) rootView.findViewById(R.id.tv_num);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_account = (TextView) rootView.findViewById(R.id.tv_account);
            this.tv_reintl = (TextView) rootView.findViewById(R.id.tv_reintl);
//            this.ll_title = (LinearLayout) rootView.findViewById(R.id.ll_title);
        }
    }

}
