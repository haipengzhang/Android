package com.zhp.listviewtest

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zhp.listviewtest.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFruits() // 初始化水果数据

        // 在ArrayAdapter的构造函数中依次传入Activity的实例、ListView子项布局的cell id
        // 内置的android.R.layout.simple_list_item_1
        val adapter = FruitAdapter(this, R.layout.fruit_item ,fruitList)
        binding.listView.adapter = adapter

        // ListView， cell， position， 被点击项的唯一标识
        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val fruit = fruitList[position]
            Toast.makeText(this, fruit.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFruits() {
        repeat(2) {
            fruitList.add(Fruit("Camera", android.R.drawable.ic_menu_camera))
            fruitList.add(Fruit("Gallery", android.R.drawable.ic_menu_gallery))
            fruitList.add(Fruit("Call", android.R.drawable.ic_menu_call))
            fruitList.add(Fruit("Share", android.R.drawable.ic_menu_share))
            fruitList.add(Fruit("Search", android.R.drawable.ic_menu_search))
            fruitList.add(Fruit("Save", android.R.drawable.ic_menu_save))
            fruitList.add(Fruit("Delete", android.R.drawable.ic_delete))
            fruitList.add(Fruit("Alert", android.R.drawable.ic_dialog_alert))
            fruitList.add(Fruit("BigStar", android.R.drawable.btn_star_big_on))
            fruitList.add(Fruit("Star", android.R.drawable.btn_star_big_off))
        }
    }
}

