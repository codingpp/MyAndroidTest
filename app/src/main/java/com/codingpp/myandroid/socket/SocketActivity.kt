package com.codingpp.myandroid.socket

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.codingpp.myandroid.databinding.ActivitySocketBinding
import java.io.*
import java.lang.ref.WeakReference
import java.net.Socket
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Android Socket测试demo
 * @author sunpan
 * @date 2019-2-29
 */
class SocketActivity : AppCompatActivity(), Runnable {

    companion object {
        fun jump(context: Context) {
            val intent = Intent(context, SocketActivity::class.java)
            context.startActivity(intent)
        }
    }

    /**
     * 端口号
     */
    val PORT = 8888

    private lateinit var socket: Socket
    private lateinit var buffer: BufferedReader
    private lateinit var out: PrintWriter
    private var myHandler: MyHandler = MyHandler(this)

    private var threadPool =
        ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, LinkedBlockingDeque<Runnable>())

    private lateinit var binding: ActivitySocketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSend.setOnClickListener {
            val ip = binding.etHost.text
            if (TextUtils.isEmpty(ip)) {
                Toast.makeText(this, "请输入ip地址", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val msg = binding.etMsg.text
            with(socket) {
                this.isConnected.let {
                    //如果服务器连接且输出流没有断开
                    if (isConnected && !isOutputShutdown) {
                        //点击按钮发送消息
                        val runnable = Runnable {
                            out.println(msg)
                        }
                        threadPool.execute(runnable)
                        //清空编辑框
                        binding.etMsg.setText("")
                    }
                }
            }
        }
        //启动线程，连接服务器，并用死循环守候，接收服务器发过来的数据
        Thread(this).start()
    }


    /**
     * 连接服务器
     */
    private fun connection() {
        try {
            socket = Socket(binding.etHost.text.toString(), PORT)
            socket.let {
                buffer = BufferedReader(InputStreamReader(socket.getInputStream()))
                out = PrintWriter(
                    BufferedWriter(OutputStreamWriter(socket.getOutputStream())),
                    true
                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Looper.prepare()
            showDialog("连接服务器失败：" + e.message)
            Looper.loop()
        }
    }

    /**
     * 如果连接出现异常，弹出dialog
     */
    private fun showDialog(msg: String) {
        AlertDialog.Builder(this)
            .setTitle("通知")
            .setMessage(msg)
            .setPositiveButton(
                "ok"
            ) { _: DialogInterface, _: Int ->

            }.show()
    }

    override fun run() {
        connection()
        try {
            while (true) {
                socket.let {
                    if (!socket.isClosed && !socket.isConnected && !socket.isInputShutdown) {
                        var getLine = buffer.readLine()
                        getLine += "\n"
                        val message = Message()
                        message.what = 1
                        message.obj = getLine
                        myHandler.sendMessage(message)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    class MyHandler(activity: SocketActivity) : Handler() {

        private var mReference: WeakReference<SocketActivity> = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val activity: SocketActivity? = mReference.get()
            msg.let {
                if (msg!!.what == 1) {
                    activity?.binding?.tvMsg?.append(msg.obj as CharSequence?)
                }
            }

        }
    }


}
