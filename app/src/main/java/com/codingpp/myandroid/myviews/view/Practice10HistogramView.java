package com.codingpp.myandroid.myviews.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 画直方图
 *
 * @author sunpan
 * @date 2019/2/22
 */
public class Practice10HistogramView extends View {

    private Paint mPaint;

    public Practice10HistogramView(Context context) {
        this(context, null);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //综合练习
        //练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.WHITE);
        canvas.drawLine(5, 50, 5, 700, mPaint);
        canvas.drawLine(5, 700, 1080, 700, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(70);
        canvas.drawText("直方图", (float) (getWidth() / 2) - 100, getHeight() - 10, mPaint);

        mPaint.setTextSize(30);
        canvas.drawText("Froyo", 35, 750, mPaint);
        canvas.drawText("Gingerbread", 130, 750, mPaint);
        canvas.drawText("Ice", 350, 750, mPaint);
        canvas.drawText("Jelly", 490, 750, mPaint);
        canvas.drawText("KitKat", 630, 750, mPaint);
        canvas.drawText("Lollipop", 770, 750, mPaint);
        canvas.drawText("Marshmallow", 900, 750, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(20, 650, 120, 700, mPaint);
        canvas.drawRect(170, 600, 270, 700, mPaint);
        canvas.drawRect(320, 600, 420, 700, mPaint);
        canvas.drawRect(470, 550, 570, 700, mPaint);
        canvas.drawRect(620, 500, 720, 700, mPaint);
        canvas.drawRect(770, 300, 870, 700, mPaint);
        canvas.drawRect(920, 660, 1020, 700, mPaint);
    }
}
