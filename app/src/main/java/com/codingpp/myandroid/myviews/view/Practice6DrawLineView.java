package com.codingpp.myandroid.myviews.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 画线
 *
 * @author sunpan
 * @date 2019/2/21
 */
public class Practice6DrawLineView extends View {

    private Paint mPaint;

    public Practice6DrawLineView(Context context) {
        this(context, null);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(6);
        mPaint.setColor(Color.RED);
        //一条斜线
        canvas.drawLine(100, 100, 900, 200, mPaint);

        //画一个"工"字
        mPaint.setColor(Color.GREEN);
        float[] points = {250, 250, 400, 250, 250, 400, 400, 400, 325, 250, 325, 400};
        canvas.drawLines(points, mPaint);

        //画一个"口"字
        mPaint.setColor(Color.MAGENTA);
        float[] p = {500, 500, 600, 500, 500, 500, 500, 600, 600, 500, 600, 600, 500, 600, 600, 600};
        canvas.drawLines(p, mPaint);
    }
}
