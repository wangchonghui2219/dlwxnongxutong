package com.example.administrator.dlwxnongxutong.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.dlwxnongxutong.bean.shoppcarbean.InfoBean;

import java.util.List;


/**
 * 买家留言输入框监听
 */
public class EdiTextListender implements TextWatcher {
    private List<InfoBean> info;
    private int position;
    public EdiTextListender( List<InfoBean> info,int position){
            this.info = info;
        this.position = position;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    private String message;
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
            message = s.toString().trim();
    }

    @Override
    public void afterTextChanged(Editable s) {

        InfoBean infoBean = info.get(position);
        infoBean.setMess(message);

    }
}
