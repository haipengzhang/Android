package com.zhp.activitytest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zhp.activitytest.databinding.FirstLayoutBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: FirstLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 只有layout文件夹下的布局才会产生binding类
        binding = FirstLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.setOnClickListener {
            Toast.makeText(this, "You click Button 1", Toast.LENGTH_SHORT).show()
            /* 销毁页面
            * finish()
            * */

            /* 显式intent*/
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)

            /* 隐式intent，搜索所有在manifest能响应action和cate的activity
            val intent = Intent("com.zhp.activitytest.ACTION_START")
            intent.addCategory("com.zhp.activitytest.MY_CATEGORY")
            startActivity(intent)
            */

            /* 内置Action
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)

            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel: 13200000000")
            startActivity(intent)
             */
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // val item = menu?.findItem(R.id.your_item_id)
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clickd Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clickd Remove", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}