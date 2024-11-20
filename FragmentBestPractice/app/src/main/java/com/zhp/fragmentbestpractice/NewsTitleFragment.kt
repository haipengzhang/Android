package com.zhp.fragmentbestpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhp.fragmentbestpractice.databinding.NewsItemBinding
import com.zhp.fragmentbestpractice.databinding.NewsTitleFragBinding

class NewsTitleFragment : Fragment() {

    lateinit var cyclerBinding: NewsTitleFragBinding
    private var newsContentFragment: NewsContentFragment? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        cyclerBinding = NewsTitleFragBinding.inflate(inflater)
        val layoutManager = LinearLayoutManager(activity)
        cyclerBinding.newsTitleRecyclerView.layoutManager = layoutManager
        val adapter = NewsAdapter(getNews()) { news ->
            if (newsContentFragment != null) {
                // 在双页模式下更新内容
                newsContentFragment?.refresh(news.title, news.content)
            } else {
                // 在单页模式下启动新 Activity
                NewsContentActivity.actionStart(requireContext(), news.title, news.content)
            }
        }
        cyclerBinding.newsTitleRecyclerView.adapter = adapter
        return cyclerBinding.root
    }

    private fun getNews(): List<News> {
        val newsList = ArrayList<News>()
        for (i in 0..50) {
            val news = News("This is news title $i", getRandomLengthString("This is news content $i"))
            newsList.add(news)
        }
        return newsList
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newsContentFragment = activity?.supportFragmentManager?.findFragmentById(R.id.newsContentFrag) as? NewsContentFragment
    }

    inner class NewsAdapter(val newsList: List<News>, private val onItemClick: (News) -> Unit):
        RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
        inner class ViewHolder(val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: NewsItemBinding = NewsItemBinding.inflate(inflater)
            val holder = ViewHolder(binding)
            binding.newsTitle.setOnClickListener {
                val news = newsList[holder.adapterPosition]
                onItemClick(news)
            }
            return holder
        }

        override fun getItemCount(): Int {
            return newsList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = newsList[position]
            holder.binding.newsTitle.text = item.title
        }
    }
}