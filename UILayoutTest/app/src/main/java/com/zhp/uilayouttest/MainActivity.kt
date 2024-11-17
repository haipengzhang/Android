package com.zhp.uilayouttest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//import com.zhp.uilayouttest.databinding.ActivityMainBinding
//import com.zhp.uilayouttest.databinding.LinearLayout02Binding
//import com.zhp.uilayouttest.databinding.RelativeLayoutBinding
//import com.zhp.uilayouttest.databinding.RelativeLayout02Binding
import com.zhp.uilayouttest.databinding.FrameLayoutBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: FrameLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FrameLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}