package com.codingpp.myandroid.observerdemo;

import java.util.Observable;

/**
 * Courier 即快递员，当他到公司时通知所有观察者（有快递的程序员）拿快递
 *
 * @author sunpan
 * @date 2019/3/1
 */
class Courier extends Observable {

    void postNewExpress(String content) {
        //标识状态或者内容发生改变（此处为快递到了）
        setChanged();

        //通知所有观察者
        notifyObservers(content);
    }

}
