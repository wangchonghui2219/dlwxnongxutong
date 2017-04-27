package com.example.administrator.dlwxnongxutong.adabter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.activity.MyApplition;
import com.example.administrator.dlwxnongxutong.activitys.ProductAttributeActivity;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.CartBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.InfoBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.ShoppCatListBean;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.media.CamcorderProfile.get;
import static android.os.Build.VERSION_CODES.M;

/**
 * Created by scy on 2017/2/18.
 */

public class ShopcartExpandableListViewAdapter extends BaseExpandableListAdapter {
    private List<InfoBean> info;
    private List<CartBean> children;
    private Context ctx;
      private  List<Integer> inte = new ArrayList<>();
    private Map<Integer,List<Integer>> positiondate = new HashMap<>();
    private final SharedPreferences sp;

    public ShopcartExpandableListViewAdapter(List<InfoBean> info, List<CartBean> children, Context ctx) {

        this.ctx = ctx;
        this.info = info;
        this.children = children;
        sp = ctx.getSharedPreferences(SPUtils.SP_MODE, Context.MODE_PRIVATE);

    }
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    public void setCheckInterface(CheckInterface checkInterface)
    {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface)
    {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getGroupCount() {

        return info.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
       children = info.get(groupPosition).getCart();

        return children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return info.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<CartBean> cart = info.get(groupPosition).getCart();

        return cart.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }



    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder gholder;
        View view;
        if (convertView == null) {
            gholder = new GroupHolder();
            view = View.inflate(ctx, R.layout.item_shoppingcat,null);
            gholder.iv_gouxuana = (CheckBox) view.findViewById(R.id.iv_gouxuana);
            gholder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(gholder);
        }else{
            view = convertView;
            gholder = (GroupHolder) view.getTag();
        }
        final InfoBean infoBean = info.get(groupPosition);
        gholder.tv_name.setText(infoBean.getSeller());
        gholder.iv_gouxuana.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)

            {
                infoBean.setChoosed(((CheckBox) v).isChecked());
                checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());// 暴露组选接口
            }
        });
        gholder.iv_gouxuana.setChecked(infoBean.isChoosed());
        return view;
    }
    private int num;
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final ChildHolder gholder;
        View view;
        if (convertView == null) {gholder = new ChildHolder();
            view = View.inflate(ctx, R.layout.item_shappcat2,null);
            gholder.iv_gouxuanb = (CheckBox) view.findViewById(R.id.iv_gouxuanb);
            gholder.tv_jianjie = (TextView) view.findViewById(R.id.tv_jianjie);
            gholder.iv_pic = (ImageView) view.findViewById(R.id.iv_pic);
            gholder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            gholder.tv_number = (TextView) view.findViewById(R.id.tv_number);
            gholder.ll_compile = (LinearLayout) view.findViewById(R.id.ll_compile);
            gholder.ll_compiles = (LinearLayout) view.findViewById(R.id.ll_compiles);
            gholder.rl_reduce = (RelativeLayout) view.findViewById(R.id.rl_reduce);
            gholder.rl_add = (RelativeLayout) view.findViewById(R.id.rl_add);
            gholder.tv_num = (TextView) view.findViewById(R.id.tv_num);
            gholder.rl_xuanze = (RelativeLayout) view.findViewById(R.id.rl_xuanze);
            gholder.tv_guigename = (TextView) view.findViewById(R.id.tv_guigename);
            view.setTag(gholder);
        }else{
            view = convertView;
            gholder = (ChildHolder) view.getTag();
        }

        final CartBean cartBean = (CartBean) getChild(groupPosition,childPosition);
        gholder.tv_guigename.setText(cartBean.getGsdesc()+"");
        Glide.with(ctx).load(cartBean.getGpo()).into(gholder.iv_pic);
        gholder.tv_jianjie.setText(cartBean.getGname());
        gholder.tv_price.setText("￥"+cartBean.getGp());
        gholder.tv_number.setText("X"+cartBean.getGnum());
        gholder.tv_num.setText(cartBean.getGnum()+"");
        num = cartBean.getGnum();
        gholder.iv_gouxuanb.setChecked(cartBean.isChoosed());
