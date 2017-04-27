package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.MyApplition;
import com.example.administrator.dlwxnongxutong.activitys.TiXIanDetailsActivity;
import com.example.administrator.dlwxnongxutong.bean.YuEFanLiBean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 余额
 */

public class YuEAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context ctx;
    private List<YuEFanLiBean.InfoBean> info;

    public YuEAdpater(Context ctx, List<YuEFanLiBean.InfoBean> info) {
        super();
        this.ctx = ctx;
        this.info = info;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(ctx, R.layout.item_yue, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
       final YuEFanLiBean.InfoBean infoBean = info.get(i);
        if (infoBean.getFlprice() == null) {
            ((MyViewHolder)viewHolder).tv_money.setText(infoBean.getTx());
            ((MyViewHolder)viewHolder).tv_time.setText(infoBean.getTxtime());
            if (infoBean.getIsstate() == 1) {
                ((MyViewHolder)viewHolder).btn_issucceed.setText("未到账");
            }else{
                ((MyViewHolder)viewHolder).btn_issucceed.setText("成功");
            }
        }else{
            ((MyViewHolder)viewHolder).tv_money.setText("+"+infoBean.getFlprice());
            ((MyViewHolder)viewHolder).tv_time.setText(infoBean.getFftime());
            if (infoBean.getIsdz() == 1) {
                ((MyViewHolder)viewHolder).btn_issucceed.setText("未到账");
            }else{
                ((MyViewHolder)viewHolder).btn_issucceed.setText("成功");
            }

        }
        ((MyViewHolder)viewHolder).tv_orderno.setText(infoBean.getOrderno());
        ((MyViewHolder)viewHolder).tv_yue_money.setText(infoBean.getAccount());


        ((MyViewHolder)viewHolder).rootView.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View v) {
                MyApplition applition = new MyApplition();
                applition.setYuEDate(infoBean);
                Intent intent  = new Intent(ctx,TiXIanDetailsActivity.class);

                ctx.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public Button btn_issucceed;
        public View rootView;
        public TextView tv_orderno;
        public TextView tv_money;
        public TextView tv_yue_money;
        public TextView tv_time;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.tv_orderno = (TextView) rootView.findViewById(R.id.tv_orderno);
            this.tv_money = (TextView) rootView.findViewById(R.id.tv_money);
            this.tv_yue_money = (TextView) rootView.findViewById(R.id.tv_yue_money);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.btn_issucceed = (Button) rootView.findViewById(R.id.btn_issucceed);

        }
    }


}
