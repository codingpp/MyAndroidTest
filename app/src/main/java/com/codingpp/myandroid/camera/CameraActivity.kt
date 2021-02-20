package com.codingpp.myandroid.camera

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codingpp.myandroid.camera.takephoto.TakePhotoActivity
import com.codingpp.myandroid.databinding.ActivityCameraBinding

/**
 *
 * 测试首页
 * @author sunpan
 * @date 2019/2/27
 */
class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.btnTakePhoto.setOnClickListener {
            val intent = Intent(this, TakePhotoActivity::class.java)
            startActivity(intent)
        }
    }
}