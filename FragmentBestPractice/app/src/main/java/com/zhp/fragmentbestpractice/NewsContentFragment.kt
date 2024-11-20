package com.zhp.fragmentbestpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zhp.fragmentbestpractice.databinding.NewsContentFragBinding

class NewsContentFragment : Fragment() {

    lateinit var binding: NewsContentFragBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = NewsContentFragBinding.inflate(inflater)
        return binding.root
    }

    fun refresh(title: String, content: String) {
        binding.contentLayout.visibility = View.VISIBLE
        binding.newsTitle.text = title // 刷新新闻的标题
        binding.newsContent.text = content // 刷新新闻的内容
    }

}