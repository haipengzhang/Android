package com.zhp.uiwidgettest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.zhp.uiwidgettest.databinding.NormalWidgetActivityBinding

class NormalWidgetActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: NormalWidgetActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NormalWidgetActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button -> {
                /*
                Toast.makeText(this, "Typed ${binding.editText.text}", Toast.LENGTH_SHORT).show()
                val currentDrawable = binding.imageView.drawable
                if (currentDrawable == null) {
                    binding.imageView.setImageResource(R.drawable.logo)
                }

                if (binding.progressBar.visibility == View.VISIBLE) {
                    binding.progressBar.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.VISIBLE
                }

                binding.progressBar2.progress += 10
                */

                AlertDialog.Builder(this).apply {
                    setTitle("This is Dialog")
                    setMessage("Something important")
                    setCancelable(false)
                    setPositiveButton("OK") { dialog, which ->
                        Toast.makeText(this@NormalWidgetActivity, "Pressed ${which}", Toast.LENGTH_SHORT).show()
                    }
                    setNegativeButton("Cancel") { dialog, which ->
                        Toast.makeText(this@NormalWidgetActivity, "Pressed ${which}", Toast.LENGTH_SHORT).show()
                    }
                    show()
                }
            }
        }
    }
}