package com.zhp.fragmentbestpractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhp.fragmentbestpractice.databinding.ActivityNewsContentBinding

class NewsContentActivity : AppCompatActivity() {
    companion object {
        fun actionStart(context: Context, title: String, content: String) {
            val intent = Intent(context, NewsContentActivity::class.java).apply {
                putExtra("news_title", title)
                putExtra("news_content", content)
            }
            context.startActivity(intent)
        }
    }

    lateinit var binding: ActivityNewsContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("news_title")
        val content = intent.getStringExtra("news_content")
        if (title != null && content != null) {
            // binding 无法直接引用 newsContentFrag 是因为 ViewBinding 的机制只能绑定 布局文件中定义的视图元素
            // （例如 TextView, Button, RecyclerView 等），
            // 而 <fragment> 标签 是一种特殊的情况，它不会直接作为一个普通视图被绑定到 ViewBinding 中。
            val frag = supportFragmentManager.findFragmentById(R.id.newsContentFrag) as? NewsContentFragment
            frag?.refresh(title, content)
        }
    }
}