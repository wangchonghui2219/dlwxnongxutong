package com.example.administrator.dlwxnongxutong.adabter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activitys.EvaluateActivity;
import com.example.administrator.dlwxnongxutong.activitys.ProductDetailActivity;
import com.example.administrator.dlwxnongxutong.bean.InfoBean;
import com.example.administrator.dlwxnongxutong.bean.PhotoBean;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;
import com.example.administrator.dlwxnongxutong.views.NoListView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.R.id.list;
import static com.alipay.sdk.app.statistic.c.w;

/**
 * 
 *
 * @作者 wch
 *
 * @create at 2017/1/11 0011 下午 5:15
 * 
 * @name 全部订单适配器
 */

public class MyOrderFromAdapter extends RecyclerView.Adapter<MyOrderFromAdapter.MyViewHolder>{
    private Context ctx;
    private List<MyOrderBean.InfoBean> info;
    private MyOrderFromAdapter adapter;
    public MyOrderFromAdapter (Context ctx,List<MyOrderBean.InfoBean> info){
        this.ctx = ctx;
        this.info = info;
        adapter = this;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.btn_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,EvaluateActivity.class);
                intent.putExtra("good_id",info.get(position).getGoid());
                ctx.startActivity(intent);
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOrder(position);
            }
        });
        final MyOrderBean.InfoBean infoBean = info.get(position);
            holder.tv_name.setText(infoBean.getSeller());
        holder.tv_num.setText("共"+infoBean.getNumber()+"件商品，合计：");
        holder.tv_total.setText("￥"+infoBean.getTotal());
        if (infoBean.getOrder() != null) {
            final List<MyOrderBean.InfoBean.OrderBean> order = infoBean.getOrder();
            MyOrderItemAdapter adapter = new MyOrderItemAdapter(ctx,order);
            holder.lv_item_list.setAdapter(adapter);
            holder.lv_item_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MyOrderBean.InfoBean.OrderBean orderBean = order.get(position);
                    Intent intent = new Intent(ctx, ProductDetailActivity.class);
                       intent.putExtra("godsid",orderBean.getGodsid());
                    ctx.startActivity(intent);
                }
            });
        }

    }

    /**
     * 删除订单
     */
    private void deleteOrder(final int position) {
        SharedPreferences sp = ctx.getSharedPreferences(SPUtils.SP_MODE, Activity.MODE_PRIVATE);

        OkGo.get(NetUtils.Delete_Order)
                .params("userid",sp.getString(SPUtils.USER_ID,""))
                .params("orderid",info.get(position).getGoid())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call cal, Response response) {
                        Log.i("wch",s+"");
                        Gson gson = new Gson();
                        InfoBean infoBean = gson.fromJson(s, InfoBean.class);
                        if (infoBean.code == 200) {
                            info.remove(position);
                        adapter.notifyDataSetChanged();
                        }
                        Toast.makeText(ctx, infoBean.message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Toast.makeText(ctx, "网络不好请重新点击", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(ctx, R.layout.item_ordrtfrom,null);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return info.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private Button btn_evaluate,btn_delete;
        private NoListView lv_item_list;
        private TextView tv_name;
        private TextView tv_num,tv_total;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                        Toast.makeText(ctx,"sss",Toast.LENGTH_SHORT).show();
                    if (onItemCleckListener != null) {
                        onItemCleckListener.OnItemCleck(view, info.get(getLayoutPosition()));
                    }
                }
            });
            this.btn_evaluate = (Button) itemView.findViewById(R.id.btn_evaluate);
            this.btn_delete = (Button) itemView.findViewById(R.id.btn_delete);
            this.lv_item_list = (NoListView) itemView.findViewById(R.id.lv_item_list);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_dianname);
            this.tv_num = (TextView) itemView.findViewById(R.id.tv_num);
            this.tv_total = (TextView) itemView.findViewById(R.id.tv_total);
        }
    }

    public interface OnItemCleckListener {
        public void OnItemCleck(View itemView, MyOrderBean.InfoBean infoBean);
    }

    private OnItemCleckListener onItemCleckListener;

    public void setOnItemCleckListener(OnItemCleckListener onItemCleckListener) {
        this.onItemCleckListener = onItemCleckListener;
    }

    private AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };
}
