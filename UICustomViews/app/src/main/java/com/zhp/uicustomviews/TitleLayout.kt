package com.zhp.uicustomviews

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.zhp.uicustomviews.databinding.TitleBinding

class TitleLayout(context: Context, attribute: AttributeSet): LinearLayout(context, attribute) {
    lateinit var binding: TitleBinding
    init {
        // LayoutInflater.from(context)创建了LayoutInflater
        // inflate创建了布局对象，创建一个layout布局在this也就是自己LinearLayout
        val root = LayoutInflater.from(context).inflate(R.layout.title, this, true)
        // binding = TitleBinding.bind(root)

        binding = TitleBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

        binding.titleBack.setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }

        binding.titleEdit.setOnClickListener {
            Toast.makeText(context, "Edit Btn clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
