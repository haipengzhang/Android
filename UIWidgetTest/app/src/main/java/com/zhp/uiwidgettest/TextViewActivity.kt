package com.zhp.uiwidgettest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zhp.uiwidgettest.databinding.ActivityTextViewBinding

class TextViewActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityTextViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button -> {
                Toast.makeText(this, "Typed ${binding.editText.text}", Toast.LENGTH_SHORT).show()
                binding.imageView.setImageResource(R.drawable.logo)
            }
        }
    }
}