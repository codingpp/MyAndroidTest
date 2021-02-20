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
 * 圆角矩形
 *
 * @author sunpan
 * @date 2019/2/21
 */
public class Practice7DrawRoundRectView extends View {

    private Paint mPaint;

    public Practice7DrawRoundRectView(Context context) {
        this(context, null);
    }

    public Practice7DrawRoundRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice7DrawRoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //练习内容：使用 canvas.drawRoundRect() 方法画圆角矩形
        mPaint.setColor(Color.GREEN);
        canvas.drawRoundRect(100, 50, 900, 500, 60, 60, mPaint);
    }

}
