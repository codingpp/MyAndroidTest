package com.codingpp.myandroid.myviews.motionevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * ViewGroup的事件方法
 *
 * @author sunpan
 * @date 2019-04-18
 */
public class TestViewGroup extends ViewGroup {
    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //进行事件的分发（传递）。返回值是 boolean 类型，受当前onTouchEvent和下级view的dispatchTouchEvent影响
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 对事件进行拦截。该方法只在ViewGroup中有，View（不包含 ViewGroup）是没有的。
        // 一旦拦截，则执行ViewGroup的onTouchEvent，在ViewGroup中处理事件，而不接着分发给View。
        // 且只调用一次，所以后面的事件都会交给ViewGroup处理
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //进行事件处理
        return super.onTouchEvent(event);
    }
}
