package com.codingpp.myandroid.dispatchevent.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.codingpp.myandroid.dispatchevent.util.LogUtil;


/**
 * 自定义的View
 *
 * @author coding
 * @date 2020/3/8
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.log("MyView dispatchTouchEvent ");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.log("MyView onTouchEvent ");
        //false 表示MyView不处理该事件
//        return false;
        return super.onTouchEvent(event);
    }
}
