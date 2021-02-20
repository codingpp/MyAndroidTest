package com.codingpp.myandroid.myviews.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 使用Canvas画背景色
 *
 * @author sunpan
 * @date 2019/2/21
 */
public class Practice1DrawColorView extends View {

    private Paint mPaint;

    public Practice1DrawColorView(Context context) {
        this(context, null);
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);

        mPaint.setTextSize(60);

        canvas.drawText("View大小：" + getWidth() + " * " + getHeight(), 60, 100, mPaint);
    }
}
