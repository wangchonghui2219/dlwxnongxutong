package com.example.administrator.dlwxnongxutong.utils;


import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.CartBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.InfoBean;
import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.UpJsonCarBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 购物车修改信息json
 */
public class MyJsonWriter {
    List<CartBean> folks;
    File saveFile;
    public MyJsonWriter(List<CartBean> folks){
        this.folks=folks;
    }

    public void setFilePath(String filepath){
        saveFile=new File(filepath);
        try {
            saveFile.createNewFile();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public String getJsonData(){
        String jsonData = null;
        //      String jsonData=new JSONStringer().object().key("village").value("abc").endObject().toString();
        try {
            StringBuilder builder=new StringBuilder();
            ArrayList<String> folksData=new ArrayList<String>();
            JSONArray array=new JSONArray();
            for(int i=0;i<folks.size();i++){
                JSONObject object = new JSONObject();
                    CartBean cartBean = folks.get(i);
                    object.put("gctid",cartBean.getGctid());
                    object.put("gnum",cartBean.getGnum());
                    object.put("spid",cartBean.getPid());

                    array.put(object);
            }
            int len =   array.length();
            jsonData=new JSONStringer().object().key("ct").value(array).endObject().toString();
            System.out.println(jsonData);
//            writeData(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
    public JSONArray jsonArrayItem (List<CartBean> cart){
        String jsonCart = null;
        try {
        StringBuilder builder = new StringBuilder();
        JSONArray array = new JSONArray();
        for (int i = 0;i<cart.size();i++){
            CartBean cartBean = cart.get(i);
            JSONObject object = new JSONObject();
                object.put("gctid",cartBean.getGctid());
            object.put("gnum",cartBean.getGnum());
            object.put("spid",cartBean.getPid());
            array.put(object);
            jsonCart = new JSONStringer().object().key("cart").value(array).endObject().toString();
            return array;
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void writeData(String jsonData) {

        try {
            BufferedReader reader=new BufferedReader(new StringReader(jsonData));
            BufferedWriter writer=new BufferedWriter(new FileWriter(saveFile));
            int len=0;
            char[] buffer=new char[1024];
            while((len=reader.read(buffer))!=-1){
                writer.write(buffer, 0, len);
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
