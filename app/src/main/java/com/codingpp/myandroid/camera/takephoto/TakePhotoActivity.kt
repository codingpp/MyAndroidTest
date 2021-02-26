package com.codingpp.myandroid.camera.takephoto

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.codingpp.myandroid.databinding.ActivityTakePhotoBinding
import com.zhy.base.fileprovider.FileProvider7
import java.io.File

/**
 * 拍照测试页面
 * @author sunpan
 * @date 2018/11/12
 */
class TakePhotoActivity : AppCompatActivity() {

    companion object {
        fun jump(context: Context) {
            val intent = Intent(context, TakePhotoActivity::class.java)
            context.startActivity(intent)
        }
    }

    /**
     * 用于保存拍照后的文件
     */
    private lateinit var image: File

    /**
     * 需要请求的权限
     */
    private var permissions =
        arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    /**
     * 权限请求返回码
     */
    private val PERMISSION_REQUEST_CODE = 0x001

    /**
     * 拍照返回码
     */
    private val CAMERA_REQUEST_CODE = 0x002

    /**
     * 裁切返回码
     */
    private val CROP_REQUEST_CODE = 0x003

    /**
     * 相册获取返回码
     */
    private val ALBUM_REQUEST_CODE = 0x004

    private lateinit var binding: ActivityTakePhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTakePhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestPermission()
        initView()
    }

    /**
     *  请求权限
     */
    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //android版本大于23，需要请求拍照及存储权限
            val cameraPermission = ActivityCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            )
            val storagePermission = ActivityCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if (cameraPermission != PackageManager.PERMISSION_GRANTED || storagePermission != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE)
            }
        }
    }

    /**
     * 初始化view
     */
    private fun initView() {
        //拍照
        binding.btnTakePhoto.setOnClickListener {
            getPicFromCamera()
        }
        //相册
        binding.btnAlbum.setOnClickListener {
            getPicFromAlbum()
        }
        val imageName = "image.jpg"
        //拍照图片的保存路径
        image = File(
            externalCacheDir?.absolutePath, imageName
        )
        if (!image.exists()) {
            //文件不存在，创建文件夹
            image.parentFile.mkdirs()
        } else {
            //获取保存的图片为bitmap，并设置到ImageView
            binding.imgContent.setImageBitmap(BitmapFactory.decodeFile(image.absolutePath))
        }
    }

    /**
     * 拍照获取图片
     */
    private fun getPicFromCamera() {
        //跳转到系统相机
        val intent = Intent(
            MediaStore.ACTION_IMAGE_CAPTURE
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider7.getUriForFile(this, image))
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    /**
     * 从相册获取图片
     */
    private fun getPicFromAlbum() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE)
    }

    /**
     * 回调接口 //TODO ActivityResultContract替换
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {
                    //用相机返回的照片调用裁剪也需要对Uri处理
                    cropPhoto(FileProvider7.getUriForFile(this@TakePhotoActivity, image))
                }

                ALBUM_REQUEST_CODE -> {
                    val uri = data?.data
                    uri?.let {
                        cropPhoto(uri)
                    }
                }

                CROP_REQUEST_CODE -> {
                    if (image.exists()) {
                        //图片存在，设置到ImageView
                        binding.imgContent.setImageBitmap(BitmapFactory.decodeFile(image.absolutePath))
                    }
                }

                else -> {

                }
            }
        }
    }

    /**
     * 裁剪图片
     */
    private fun cropPhoto(contentUri: Uri) {
        val uriTempFile = Uri.parse("file://".plus(image.absolutePath))

        val intent = Intent("com.android.camera.action.CROP")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        intent.setDataAndType(contentUri, "image/*")
        intent.putExtra("crop", true)
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)
        intent.putExtra("outputX", 300)
        intent.putExtra("outputY", 300)
        intent.putExtra("return-data", false)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriTempFile)
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
        intent.putExtra("noFaceDetection", true)
        startActivityForResult(intent, CROP_REQUEST_CODE)
    }
}
