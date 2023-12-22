package com.rian.bodyfittest.data.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rian.bodyfittest.data.model.Article
import com.rian.bodyfittest.databinding.HomeArticlesItemsBinding
import com.rian.bodyfittest.ui.Detail.DetailArticleActivity

class ArticleListAdapter (private val articles: List<Article>) : RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HomeArticlesItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class ViewHolder(val binding: HomeArticlesItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            Glide.with(itemView.context).load(article.image).into(binding.imgArticle)
            binding.tvArticleCategory.text = article.category
            binding.tvDesc.text = article.title
            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, DetailArticleActivity::class.java)
                intent.putExtra("EXTRA_ID", article.id)
                context.startActivity(intent)
            }
        }
    }
}