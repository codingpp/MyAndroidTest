package com.codingpp.myandroid.dispatchevent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.codingpp.myandroid.R;

/**
 * 测试主页
 *
 * @author sunpan
 * @date 2020-3-8
 */
public class DispatchActivity extends AppCompatActivity {

    public static void jump(Context context) {
        Intent intent = new Intent(context, DispatchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        findViewById(R.id.view).setOnClickListener(v -> {
            //do nothing
        });
    }
}
