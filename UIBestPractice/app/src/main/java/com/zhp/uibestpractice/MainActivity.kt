package com.zhp.uibestpractice

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhp.uibestpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    private var msgList = ArrayList<Message>()
    lateinit var adapter: MessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMsg()

        // 给cyclerview初始化adper和layoutmanager
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        adapter = MessageAdapter(msgList)
        binding.recyclerView.adapter = adapter

        binding.send.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.send -> {
                val content = binding.inputText.text.toString()
                if (content.isNotEmpty()) {
                    val msg = Message(content, Message.TYPE_SENT)
                    msgList.add(msg)
                    adapter.notifyItemInserted(msgList.size - 1) // 当有新消息时，刷新RecyclerView中的显示
                    binding.recyclerView.scrollToPosition(msgList.size - 1)  // 将RecyclerView 定位到最后一行
                    binding.inputText.setText("") // 清空输入框中的内容
                }
            }
        }
    }

    private fun initMsg() {
        val msg1 = Message("Hello guy.", Message.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Message("Hello. Who is that?", Message.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = Message("This is Tom. Nice talking to you. ", Message.TYPE_RECEIVED)
        msgList.add(msg3)
    }
}

