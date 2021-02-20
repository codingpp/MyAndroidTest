package com.codingpp.myandroid.observerdemo;

import java.util.Observable;
import java.util.Observer;

/**
 * 程序员是观察者
 *
 * @author sunpan
 * @date 2019/3/1
 */
public class Coder implements Observer {

    private String name;
    private Callback callback;

    Coder(String name, Callback callback) {
        this.name = name;
        this.callback = callback;
    }

    @Override
    public void update(Observable o, Object arg) {
        callback.postInfo("嘿，" + toString(name) + "，" + arg);
    }

    private String toString(String name) {
        return "程序员" + name;
    }
}
