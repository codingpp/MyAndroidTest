package com.codingpp.myandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingpp.myandroid.databinding.ActivityMainBinding
import com.codingpp.myandroid.observerdemo.ObserveActivity

/**
 * 主页
 * @author condingpp
 * @date 2021-02-20
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    /**
     * 初始化view
     */
    private fun initView() {
        binding.btnObserver.setOnClickListener {
            ObserveActivity.jump(this)
        }
    }
}