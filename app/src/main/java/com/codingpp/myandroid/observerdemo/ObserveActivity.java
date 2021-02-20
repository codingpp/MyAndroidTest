package com.codingpp.myandroid.observerdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.codingpp.myandroid.R;
import com.codingpp.myandroid.databinding.ActivityObserveBinding;
import com.google.android.material.button.MaterialButton;

/**
 * 测试Observe
 *
 * @author sunpan
 */
public class ObserveActivity extends AppCompatActivity implements Callback {

    private TextView tvObserver;

    private Courier courier;

    private ActivityObserveBinding binding;

    /**
     * 跳转到observer页面
     *
     * @param context ctx
     */
    public static void jump(Context context) {
        Intent intent = new Intent(context, ObserveActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityObserveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initData();
    }

    /**
     * 初始化view
     */
    private void initView() {
        binding.btnObservable.setOnClickListener(v -> {
            tvObserver.setText("");
            //快递到了
            courier.postNewExpress("您的快递到啦！");
        });
        MaterialButton btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(v -> {
            tvObserver.setText("");
            courier.postNewExpress("您发出的快递已被签收！");
        });
        tvObserver = findViewById(R.id.tvObserver);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //观察者
        Coder coder1 = new Coder("张三", this);
        Coder coder2 = new Coder("李四", this);
        Coder coder3 = new Coder("王五", this);
        //被观察者
        courier = new Courier();

        //将观察者注册到被观察者的观察者列表中
        courier.addObserver(coder1);
        courier.addObserver(coder2);
        courier.addObserver(coder3);
    }

    @Override
    public void postInfo(String result) {
        String text = tvObserver.getText().toString().concat("\n");
        tvObserver.setText(text.concat(result));
    }
}
