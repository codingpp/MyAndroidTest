package com.codingpp.myandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingpp.myandroid.cleancache.CleanActivity
import com.codingpp.myandroid.databinding.ActivityMainBinding
import com.codingpp.myandroid.observerdemo.ObserveActivity
import com.codingpp.myandroid.socket.SocketActivity

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
    }
}