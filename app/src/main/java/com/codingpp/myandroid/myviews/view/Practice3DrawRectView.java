package com.codingpp.myandroid.myviews.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 画矩形
 *
 * @author sunpan
 * @date 2019/2/21
 */
public class Practice3DrawRectView extends View {

    private Paint mPaint;

    public Practice3DrawRectView(Context context) {
        this(context, null);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        //边框矩形
        canvas.drawRect(20, 20, 300, 300, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        //实心矩形
        canvas.drawRect(400, 20, 600, 500, mPaint);
    }
}
