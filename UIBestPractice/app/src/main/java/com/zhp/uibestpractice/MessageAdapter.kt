package com.zhp.uibestpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.zhp.uibestpractice.databinding.LeftItemBinding
import com.zhp.uibestpractice.databinding.RightItemBinding

class MessageAdapter(val msgList: List<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // 泛型的应用
    sealed class MsgViewHolder<T : ViewBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)

    // 父类已经有binding变量，不需要写val
    class LeftViewHolder(binding: LeftItemBinding) : MsgViewHolder<LeftItemBinding>(binding)

    class RightViewHolder(binding: RightItemBinding) : MsgViewHolder<RightItemBinding>(binding)

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
            is LeftViewHolder -> {
                holder.binding.leftMsg.text = message.content
            }

            is RightViewHolder -> {
                holder.binding.rightMsg.text = message.content
            }
        }
    }

}
