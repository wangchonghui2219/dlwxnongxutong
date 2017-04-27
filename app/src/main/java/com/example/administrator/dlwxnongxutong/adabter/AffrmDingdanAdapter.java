package com.example.administrator.dlwxnongxutong.adabter;


import android.content.Context;
import android.drm.DrmStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activitys.person.Product;
import com.example.administrator.dlwxnongxutong.bean.DisTypeBean;
import com.example.administrator.dlwxnongxutong.bean.affrm.LisBean;
import com.example.administrator.dlwxnongxutong.bean.affrm.ListBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.CartBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.InfoBean;
import com.example.administrator.dlwxnongxutong.listener.EdiTextListender;
import com.example.administrator.dlwxnongxutong.utils.NetUtils;
import com.example.administrator.dlwxnongxutong.views.NoListView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 确认订单商品列表
 */
public class AffrmDingdanAdapter extends BaseFastAdapter {
    private Context ctx;
    private List<InfoBean> info;
    private double allTotalPrice = 0.0;
    private double price;
    private List<DisTypeBean.InfoBean> info1;
    public AffrmDingdanAdapter(Context ctx,List<InfoBean> info,List<DisTypeBean.InfoBean> info1) {
        super();
        this.ctx = ctx;
        this.info = info;
        this.info1 = info1;
    }

    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
       final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            view = View.inflate(ctx,R.layout.item_affirm_dingdan,null);
            vh.lv_product = (NoListView) view.findViewById(R.id.lv_product);
            vh.tv_dianjianame = (TextView) view.findViewById(R.id.tv_dianjianame);
            vh.tv_zongshu = (TextView) view.findViewById(R.id.tv_zongshu);
            vh.tv_zongjia = (TextView) view.findViewById(R.id.tv_zongjia);
            vh.rl_distype = (RelativeLayout) view.findViewById(R.id.rl_distype);
            vh.tv_isbaoyou = (TextView) view.findViewById(R.id.tv_isbaoyou);
            vh.et_message = (EditText) view.findViewById(R.id.et_message);
            vh.tv_freight = (TextView) view.findViewById(R.id.tv_freight);
            view.setTag(vh);
        }else{
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
        InfoBean infoBean = info.get(position);
        vh.tv_dianjianame.setText(infoBean.getSeller());
        vh.et_message.addTextChangedListener(new EdiTextListender(info,position));//给买家留言输入框添加监听
        int  totalCount = 0;
        double totalPrice = 0.00;

        List<CartBean> cart = infoBean.getCart();
        for (int i = 0;i<cart.size();i++){
            CartBean cartBean = cart.get(i);
            totalCount += cartBean.getGnum();
            totalPrice +=cartBean.getGp()*cartBean.getGnum();


        }

            allTotalPrice += totalPrice;

        Log.i("wch",allTotalPrice+"");
        Log.i("wch",position+"");
//        transmitPrice.setPrice(allTotalPrice);
        vh.tv_zongjia.setText("￥"+totalPrice);
        vh.tv_zongshu.setText("共"+totalCount+"件商品,合计：");
        AffrmDingdanItemAdapter adapter = new AffrmDingdanItemAdapter(ctx,cart);
        vh.lv_product.setAdapter(adapter);
        if (describe != null) {
            if (position == flag) {
                vh.tv_isbaoyou.setText(describe);
            }else{
                vh.tv_isbaoyou.setText("包邮");
            }

        }else{
            vh.tv_isbaoyou.setText("包邮");
        }

        if (position == flag) {
            vh.tv_freight.setText("运费￥"+info1.get(typeposition).getPrice());
        }else{

        }


        vh.rl_distype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popuWindowShow.setPopuWindow(info1,position);
                flag = position;

            }
        });
        return view;
    }

    private  class ViewHolder{
        NoListView lv_product;
        RelativeLayout rl_distype;
        TextView tv_dianjianame,tv_zongshu,tv_zongjia,tv_isbaoyou,tv_freight;
        EditText et_message;
    }
    private PopuWindowShow popuWindowShow;
    public interface PopuWindowShow{

        public abstract void setPopuWindow(List<DisTypeBean.InfoBean> info, int position);
    }
    public void setPopuWindowShow(PopuWindowShow popuWindowShow){
        this.popuWindowShow = popuWindowShow;
    }
    private String describe;
    private int flag = -1;
    private int typeposition = 0;
    /**
     * 选配送方式重新设置
     * @param infoBean
     */
    public void setDisType(DisTypeBean.InfoBean infoBean,int typeposition,int pos){
        this.describe= infoBean.getDescribe();
        this.typeposition = typeposition;
        this.flag = pos;
        notifyDataSetChanged();
    }

    /**
     * 设置外部总钱数接口
     */
    private TransmitPrice transmitPrice;
     public interface TransmitPrice {
         public abstract void setPrice(double price);
     }
     public void setPrice(TransmitPrice transmitPrice){
         this.transmitPrice = transmitPrice;
     }
}
