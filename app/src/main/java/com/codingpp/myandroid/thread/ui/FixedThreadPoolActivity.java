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
 * FixedThreadPool是一个核心线程数量固定的线程池
 *
 * @author sunpan
 * @date 2019-03-21
 */
public class FixedThreadPoolActivity extends AppCompatActivity {

    private TextView tvContent;
    private ExecutorService fixedThreadPool;

    private static final int HANDLER_SET_CONTENT = 0x001;

    private MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_thread);
        initView();
        //FixedThreadPool中没有非核心线程，所有的线程都是核心线程，且线程的超时时间为0，说明核心线程即使在没有任务可执行的时候也不会被销毁（这样可让FixedThreadPool更快速的响应请求）
        //最后的线程队列是一个LinkedBlockingQueue，但是LinkedBlockingQueue却没有参数，这说明线程队列的大小为Integer.MAX_VALUE（2的31次方减1）
        //当所有的核心线程都在执行任务的时候，新的任务只能进入线程队列中进行等待，直到有线程被空闲出来。
        fixedThreadPool = Executors.newFixedThreadPool(3);
        //！！！！newFixedThreadPool的阻塞队列大小是没有大小限制的，如果队列堆积数据太多会造成资源消耗。
    }

    /**
     * 初始化view
     */
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
     * 执行
     */
    private void execute() {
        int max = 30;
        for (int i = 0; i < max; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    //Thread.sleep会抛出interruptedException异常。SystemClock.sleep不会立即抛出异常,中断事件将要被延迟直到下一个中断事件
                    SystemClock.sleep(3000);
                    Message message = new Message();
                    message.what = HANDLER_SET_CONTENT;
                    message.obj = finalI;
                    myHandler.sendMessage(message);
                }
            };
            //先往核心线程中添加三个任务，剩余任务进入到workQueue中等待，当有空闲的核心线程时就执行任务队列中的任务
            fixedThreadPool.execute(runnable);
        }
    }

    private static final class MyHandler extends Handler {

        private WeakReference<FixedThreadPoolActivity> mReference;

        MyHandler(FixedThreadPoolActivity activity) {
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FixedThreadPoolActivity activity = mReference.get();
            if (msg.what == HANDLER_SET_CONTENT) {
                //更新文本显示
                String oldText = activity.tvContent.getText().toString().trim();
                activity.tvContent.setText(oldText.concat("\n").concat("run:" + msg.obj));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fixedThreadPool.shutdownNow();
    }
}
