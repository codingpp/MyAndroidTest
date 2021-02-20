package com.codingpp.myandroid.lifecycle.logsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.codingpp.myandroid.R;
import com.codingpp.myandroid.databinding.ActivityScrollingBinding;
import com.codingpp.myandroid.lifecycle.networksample.SampleActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Lifecycle测试页面
 *
 * @author sunpan
 * @date 2019/2/26
 */
public class ScrollingActivity extends AppCompatActivity {

    private ActivityScrollingBinding binding;

    public static void jump(Context context) {
        Intent intent = new Intent(context, ScrollingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(ScrollingActivity.this, SampleActivity.class)));

        //注册观察者
        getLifecycle().addObserver(new MyObserver());
    }
}
