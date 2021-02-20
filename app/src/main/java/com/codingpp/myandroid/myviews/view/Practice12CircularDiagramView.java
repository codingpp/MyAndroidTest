package com.codingpp.myandroid.myviews.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
public class Practice12CircularDiagramView extends View {

    private Paint mPaint;

    public Practice12CircularDiagramView(Context context) {
        this(context, null);
    }

    public Practice12CircularDiagramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice12CircularDiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String[] colors = {"#F44336", "#FFC107", "#009688", "#9C27B0"};
        int[] data = {30, 40, 50, 90};
        RectF rectF = new RectF(-150, -150, 150, 150);
        int startAngle = -90;

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30);

        canvas.save();
        canvas.translate(350, 200);
        mPaint.setColor(Color.LTGRAY);
        canvas.drawCircle(0, 0, 150, mPaint);

        for (int i = 0; i < data.length; i++) {
            mPaint.setColor(Color.parseColor(colors[i]));
            canvas.drawArc(rectF, startAngle, data[i], false, mPaint);
            startAngle += data[i];
        }

    }
}
