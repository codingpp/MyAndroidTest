package com.codingpp.myandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingpp.myandroid.cleancache.CleanActivity
import com.codingpp.myandroid.databinding.ActivityMainBinding
import com.codingpp.myandroid.handler.HandlerActivity
import com.codingpp.myandroid.lifecycle.logsample.ScrollingActivity
import com.codingpp.myandroid.myviews.MyViewsActivity
import com.codingpp.myandroid.observerdemo.ObserveActivity
import com.codingpp.myandroid.socket.SocketActivity
import com.codingpp.myandroid.thread.ThreadActivity

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
        binding.btnSocket.setOnClickListener {
            SocketActivity.jump(this)
        }
        binding.btnClean.setOnClickListener {
            CleanActivity.jump(this)
        }
        binding.btnHandler.setOnClickListener {
            HandlerActivity.jump(this)
        }
        binding.btnThread.setOnClickListener {
            ThreadActivity.jump(this)
        }
        binding.btnViews.setOnClickListener {
            MyViewsActivity.jump(this)
        }
        binding.btnLifecycle.setOnClickListener {
            ScrollingActivity.jump(this)
        }
    }
}