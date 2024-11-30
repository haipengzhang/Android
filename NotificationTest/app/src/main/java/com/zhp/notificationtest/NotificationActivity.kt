package com.zhp.notificationtest

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhp.notificationtest.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* 让通知在通知栏消失
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
        manager.cancel(1)
        */
    }
}