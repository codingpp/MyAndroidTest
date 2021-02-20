package com.codingpp.myandroid.observerdemo;

/**
 * 通知回调
 *
 * @author sunpan
 * @date 2019/3/1
 */
public interface Callback {

    /**
     * 发送信息
     *
     * @param result 结果
     */
    void postInfo(String result);
}
