package com.example.administrator.dlwxnongxutong.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.dlwxnongxutong.R;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

import static android.media.CamcorderProfile.get;
import static com.alipay.sdk.app.statistic.c.v;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager vp_page;
    private List<Integer> pics = new ArrayList<>();
    private TextView tv_skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }
    private int tag = 0;
    private void initView() {
        vp_page = (ViewPager) findViewById(R.id.vp_page);
        tv_skip = (TextView) findViewById(R.id.tv_skip);
        pics.add(R.mipmap.yi);
        pics.add(R.mipmap.er);
        pics.add(R.mipmap.san);
        MyPageAdapter adapter = new MyPageAdapter();
        vp_page.setAdapter(adapter);
        vp_page.setOnPageChangeListener(this);
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                tag = 1;
                try {
                    thread.stop();
                } catch (Exception e) {
                    Log.e("wch", "Exception: ~~~~~~~~~~ ~~~~~~~~~~", e);
                }
                finish();
            }
        });
    }

    private class MyPageAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return pics.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = (ImageView) View.inflate(GuideActivity.this,
                    R.layout.item_page, null);
            Integer imageUrl = pics.get(position);
            Picasso.with(GuideActivity.this).load(imageUrl).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }

        private class ViewHolder {
            public View rootView;
            public ImageView iv_pic;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            }

        }
    }

    public GuideActivity() {
        super();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    private int  mm = 3;
    @Override
    public void onPageSelected(int position) {
        if (position == pics.size()-1) {


                tv_skip.setVisibility(View.VISIBLE);
                thread.start();

        }else{
            tv_skip.setVisibility(View.GONE);

        }
    }
 private  Thread thread = new Thread(){
        @Override
        public void run() {
            for (mm = 3;mm>=0;mm--){
                try {
                    sleep(1000);
                    if (mm == 0&&tag == 0) {
                        Intent intent  = new Intent(GuideActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    }else{
                        handler.sendEmptyMessage(0);
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
             switch (msg.what){
                        case 0:
                            tv_skip.setText(mm+" 跳过");
                            break;
                    }
        }
    };
}
