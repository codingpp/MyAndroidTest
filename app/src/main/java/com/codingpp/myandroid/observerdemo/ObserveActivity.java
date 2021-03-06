package com.codingpp.myandroid.observerdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.codingpp.myandroid.databinding.ActivityObserveBinding;

/**
 * 测试Observe
 *
 * @author sunpan
 */
public class ObserveActivity extends AppCompatActivity implements Callback {


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
            binding.tvObserver.setText("");
            //快递到了
            courier.postNewExpress("您的快递到啦！");
        });
        binding.btn2.setOnClickListener(v -> {
            binding.tvObserver.setText("");
            courier.postNewExpress("您发出的快递已被签收！");
        });
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
        String text = binding.tvObserver.getText().toString().concat("\n");
        binding.tvObserver.setText(text.concat(result));
    }
}
