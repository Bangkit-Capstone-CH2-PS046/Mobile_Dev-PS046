package com.rian.bodyfittest.ui.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.rian.bodyfittest.data.model.DataSource.dummyArticle
import com.rian.bodyfittest.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        supportActionBar?.hide()


        val articleId =  intent.getLongExtra("EXTRA_ID", -1)

        val article = dummyArticle.find { it.id == articleId }

        article?.let {
            binding.tvArticleCategory.text = it.category
            binding.tvTitle.text = it.title
            binding.descArticle.text = it.description
            Glide.with(this@DetailArticleActivity)
                .load(it.image)
                .into(binding.imageDetail)
        }

//        binding.apply {
//            tvArticleCategory.text = article?.category
//            tvTitle.text = article?.title
//            descArticle.text = article?.description
//            Glide.with(this@DetailArticleActivity)
//                .load(article?.image)
//                .into(imageDetail)
//        }








    }


}