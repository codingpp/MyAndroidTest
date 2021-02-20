package com.codingpp.myandroid.myviews.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * 使用drawPath画自定义图形
 *
 * @author sunpan
 * @date 2019/2/22
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Practice9DrawPathView extends View {

    private Paint mPaint;
    private Path mPath;

    public Practice9DrawPathView(Context context) {
        this(context, null);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画左半边扇形
        mPath.addArc(200, 200, 400, 400, -225, 225);
        //画右半边扇形，并自动封闭
        mPath.arcTo(400, 200, 600, 400, -180, 225, false);
        //画线并自动封闭
        mPath.lineTo(400, 542);

        mPaint.setColor(Color.RED);
        //练习内容：使用 canvas.drawPath() 方法画心形
        canvas.drawPath(mPath, mPaint);

    }
}
