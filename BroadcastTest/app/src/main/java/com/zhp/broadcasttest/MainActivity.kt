package com.zhp.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zhp.broadcasttest.databinding.ActivityMainBinding

// 实时监听时间变化
class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    lateinit var timeChangeReceiver: TimeChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)

        // 发送自定义广播
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mainBinding.button.setOnClickListener {
            val intent = Intent("com.zhp.broadcast.MY_BROADCAST")
            // 设置intent是发送给那个包的
            intent.setPackage(packageName)
            // sendBroadcast(intent)

            // 第二个参数是权限相关
            sendOrderedBroadcast(intent, null)
        }

        setContentView(mainBinding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }

    inner class TimeChangeReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Toast.makeText(context, "Time has changed", Toast.LENGTH_SHORT).show()
        }
    }
}