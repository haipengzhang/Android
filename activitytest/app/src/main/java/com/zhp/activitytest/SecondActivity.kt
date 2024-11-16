package com.zhp.activitytest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zhp.activitytest.databinding.FirstLayoutBinding
import com.zhp.activitytest.databinding.SecondLayoutBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: SecondLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 只有layout文件夹下的布局才会产生binding类
        binding = SecondLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button2.setOnClickListener {
            Toast.makeText(this, "You click Button 2", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("extra_data", "hello third activity")
            // startActivity(intent)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val returnedData = data?.getStringExtra("Data Return")
                Log.d("Back from Third Activity", "Data is $returnedData")
            }
        }
    }
}