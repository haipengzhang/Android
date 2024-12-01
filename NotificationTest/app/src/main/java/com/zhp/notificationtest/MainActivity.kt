package com.zhp.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.zhp.notificationtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)

            val channel2 = NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel2)

            binding.sendNotice.setOnClickListener {
                /*
                // normal channel notification
                val intent = Intent(this, NotificationActivity::class.java)
                val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
                val notification = NotificationCompat.Builder(this, "normal")
                    .setContentTitle("This is content title")
                    .setContentText("This is content text")
                    .setSmallIcon(android.R.drawable.ic_menu_camera)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, android.R.drawable.ic_notification_overlay))
                    .setContentIntent(pi)
                    .setStyle(NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync  data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))
                    .setAutoCancel(true)
                    .build()

                manager.notify(1, notification)
                */

                // important channel notification
                val intent = Intent(this, NotificationActivity::class.java)
                val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
                val notification = NotificationCompat.Builder(this, "important")
                    .setContentTitle("This is important content title")
                    .setContentText("This is important content text")
                    .setSmallIcon(android.R.drawable.ic_menu_camera)
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build()
                manager.notify(1, notification)
            }
        }
    }
}