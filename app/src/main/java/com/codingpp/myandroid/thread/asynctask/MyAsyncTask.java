package com.codingpp.myandroid.thread.asynctask;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.text.TextUtils;

import com.codingpp.myandroid.thread.callback.AsyncTaskCallback;


/**
 * MyAsyncTask
 *
 * @author sunpan
 * @date 2019/3/25
 */
public class MyAsyncTask extends AsyncTask<Void, Void, String> {

    private String params;
    private AsyncTaskCallback callback;

    public MyAsyncTask(String params, AsyncTaskCallback callback) {
        this.params = params;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        SystemClock.sleep(3000);
        return params.concat(" world");
    }

    @Override
    protected void onPostExecute(String s) {
        if (null != callback) {
            if (!TextUtils.isEmpty(s)) {
                callback.success(s);
            } else {
                callback.error(new Exception("模拟"));
            }
        }
    }
}
