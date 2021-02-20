package com.codingpp.myandroid.thread.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codingpp.myandroid.databinding.ActivityAsyncTaskBinding;
import com.codingpp.myandroid.thread.asynctask.MyAsyncTask;
import com.codingpp.myandroid.thread.callback.AsyncTaskCallback;

/**
 * AsyncTask的测试页面
 *
 * @author sunpan
 * @date 2019/3/25
 */
public class AsyncTaskActivity extends AppCompatActivity implements AsyncTaskCallback {

    private MyAsyncTask myAsyncTask;

    private ActivityAsyncTaskBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAsyncTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.btnExecute.setOnClickListener(v -> {
            binding.progress.setVisibility(View.VISIBLE);
            binding.tvResult.setText("");
            myAsyncTask = new MyAsyncTask("hello", AsyncTaskActivity.this);
            myAsyncTask.execute();
        });
    }

    @Override
    public void success(String result) {
        binding.progress.setVisibility(View.INVISIBLE);
        binding.tvResult.setText(result);
    }

    @Override
    public void error(Exception e) {
        binding.progress.setVisibility(View.INVISIBLE);
        binding.tvResult.setText(e.getMessage());
    }
}
