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
 * CachedThreadPool
 * newCachedThreadPool是线程数量是没有大小限制的，当新的线程来了直接创建，同样会造成资源消耗殆尽。
 *
 * @author sunpan
 * @date 2019-03-22
 */
@SuppressWarnings("unused")
public class CachedThreadPoolActivity extends AppCompatActivity {

    private TextView tvContent;

    private ExecutorService cachedThreadPool;

    private static final int HANDLER_SET_CONTENT = 0x001;

    private MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cached_thread_pool);
        initView();
        //CachedThreadPool中是没有核心线程的，但是它的最大线程数却为Integer.MAX_VALUE，
        //另外，它是有线程超时机制的，超时时间为60秒，这里它使用了SynchronousQueue作为线程队列。
        //由于最大线程数为无限大，所以每当我们添加一个新任务进来的时候，如果线程池中有空闲的线程，则由该空闲的线程执行新任务，如果没有空闲线程，则创建新线程来执行任务。
        //根据CachedThreadPool的特点，有大量任务请求的时候使用CachedThreadPool，因为当CachedThreadPool中没有新任务的时候，它里边所有的线程都会因为超时而被终止
        cachedThreadPool = Executors.newCachedThreadPool();
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
                execute1();
            }
        });
        tvContent = findViewById(R.id.tvContent);
    }

    /**
     * 执行线程方法1
     * 每次添加完任务之后我都停两秒在添加新任务，
     * 由于这里的任务执行不费时，这里所有的任务都使用同一个线程来执行
     * 因为每次添加新任务的时候都有空闲的线程
     */
    private void execute1() {
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
                }
            };
            cachedThreadPool.execute(runnable);
        }
    }

    /**
     * 执行线程方法2
     * 每个任务在执行的过程中都先休眠两秒，但是我向线程池添加任务则是每隔1s添加一个任务，
     * 这样的话，添加第一个任务时先开新线程，添加第二个任务时，由于第一个新线程尚未执行完，所以又开一个新线程，
     * 添加第三个任务时，第一个线程已经空闲下来了，直接第一个线程来执行第三个任务，依此类推
     */
    private void execute2() {
        int counter = 30;
        for (int i = 0; i < counter; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    Message message = new Message();
                    message.what = HANDLER_SET_CONTENT;
                    message.obj = finalI;
                    myHandler.sendMessage(message);
                }
            };
            cachedThreadPool.execute(runnable);
        }
    }

    private static final class MyHandler extends Handler {
        private WeakReference<CachedThreadPoolActivity> mReference;

        MyHandler(CachedThreadPoolActivity activity) {
            this.mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CachedThreadPoolActivity activity = mReference.get();
            if (msg.what == HANDLER_SET_CONTENT) {
                String oldText = activity.tvContent.getText().toString().trim();
                activity.tvContent.setText(oldText.concat("\n").concat("run:" + msg.obj));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cachedThreadPool.shutdownNow();
    }
}
