package com.codingpp.myandroid.thread.factory;

import java.util.concurrent.ThreadFactory;

/**
 * 线程工厂
 *
 * @author sunpan
 * @date 2019-03-21
 */
public class MyThreadFactory implements ThreadFactory {

    private String threadName;
    private int counter;

    public MyThreadFactory(String threadName) {
        counter = 0;
        this.threadName = threadName;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, threadName + "-thread-" + counter);
        counter++;
        return thread;
    }
}
