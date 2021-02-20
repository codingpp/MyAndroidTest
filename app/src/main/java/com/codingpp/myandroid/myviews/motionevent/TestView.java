package com.codingpp.myandroid.myviews.motionevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 事件分发的两个方法
 *
 * @author sunpan
 * @date 2019-04-18
 */
public class TestView extends View {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //进行事件的分发（传递）
        // 返回值是 boolean 类型，受当前onTouchEvent和下级view的dispatchTouchEvent影响
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //进行事件处理
        return super.onTouchEvent(event);
    }
}
