package com.codingpp.myandroid.thread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.codingpp.myandroid.databinding.ActivityThreadMainBinding;
import com.codingpp.myandroid.thread.ui.AsyncTaskActivity;
import com.codingpp.myandroid.thread.ui.CachedThreadPoolActivity;
import com.codingpp.myandroid.thread.ui.DefaultThreadPoolActivity;
import com.codingpp.myandroid.thread.ui.FixedThreadPoolActivity;
import com.codingpp.myandroid.thread.ui.MyThreadPoolActivity;
import com.codingpp.myandroid.thread.ui.ScheduledThreadPoolActivity;
import com.codingpp.myandroid.thread.ui.SingleThreadExecutorActivity;

/**
 * 线程池测试（来自https://blog.csdn.net/u012702547/article/details/52259529）
 * <p>
 * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式更加明确线程池的运行规则，规避资源耗尽的风险。
 * 说明: Executors各 个方法的弊端:
 * 1) newFixedThreadPoo L和newSingleThreadExecutor:主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至00M
 * 2) newCachedThreadPoo 1和newScheduledThreadPool:主要问题是线程数最大数是Integer.MAX_VALUE, 可能会创建数量非常多的线程，甚至00M。
 *
 * @author sunpan
 * @date 2019/3/18
 */
public class ThreadActivity extends AppCompatActivity {

    private ActivityThreadMainBinding binding;

    public static void jump(Context context) {
        Intent intent = new Intent(context, ThreadActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThreadMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.btnScheduled.setOnClickListener(view -> startActivity(ScheduledThreadPoolActivity.class));
        binding.btnThreadPool.setOnClickListener(view -> startActivity(DefaultThreadPoolActivity.class));
        binding.btnFixedThread.setOnClickListener(view -> startActivity(FixedThreadPoolActivity.class));
        binding.btnSingleThreadExecutor.setOnClickListener(view -> startActivity(SingleThreadExecutorActivity.class));
        binding.btnCachedThreadPool.setOnClickListener(view -> startActivity(CachedThreadPoolActivity.class));
        binding.btnMyThreadPool.setOnClickListener(view -> startActivity(MyThreadPoolActivity.class));
        binding.btnMyAsyncTask.setOnClickListener(view -> startActivity(AsyncTaskActivity.class));
    }

    /**
     * 启动activity
     *
     * @param cls 目标activity的class
     */
    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

}

//线程池其他常用功能
//1.shutDown()  关闭线程池，不影响已经提交的任务
//2.shutDownNow() 关闭线程池，并尝试去终止正在执行的线程
//3.allowCoreThreadTimeOut(boolean value) 允许核心线程闲置超时时被回收
//4.submit 一般情况下我们使用execute来提交任务，但是有时候可能也会用到submit，使用submit的好处是submit有返回值，举个栗子：
// 使用submit时我们可以通过实现Callable接口来实现异步任务。在call方法中执行异步任务，返回值即为该任务的返回值。Future是返回结果，返回它的isDone属性表示异步任务执行成功
//5.自定义线程池,除了使用submit来定义线程池获取线程执行结果之外，我们也可以通过自定义ThreadPoolExecutor来实现这个功能
