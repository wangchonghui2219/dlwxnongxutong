package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.bean.BankListBean;

import java.util.List;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 绑定银行卡
 */
public class BnakListAdapter extends BaseFastAdapter {
    private Context ctx;
    private List<BankListBean.InfoBean> info;
    public BnakListAdapter(Context ctx,List<BankListBean.InfoBean> info) {
        super();
        this.ctx = ctx;
        this.info = info;
    }

    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder vh;
        if (convertView == null) {
            vh = new MyViewHolder();
            convertView = View.inflate(ctx,R.layout.item_bank_item,null);
            vh.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(vh);
        }else {
            vh = (MyViewHolder) convertView.getTag();
        }
        BankListBean.InfoBean infoBean = info.get(position);
        String bank_name = infoBean.getBank_name();
        String bank_card = infoBean.getBank_card();
        String subbank_card = bank_card.substring(bank_card.length() - 4, bank_card.length() - 1);
        vh.tv_name.setText(bank_name+"("+subbank_card+")");
        return convertView;
    }

    private class MyViewHolder{
        TextView tv_name;
    }
}