//            gholder.iv_gouxuanb.setChecked(infoBean.isChoosed());
        gholder.iv_gouxuanb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                cartBean.setChoosed(((CheckBox) v).isChecked());
                gholder.iv_gouxuanb.setChecked(((CheckBox) v).isChecked());
                checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
            }
        });
        if (isXiuGai||"tag".equals(sp.getString(SPUtils.Product_TAG,""))) {

            gholder.ll_compiles.setVisibility(View.GONE);
            gholder.ll_compile.setVisibility(View.VISIBLE);

        }else{

            gholder.ll_compiles.setVisibility(View.VISIBLE);
            gholder.ll_compile.setVisibility(View.GONE);
        }



//        num = children.get(childPosition).getGnum();
        /**
         * 商品数量加减
         */
        gholder.rl_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 CartBean cartBean = (CartBean) getChild(groupPosition,childPosition);
            num = cartBean.getGnum();
                num++;
            gholder.tv_num.setText(num+"");
                cartBean.setGnum(num);
            }
        });
        gholder.rl_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartBean cartBean = (CartBean) getChild(groupPosition,childPosition);
                num = cartBean.getGnum();
                if (num<=1){
                    num = 1;
                }else{
                    num--;
                }
                cartBean.setGnum(num);
                gholder.tv_num.setText(num+"");
            }
        });
        /**
         * 选择规格跳转到商品属性界面选择
         */
        gholder.rl_xuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ctx,ProductAttributeActivity.class);
                intent.putExtra("gname",cartBean.getGcname());
                intent.putExtra("gp",cartBean.getGp());
                intent.putExtra("gpo",cartBean.getGpo());
                intent.putExtra("godsid",cartBean.getGid());
                intent.putExtra("gnum",cartBean.getGnum());
                MyApplition applition = new MyApplition();
                applition.setSc(cartBean.getSc());
                ctx.startActivity(intent);
            }
        });
        return view;

    }
    private boolean isXiuGai;
    public void setIsXiuGai(boolean isXiuGai) {
        this.isXiuGai = isXiuGai;
        this.notifyDataSetChanged();
    }

    /**
     * 组元素绑定器
     *
     *
     */
    private class GroupHolder {
        CheckBox iv_gouxuana;
        TextView tv_name;
    }
    /**
     * 子元素绑定器
     *
     *
     */
    private class ChildHolder {
        CheckBox iv_gouxuanb;
        ImageView iv_pic;
        TextView tv_jianjie;
        TextView tv_price,tv_num,tv_guigename;
        LinearLayout ll_compiles,ll_compile;
        TextView tv_number;
        RelativeLayout rl_reduce,rl_add,rl_xuanze;

    }
    /**
     * 复选框接口
     *
     *
     */
    public interface CheckInterface
    {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition
         *            组元素位置
         * @param isChecked
         *            组元素选中与否
         */
        public void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition
         *            组元素位置
         * @param childPosition
         *            子元素位置
         * @param isChecked
         *            子元素选中与否
         */
        public void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    /**
     * 改变数量的接口
     *
     *
     */
    public interface ModifyCountInterface
    {
        /**
         * 增加操作
         *
         * @param groupPosition
         *            组元素位置
         * @param childPosition
         *            子元素位置
         * @param showCountView
         *            用于展示变化后数量的View
         * @param isChecked
         *            子元素选中与否
         */
        public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param groupPosition
         *            组元素位置
         * @param childPosition
         *            子元素位置
         * @param showCountView
         *            用于展示变化后数量的View
         * @param isChecked
         *            子元素选中与否
         */
        public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);
    }
}
