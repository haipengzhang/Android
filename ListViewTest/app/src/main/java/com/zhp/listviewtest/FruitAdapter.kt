package com.zhp.listviewtest

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.zhp.listviewtest.databinding.FruitItemBinding

class FruitAdapter(activity: Activity, val resourceId: Int, data: List<Fruit>) :
    ArrayAdapter<Fruit>(activity, resourceId, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // convertView复用的关键
        // 使用 ViewBinding
        val binding = convertView?.let {
            // 如果 convertView 不为空，尝试从 tag 获取绑定类
            it.tag as FruitItemBinding
        } ?: run {
            // 如果 convertView 为空，初始化 ViewBinding
            val inflater = LayoutInflater.from(context)
            val binding = FruitItemBinding.inflate(inflater, parent, false)
            binding.root.tag = binding // 将绑定类存储到 view 的 tag 中
            binding
        }

        val fruit = getItem(position) // 获取当前项的Fruit实例
        if (fruit != null) {
            binding.fruitImage.setImageResource(fruit.imageId)
            binding.fruitName.text = fruit.name
        }
        return binding.root
    }

}