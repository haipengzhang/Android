package com.zhp.fragmenttest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zhp.fragmenttest.databinding.LeftFragmentBinding

class LeftFragment : Fragment() {
    // 定义接口
    interface OnButtonClickListener {
        fun onButtonClick()
    }

    private var listener: OnButtonClickListener? = null
    private var _binding: LeftFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = LeftFragmentBinding.inflate(inflater, container, false)

        // 设置按钮点击事件
        binding.button.setOnClickListener {
            listener?.onButtonClick()
        }

        return binding.root
        // return inflater.inflate(R.layout.left_fragment, container, false)
    }

    /**
     * onAttach 方法：当 Fragment 被附加到 Activity 上时，context 参数就是该 Activity。
     * 类型检查：通过 if (context is OnButtonClickListener) 判断该 Activity 是否实现了 OnButtonClickListener 接口。
     * 赋值：如果 Activity 实现了接口，则将 context 转型为 OnButtonClickListener 类型，并赋值给 listener。
     * */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnButtonClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnButtonClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * Fragment 的视图生命周期更短： Fragment 的视图在 onDestroyView 中销毁，
     * 但 Fragment 实例可能会继续存活（例如被缓存时），因此如果不清理 _binding，它仍然会引用被销毁的视图，造成内存泄漏。
     * */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}