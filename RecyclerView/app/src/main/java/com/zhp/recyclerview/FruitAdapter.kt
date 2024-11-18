package com.zhp.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.zhp.recyclerview.databinding.FruitItem2Binding
import com.zhp.recyclerview.databinding.FruitItem3Binding
import com.zhp.recyclerview.databinding.FruitItemBinding

class FruitAdapter(val fruitList: List<Fruit>): RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    class ViewHolder(val binding: FruitItem3Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 使用 ViewBinding 加载布局
        // val binding = FruitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // val binding = FruitItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        val binding = FruitItem3Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(binding)
        binding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "you clicked view ${fruit.name}",
                Toast.LENGTH_SHORT).show()
        }
        binding.fruitImage.setOnClickListener {
            val position = holder.bindingAdapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "you clicked image ${fruit.name}",
                Toast.LENGTH_SHORT).show()
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.binding.fruitImage.setImageResource(fruit.imageId)
        holder.binding.fruitName.text = fruit.name
    }

    override fun getItemCount() = fruitList.size
}