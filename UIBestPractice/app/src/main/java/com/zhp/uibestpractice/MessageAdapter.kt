package com.zhp.uibestpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zhp.uibestpractice.databinding.LeftItemBinding
import com.zhp.uibestpractice.databinding.RightItemBinding

class MessageAdapter(val msgList: List<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class LeftViewHolder(val binding: LeftItemBinding) : RecyclerView.ViewHolder(binding.root)

    inner class RightViewHolder(val binding: RightItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType  == Message.TYPE_RECEIVED) {
            val binding = LeftItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LeftViewHolder(binding)
        } else {
            val binding = RightItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RightViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return msgList[position].type
    }

    override fun getItemCount(): Int {
        return msgList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = msgList[position]
        when (holder) {
            is LeftViewHolder -> holder.binding.leftMsg.text = message.content

            is RightViewHolder -> holder.binding.rightMsg.text = message.content

            else -> throw IllegalArgumentException()
        }
    }

}
