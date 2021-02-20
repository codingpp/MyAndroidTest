package com.codingpp.myandroid.myviews.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 画圆
 *
 * @author sunpan
 * @date 2019/2/21
 */
public class Practice2DrawCircleView extends View {

    private Paint mPaint;

    public Practice2DrawCircleView(Context context) {
        this(context, null);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //初始画笔，设置抗锯齿效果
        //画笔颜色为黑
        mPaint.setColor(Color.BLACK);
        //画中心点坐标（200,200），半径为100圆，默认为实心圆
        canvas.drawCircle(200, 200, 100, mPaint);

        //第二个圆
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        canvas.drawCircle(600, 200, 80, mPaint);

        //第三个圆
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(200, 500, 100, mPaint);

        //第四个圆
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(500, 500, 100, mPaint);

    }
}
