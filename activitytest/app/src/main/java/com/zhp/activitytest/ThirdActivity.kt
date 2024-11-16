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
import com.zhp.activitytest.databinding.SecondLayoutBinding
import com.zhp.activitytest.databinding.ThirdLayoutBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ThirdLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ThirdActivity", this.toString())
        val extraData = intent.getStringExtra("extra_data")
        Log.d("Second Activity", "Extra Data is $extraData")
        binding = ThirdLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button3.setOnClickListener {
            Toast.makeText(this, "You click Button 3", Toast.LENGTH_SHORT).show()
            /*
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
             */

            /*
            * onBackPressed()
            *  */

            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent()
        intent.putExtra("Data Return", "Return data to SecondActivity")
        setResult(RESULT_OK, intent)
        finish()
    }
}