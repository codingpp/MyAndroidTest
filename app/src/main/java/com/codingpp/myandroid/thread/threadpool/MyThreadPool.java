package com.codingpp.myandroid.thread.threadpool;

import com.codingpp.myandroid.thread.callback.ThreadPoolCallback;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 自定义线程池
 *
 * @author sunpan
 * @date 2019/3/22
 */
public class MyThreadPool extends ThreadPoolExecutor {

    private ThreadPoolCallback callback;

    public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadPoolCallback callback) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.callback = callback;
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        callback.beforeExecute();
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        callback.afterExecute();
    }

    @Override
    protected void terminated() {
        super.terminated();
        //当调用shutDown()或者shutDownNow()时会触发该方法
        callback.terminated();
    }
}
