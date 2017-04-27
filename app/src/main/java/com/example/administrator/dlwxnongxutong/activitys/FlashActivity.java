package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.administrator.dlwxnongxutong.R;
import com.example.administrator.dlwxnongxutong.utils.SPUtils;

public class FlashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        SharedPreferences sp = getSharedPreferences(SPUtils.SP_MODE,MODE_PRIVATE);
        final int first = sp.getInt(SPUtils.FIRST, 0);
        new Thread(){

            private Intent intent;

            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                    if (first == 0) {

                        intent = new Intent(FlashActivity.this,GuideActivity.class);
                    }else{
                        intent = new Intent(FlashActivity.this,MainActivity.class);
                    }
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
