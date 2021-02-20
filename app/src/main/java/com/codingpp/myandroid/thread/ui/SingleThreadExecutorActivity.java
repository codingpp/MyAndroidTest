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
import com.google.android.material.button.MaterialButton;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * SingleThreadExecutor
 * singleThreadExecutor和FixedThreadPool很像，不同的就是SingleThreadExecutor的核心线程数只有1
 * 使用SingleThreadExecutor的一个最大好处就是可以避免我们去处理线程同步问题，
 * 其实如果我们把FixedThreadPool的参数传个1，效果就和SingleThreadExecutor一致了
 *
 * @author sunpan
 * @date 2019-03-22
 */
public class SingleThreadExecutorActivity extends AppCompatActivity {

    private TextView tvContent;

    private ExecutorService singleThreadExecutor;

    private static final int HANDLER_SET_CONTENT = 0x001;

    private MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_thread_executor);
        initView();
        singleThreadExecutor = Executors.newSingleThreadExecutor();
    }

    /**
     * 初始化view
     */
    private void initView() {
        tvContent = findViewById(R.id.tvContent);
        MaterialButton btnExecute = findViewById(R.id.btnExecute);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvContent.setText("");
                execute();
            }
        });
    }

    /**
     * 执行任务
     */
    private void execute() {
        int counter = 30;
        for (int i = 0; i < counter; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = HANDLER_SET_CONTENT;
                    message.obj = finalI;
                    myHandler.sendMessage(message);
                    SystemClock.sleep(1000);
                }
            };
            singleThreadExecutor.execute(runnable);
        }
    }

    private static final class MyHandler extends Handler {
        private WeakReference<SingleThreadExecutorActivity> mReference;

        MyHandler(SingleThreadExecutorActivity activity) {
            this.mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SingleThreadExecutorActivity activity = mReference.get();
            if (msg.what == HANDLER_SET_CONTENT) {
                String oldText = activity.tvContent.getText().toString().trim();
                activity.tvContent.setText(oldText.concat("\n").concat("run:" + msg.obj));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        singleThreadExecutor.shutdownNow();
    }
}
