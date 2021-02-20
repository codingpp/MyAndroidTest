package com.codingpp.myandroid.thread.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codingpp.myandroid.R;
import com.codingpp.myandroid.thread.asynctask.MyAsyncTask;
import com.codingpp.myandroid.thread.callback.AsyncTaskCallback;
import com.google.android.material.button.MaterialButton;

/**
 * AsyncTask的测试页面
 *
 * @author sunpan
 * @date 2019/3/25
 */
public class AsyncTaskActivity extends AppCompatActivity implements AsyncTaskCallback {

    private TextView tvResult;
    private MyAsyncTask myAsyncTask;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        initView();
    }

    private void initView() {
        MaterialButton btnExecute = findViewById(R.id.btnExecute);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                tvResult.setText("");
                myAsyncTask = new MyAsyncTask("hello", AsyncTaskActivity.this);
                myAsyncTask.execute();
            }
        });
        tvResult = findViewById(R.id.tvResult);
        progressBar = findViewById(R.id.progress);
    }

    @Override
    public void success(String result) {
        progressBar.setVisibility(View.INVISIBLE);
        tvResult.setText(result);
    }

    @Override
    public void error(Exception e) {
        progressBar.setVisibility(View.INVISIBLE);
        tvResult.setText(e.getMessage());
    }
}
