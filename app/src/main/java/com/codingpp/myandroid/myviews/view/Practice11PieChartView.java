package com.codingpp.myandroid.myviews.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * 饼状图
 *
 * @author sunpan
 * @date 2019/2/25
 */
public class Practice11PieChartView extends View {

    private Paint mPaint;

    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(50);
        canvas.drawText("饼图", (float) (getWidth() / 2 - 50), getHeight() - 10, mPaint);

        int left = 250, top = 100, right = 800, bottom = 650;
        int offset = 15;

        //练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(left, top, right, bottom, -180, 120, true, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(left + offset, top + offset, right + offset, bottom + offset, -60, 55, true, mPaint);

        mPaint.setColor(Color.LTGRAY);
        canvas.drawArc(left + offset, top + offset, right + offset, bottom + offset, -3, 5, true, mPaint);

        mPaint.setColor(Color.MAGENTA);
        canvas.drawArc(left + offset, top + offset, right + offset, bottom + offset, 3, 5, true, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawArc(left + offset, top + offset, right + offset, bottom + offset, 10, 60, true, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawArc(left + offset, top + offset, right + offset, bottom + offset, 72, 107, true, mPaint);


    }
}
