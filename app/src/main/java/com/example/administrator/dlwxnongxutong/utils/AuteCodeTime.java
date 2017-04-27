package com.example.administrator.dlwxnongxutong.utils;/**
 * Created by scy on 2017/2/8.
 */

import android.widget.Button;

import static android.R.attr.filter;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name AuteCodeTme$
 */
public class AuteCodeTime {
        private static int time = 60;
        public static int tag = 0;

        public static void setTime(final Button code){
                new Thread(){
                    @Override

                    public void run() {

                            while(true){
                                try {
                                    sleep(1000);
                                    if (time <=0 ) {
                                        tag = 0;
                                        code.setText("获取验证码");
                                    }else{
                                        tag = 1;
                                        code.setText(time+"s");
                                    }

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    }
                }.start();
        }
}
