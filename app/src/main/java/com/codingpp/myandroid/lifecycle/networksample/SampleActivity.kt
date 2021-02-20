package com.codingpp.myandroid.lifecycle.networksample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codingpp.myandroid.databinding.ActivitySampleBinding

/**
 *  网络监听测试Activity
 * @author sunpan
 * @date 2019/2/26
 */
class SampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val observer = NetworkObserver(this)
        observer.listener = { type: Int ->
            var result = "当前网络连接为: "
            result = when (type) {
                NetworkObserver.NETWORK_WIFI -> result.plus("WIFI")
                NetworkObserver.NETWORK_MOBILE -> result.plus("移动网络")
                NetworkObserver.NETWORK_NONE -> result.plus("无网络连接")
                else -> result.plus("未知网络连接")
            }
            binding.tvNetStatus.text = result
        }
        lifecycle.addObserver(observer)
    }
}