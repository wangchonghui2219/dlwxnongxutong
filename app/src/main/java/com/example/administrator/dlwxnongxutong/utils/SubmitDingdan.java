package com.example.administrator.dlwxnongxutong.utils;


import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.CartBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.InfoBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.UpJsonCarBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
/**
 * 提交订单，writejson
 */
import java.util.ArrayList;
import java.util.List;

public class SubmitDingdan {
    /**
     * 提交单个商品信息
     * @param info
     * @return
     */
    public String getJsonData(List<InfoBean> info,String odyid,String gsid){
        String jsonData = null;

        try {
            StringBuilder builder=new StringBuilder();
            ArrayList<String> folksData=new ArrayList<String>();
            JSONObject array=new JSONObject();
            for(int i=0;i<info.size();i++){
                InfoBean carBean=info.get(i);
                JSONObject jsonObject=new JSONObject();
                List<CartBean> cart = carBean.getCart();
                //TODO
                array.put("goodsid",carBean.getCart().get(0).getGid());
                array.put("bid",carBean.getCart().get(0).getBid());
                array.put("msg",carBean.getMess());
                array.put("way",odyid);
                if ("1".equals( cart.get(0).getIsintpay())) {

                    array.put("jf",cart.get(0).getIntlpay());
                }
                array.put("spid",gsid);
                array.put("gnum",carBean.getCart().get(0).getGnum());

//                carBean.getCart().get(0).get
            }
            //          JSONArray jsonArray=new JSONArray(folksData);
            int len =   array.length();
            jsonData=new JSONStringer().object().key("order").value(array).endObject().toString();
            System.out.println(jsonData);
//            writeData(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    /**
     * 提交购物车商品信息
     * @param info
     * @return
     */
    public String getCartJsonData(List<InfoBean> info,String odyid,String gsid){
        String jsonData = null;

        try {
            StringBuilder builder=new StringBuilder();
            ArrayList<String> folksData=new ArrayList<String>();
            JSONArray array=new JSONArray();
            for(int i=0;i<info.size();i++){
                InfoBean carBean=info.get(i);
                JSONObject jsonObject=new JSONObject();
                List<CartBean> cart = carBean.getCart();

                jsonObject.put("bid",carBean.getCart().get(0).getBid());
                jsonObject.put("msg",carBean.getMess());
                jsonObject.put("way",odyid);
                jsonObject.put("cart",getCartJson(cart));
//                carBean.getCart().get(0).get
                array.put(jsonObject);
            }
            //          JSONArray jsonArray=new JSONArray(folksData);
            int len =   array.length();
            jsonData=new JSONStringer().object().key("cart").value(array).endObject().toString();
            System.out.println(jsonData);
//            writeData(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public JSONArray getCartJson(List<CartBean> cart){
        String jsonData = null;
        try {
            StringBuilder builder=new StringBuilder();
            ArrayList<String> folksData=new ArrayList<String>();
            JSONArray array=new JSONArray();
            for(int i=0;i<cart.size();i++){
                CartBean cartBean = cart.get(i);
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("id",cartBean.getGctid());
                if ("1".equals( cartBean.getIsintpay())) {
                    jsonObject.put("jf",cartBean.getIntlpay());
                }


                array.put(jsonObject);

//                carBean.getCart().get(0).get
            }

            return array;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }



}
