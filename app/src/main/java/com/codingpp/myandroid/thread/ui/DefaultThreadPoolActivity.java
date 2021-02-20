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
import com.codingpp.myandroid.thread.factory.MyThreadFactory;
import com.google.android.material.button.MaterialButton;

import java.lang.ref.WeakReference;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



/**
 * 基础的线程池
 *
 * @author sunpan
 * @date 2019-03-18
 */
public class DefaultThreadPoolActivity extends AppCompatActivity {

    private static final String TAG = DefaultThreadPoolActivity.class.getSimpleName();

    private TextView tvContent;
    private ThreadPoolExecutor threadPoolExecutor;

    private static final int HANDLER_SET_CONTENT = 0x001;
    private MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);
        initView();
        MyThreadFactory myThreadFactory = new MyThreadFactory(TAG);
        //corePoolSize  线程池中核心线程的数量
        //maximumPoolSize  线程池中最大线程数量
        //keepAliveTime 非核心线程的超时时长，当系统中非核心线程闲置时间超过keepAliveTime之后，则会被回收。如果ThreadPoolExecutor的allowCoreThreadTimeOut属性设置为true，则该参数也表示核心线程的超时时长
        //unit 第三个参数的单位，有纳秒、微秒、毫秒、秒、分、时、天等
        //workQueue 线程池中的任务队列，该队列主要用来存储已经被提交但是尚未执行的任务。存储在这里的任务是由ThreadPoolExecutor的execute方法提交来的
        //threadFactory  为线程池提供创建新线程的功能，这个我们一般使用默认即可
        //handler 拒绝策略，当线程无法执行新任务时（一般是由于线程池中的线程数量已经达到最大数或者线程池关闭导致的），默认情况下，当线程池无法处理新线程时，会抛出一个RejectedExecutionException
        threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(128), myThreadFactory);

        //针对于workQueue参数：workQueue是一个BlockingQueue类型，
        // 它是一个特殊的队列，当我们从BlockingQueue中取数据时，如果BlockingQueue是空的，则取数据的操作会进入到阻塞状态，
        // 当BlockingQueue中有了新数据时，这个取数据的操作又会被重新唤醒。
        // 同理，如果BlockingQueue中的数据已经满了，往BlockingQueue中存数据的操作又会进入阻塞状态，直到BlockingQueue中又有新的空间，存数据的操作又会被冲洗唤醒。
        // BlockingQueue有多种不同的实现类：
        //1.ArrayBlockingQueue：这个表示一个规定了大小的BlockingQueue，ArrayBlockingQueue的构造函数接受一个int类型的数据，该数据表示BlockingQueue的大小，存储在ArrayBlockingQueue中的元素按照FIFO（先进先出）的方式来进行存取。
        //2.LinkedBlockingQueue：这个表示一个大小不确定的BlockingQueue，在LinkedBlockingQueue的构造方法中可以传一个int类型的数据，这样创建出来的LinkedBlockingQueue是有大小的，也可以不传，不传的话，LinkedBlockingQueue的大小就为Integer.MAX_VALUE，源码如下：
        //3. PriorityBlockingQueue：这个队列和LinkedBlockingQueue类似，不同的是PriorityBlockingQueue中的元素不是按照FIFO来排序的，而是按照元素的Comparator来决定存取顺序的（这个功能也反映了存入PriorityBlockingQueue中的数据必须实现了Comparator接口）。
        //4. SynchronousQueue：这个是同步Queue，属于线程安全的BlockingQueue的一种，在SynchronousQueue中，生产者线程的插入操作必须要等待消费者线程的移除操作，Synchronous内部没有数据缓存空间，因此我们无法对SynchronousQueue进行读取或者遍历其中的数据，元素只有在你试图取走的时候才有可能存在。我们可以理解为生产者和消费者互相等待，等到对方之后然后再一起离开。
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
     * 执行
     */
    private void execute() {
        int count = 30;
        for (int i = 0; i < count; i++) {
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
            threadPoolExecutor.execute(runnable);
        }
        //遵循如下规则：
        //1.execute一个线程之后，如果线程池中的线程数未达到核心线程数，则会立马启用一个核心线程去执行
        //2.execute一个线程之后，如果线程池中的线程数已经达到核心线程数，且workQueue未满，则将新线程放入workQueue中等待执行
        //3.execute一个线程之后，如果线程池中的线程数已经达到核心线程数但未超过非核心线程数，且workQueue已满，则开启一个非核心线程来执行任务
        //4.execute一个线程之后，如果线程池中的线程数已经超过非核心线程数，则拒绝执行该任务
    }

    private static final class MyHandler extends Handler {

        private WeakReference<DefaultThreadPoolActivity> mActivity;

        MyHandler(DefaultThreadPoolActivity threadPoolActivity) {
            mActivity = new WeakReference<>(threadPoolActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DefaultThreadPoolActivity activity = mActivity.get();
            if (msg.what == HANDLER_SET_CONTENT) {
                String oldText = activity.tvContent.getText().toString().trim();
                activity.tvContent.setText(oldText.concat("\n").concat("run:" + msg.obj));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        threadPoolExecutor.shutdownNow();
    }
}
