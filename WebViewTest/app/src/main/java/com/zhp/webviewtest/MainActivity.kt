package com.zhp.webviewtest

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.zhp.webviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        * 我们调用了WebView的setWebViewClient()方法，并传入了一个WebViewClient的实例。
        * 这段代码的作用是，当需要从一个网页跳转到另一个网页时，我们希望目标网页仍然在当前WebView中显示，
        * 而不是打开系统浏览器。
        * */
        binding.webView.settings.javaScriptEnabled=true
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl("https://www.baidu.com")
    }
}