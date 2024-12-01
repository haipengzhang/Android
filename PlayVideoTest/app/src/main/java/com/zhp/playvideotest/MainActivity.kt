package com.zhp.playvideotest

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhp.playvideotest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.login_video_02}")
        binding.videoView.setVideoURI(uri)
        binding.play.setOnClickListener {
            if (!binding.videoView.isPlaying) {
                binding.videoView.start() // 开始播放
            }
        }
        binding.pause.setOnClickListener {
            if (binding.videoView.isPlaying) {
                binding.videoView.pause() // 暂停播放
            }
        }
        binding.replay.setOnClickListener {
            if (binding.videoView.isPlaying) {
                binding.videoView.resume() // 重新播放
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.videoView.suspend()
    }
}