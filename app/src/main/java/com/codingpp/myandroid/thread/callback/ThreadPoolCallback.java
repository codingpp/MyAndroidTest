package com.codingpp.myandroid.thread.callback;

/**
 * 线程执行回调
 *
 * @author sunpan
 * @date 2019/3/22
 */
public interface ThreadPoolCallback {

    /**
     * 执行前
     */
    void beforeExecute();

    /**
     * 执行后
     */
    void afterExecute();

    /**
     * 终止
     */
    void terminated();
}
