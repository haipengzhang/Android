package com.zhp.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.zhp.recyclerview.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFruits() // 初始化水果数据
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 指定recyclerView是线性布局，还有网格和瀑布流
        // val layoutManager = LinearLayoutManager(this)
        // layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        // val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        val layoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = FruitAdapter(fruitList)
    }

    private fun initFruits() {
        repeat(2) {
            fruitList.add(Fruit(getRandomLengthString("Camera"), android.R.drawable.ic_menu_camera))
            fruitList.add(Fruit(getRandomLengthString("Gallery"), android.R.drawable.ic_menu_gallery))
            fruitList.add(Fruit(getRandomLengthString("Call"), android.R.drawable.ic_menu_call))
            fruitList.add(Fruit(getRandomLengthString("Share"), android.R.drawable.ic_menu_share))
            fruitList.add(Fruit(getRandomLengthString("Search"), android.R.drawable.ic_menu_search))
            fruitList.add(Fruit(getRandomLengthString("Save"), android.R.drawable.ic_menu_save))
            fruitList.add(Fruit(getRandomLengthString("Delete"), android.R.drawable.ic_delete))
            fruitList.add(Fruit(getRandomLengthString("Alert"), android.R.drawable.ic_dialog_alert))
            fruitList.add(Fruit(getRandomLengthString("BigStar"), android.R.drawable.btn_star_big_on))
            fruitList.add(Fruit(getRandomLengthString("Star"), android.R.drawable.btn_star_big_off))
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n =  (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }
}
