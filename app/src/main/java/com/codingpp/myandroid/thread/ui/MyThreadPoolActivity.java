package com.codingpp.myandroid.thread.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codingpp.myandroid.R;
import com.codingpp.myandroid.thread.callback.ThreadPoolCallback;
import com.codingpp.myandroid.thread.threadpool.MyThreadPool;
import com.google.android.material.button.MaterialButton;

import java.lang.ref.WeakReference;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;


/**
 * 自定义线程池测试页面
 *
 * @author sunpan
 * @date 2019/3/22
 */
public class MyThreadPoolActivity extends AppCompatActivity implements ThreadPoolCallback {

    private TextView tvContent;

    private MyHandler myHandler = new MyHandler(this);

    private MyThreadPool myThreadPool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_thread_pool);
        initView();
        myThreadPool = new MyThreadPool(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), this);
    }

    private void initView() {
        MaterialButton btnExecute = findViewById(R.id.btnExecute);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvContent.setText("");
                execute();
            }
        });
        tvContent = findViewById(R.id.tvContent);
    }

    /**
     * 开始执行
     */
    private void execute() {
        int counter = 10;
        for (int i = 0; i < counter; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(100);
                    sendMessage("run:" + finalI);
                }
            };
            myThreadPool.execute(runnable);
        }
    }

    @Override
    public void beforeExecute() {
        sendMessage("beforeExecute: 开始执行任务！");
    }

    @Override
    public void afterExecute() {
        sendMessage("afterExecute: 任务执行结束！");
    }

    @Override
    public void terminated() {
        sendMessage("terminated: 线程池关闭！");
    }

    private void sendMessage(String str) {
        Message message = new Message();
        message.what = 1;
        message.obj = str;
        myHandler.sendMessage(message);
    }

    private static final class MyHandler extends Handler {
        private WeakReference<MyThreadPoolActivity> mReference;

        MyHandler(MyThreadPoolActivity activity) {
            this.mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MyThreadPoolActivity activity = mReference.get();
            if (msg.what == 1) {
                String oldText = activity.tvContent.getText().toString().trim();
                activity.tvContent.setText(oldText.concat("\n").concat((String) msg.obj));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myThreadPool.shutdownNow();
    }
}
