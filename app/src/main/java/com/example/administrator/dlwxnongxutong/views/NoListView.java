package com.example.administrator.dlwxnongxutong.views;/**
 * Created by scy on 2017/2/16.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name $
 */
public class NoListView extends ListView {
    public NoListView(Context context) {
        super(context);
    }

    public NoListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
