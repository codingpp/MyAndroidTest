package com.codingpp.myandroid.myviews.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 画点
 *
 * @author sunpan
 * @date 2019/2/21
 */
public class Practice4DrawPointView extends View {

    private Paint mPaint;

    public Practice4DrawPointView(Context context) {
        this(context, null);
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置为圆点
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置点的大小
        mPaint.setStrokeWidth(100);
        canvas.drawPoint(100, 100, mPaint);

        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStrokeWidth(80);
        canvas.drawPoint(400, 200, mPaint);

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(20);
        float[] points = {400, 400, 450, 450, 450, 500, 500, 450, 500, 500, 550, 450, 550, 500};
        //跳过两个数，即前两个400 ,一共绘制8个数（4个点）
        canvas.drawPoints(points, 2, 8, mPaint);
    }
}
