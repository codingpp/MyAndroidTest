package com.codingpp.myandroid.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.codingpp.myandroid.databinding.ActivityHandlerBinding;

import java.lang.ref.WeakReference;

/**
 * 测试AndroidHandler
 *
 * @author sunpan
 * @date 2019-4-19
 */
public class HandlerActivity extends AppCompatActivity {

    private static final String TAG = HandlerActivity.class.getSimpleName();

    private MyHandler myHandler = new MyHandler(Looper.getMainLooper(), this);

    private ActivityHandlerBinding binding;

    public static void jump(Context context) {
        Intent intent = new Intent(context, HandlerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHandlerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //一般来说在工作线程中执行耗时任务，当任务完成时，会返回UI线程，一般是更新UI。这时有两种方法可以达到目的。
        //一种是handler.post(r)。r是要执行的任务代码。意思就是说r的代码实际是在UI线程执行的。可以写更新UI的代码。（工作线程是不能更新UI的）
        Log.e(TAG, "run: " + Thread.currentThread().getName());
        new Handler(Looper.getMainLooper()).post(() -> Log.e(TAG, "runPost: " + Thread.currentThread().getName()));
        //另一种是handler.sendMessage。发一个消息，再根据消息，执行相关任务代码。
        myHandler.sendEmptyMessage(1);
    }

    private static final class MyHandler extends Handler {

        private WeakReference<HandlerActivity> mReference;

        public MyHandler(Looper looper, HandlerActivity activity) {
            super(looper);
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Log.e(TAG, "handleMessage: " + Thread.currentThread().getName());
            }
        }
    }
}
