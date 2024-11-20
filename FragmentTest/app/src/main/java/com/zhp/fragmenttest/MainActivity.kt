package com.zhp.fragmenttest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhp.fragmenttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LeftFragment.OnButtonClickListener  {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        // 加载左边的 Fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.rightLayout, RightFragment())
            .commit()
         */
    }

    // 实现接口，动态加载右边的 Fragment
    override fun onButtonClick() {
        val fragmentA = AnotherRightFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.rightFrag, fragmentA)
            .addToBackStack(null) // 可选：加入返回栈
            .commit()
    }
}