package com.rian.bodyfittest.ui.Article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rian.bodyfittest.R
import com.rian.bodyfittest.data.adapter.ArticleAdapter
import com.rian.bodyfittest.data.adapter.ArticleListAdapter
import com.rian.bodyfittest.data.model.DataSource
import com.rian.bodyfittest.databinding.ActivityArticleBinding
import com.rian.bodyfittest.databinding.ActivityDetailTrainingBinding

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val articleList = DataSource.dummyArticle

        val recyclerView: RecyclerView = binding.rvListArticle
        val layoutManager = LinearLayoutManager(this)
        val adapter = ArticleListAdapter(articleList)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}