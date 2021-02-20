package com.codingpp.myandroid.thread.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codingpp.myandroid.R;
import com.codingpp.myandroid.thread.factory.MyThreadFactory;
import com.google.android.material.button.MaterialButton;

import java.lang.ref.WeakReference;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * ScheduledThreadPool是一个具有定时定期执行任务功能的线程池
 * 它的核心线程数量是固定的（在构造的时候传入的），但是非核心线程是无穷大，当非核心线程闲置时，则会被立即回收
 *
 * @author sunpan
 * @date 2019-03-18
 */
@SuppressWarnings("unused")
public class ScheduledThreadPoolActivity extends AppCompatActivity {

    private static final String TAG = ScheduledThreadPoolActivity.class.getSimpleName();
    private static final int HANDLER_SET_CONTENT = 0x001;

    private TextView tvContent;

    private ScheduledExecutorService scheduledExecutorService;
    private MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_thread);
        initView();
        MyThreadFactory myThreadFactory = new MyThreadFactory(TAG);
        scheduledExecutorService = new ScheduledThreadPoolExecutor(3, myThreadFactory);
    }

    private void initView() {
        MaterialButton btnExecute = findViewById(R.id.btnExecute);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                execute1();
//                execute2();
                execute3();
            }
        });
        tvContent = findViewById(R.id.tvContent);
    }

    /**
     * 延迟启动任务
     */
    private void execute1() {
        String t = "execute1：延迟启动任务";
        tvContent.setText(t);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = HANDLER_SET_CONTENT;
                message.obj = "execute1：延迟启动任务执行了";
                myHandler.sendMessage(message);
            }
        };
        scheduledExecutorService.schedule(runnable, 1, TimeUnit.SECONDS);
    }

    /**
     * 延迟定时执行任务
     * 延迟initialDelay秒后每个period秒执行一次任务
     */
    private void execute2() {
        String t = "execute2:延迟定时执行任务";
        tvContent.setText(t);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = HANDLER_SET_CONTENT;
                message.obj = "execute2:延迟定时任务执行了";
                myHandler.sendMessage(message);
            }
        };
        //延迟1秒之后每隔1秒执行一次新任务
        scheduledExecutorService.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }

    /**
     * 延迟执行任务
     * 第一次延迟initialDelay秒，以后每次延迟delay秒执行一个任务
     */
    private void execute3() {
        String t = "execute3:延迟执行任务";
        tvContent.setText(t);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = HANDLER_SET_CONTENT;
                message.obj = "execute3:延迟执行任务执行了";
                myHandler.sendMessage(message);
            }
        };
        //第一次延迟1秒之后，以后每次也延迟1秒执行
        scheduledExecutorService.scheduleWithFixedDelay(runnable, 1, 1, TimeUnit.SECONDS);
    }

    private static final class MyHandler extends Handler {
        private WeakReference<ScheduledThreadPoolActivity> mReference;

        MyHandler(ScheduledThreadPoolActivity activity) {
            this.mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ScheduledThreadPoolActivity activity = mReference.get();
            if (msg.what == HANDLER_SET_CONTENT) {
                String oldText = activity.tvContent.getText().toString().trim();
                activity.tvContent.setText(oldText.concat("\n").concat((String) msg.obj));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scheduledExecutorService.shutdownNow();
    }
}
