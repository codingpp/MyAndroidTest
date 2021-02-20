package com.codingpp.myandroid.lifecycle.networksample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 *  网络监听Observer
 * @author sunpan
 * @date 2019/2/26
 */
class NetworkObserver(private val context: Context) : LifecycleObserver {

    /**
     * 网络状态接收
     */
    private val receiver = NetworkReceiver()

    /**
     * 网络变化监听
     */
    var listener: OnNetworkChangedListener? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        val filter = IntentFilter()
        filter.addAction(CONNECTIVITY_ACTION)
        context.registerReceiver(receiver, filter)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        context.unregisterReceiver(receiver)
    }

    companion object {
        /**
         * 无网络
         */
        const val NETWORK_NONE = -1

        /**
         * 未知网络
         */
        const val NETWORK_UNKNOWN = 0

        /**
         * 移动数据
         */
        const val NETWORK_MOBILE = 1

        /**
         * Wi-Fi网络
         */
        const val NETWORK_WIFI = 2

    }

    inner class NetworkReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var resultType = NETWORK_NONE
            val manager = context?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            //获取所有网络连接的信息
            val networks = manager.allNetworks
            //通过循环将网络信息逐个取出来
            for (i in networks.indices) {
                //获取ConnectivityManager对象对应的NetworkInfo对象
                val networkInfo = manager.getNetworkInfo(networks[i])
                resultType = if (networkInfo?.isConnected == true) {
                    when (networkInfo.type) {
                        ConnectivityManager.TYPE_MOBILE -> NETWORK_MOBILE
                        ConnectivityManager.TYPE_WIFI -> NETWORK_WIFI
                        else -> NETWORK_UNKNOWN
                    }
                } else {
                    NETWORK_NONE
                }
            }
            listener?.invoke(resultType)
        }
    }
}