package com.codingpp.myandroid.thread.callback;

/**
 * 回调
 *
 * @author sunpan
 * @date 2019/3/25
 */
public interface AsyncTaskCallback {

    /**
     * 成功
     *
     * @param result 结果
     */
    void success(String result);

    /**
     * 失败
     *
     * @param e 异常信息
     */
    void error(Exception e);

}
