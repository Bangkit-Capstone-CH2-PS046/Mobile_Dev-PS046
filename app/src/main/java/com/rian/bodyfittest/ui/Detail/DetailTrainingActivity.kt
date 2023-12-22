package com.rian.bodyfittest.ui.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.rian.bodyfittest.R
import com.rian.bodyfittest.data.model.DataSource
import com.rian.bodyfittest.databinding.ActivityDetailTrainingBinding

class DetailTrainingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTrainingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnBack.setOnClickListener {
            finish()
        }

        val trainingId =  intent.getLongExtra("EXTRA_ID", -1)

        val training = DataSource.dummyTraining.find { it.id == trainingId }

        training?.let {
            binding.tvTitle.text = it.title
            binding.descStep.text = it.description
            binding.tvStatus.text = it.category
            Glide.with(this@DetailTrainingActivity)
                .load(it.image)
                .into(binding.imageDetail)
        }

        binding.doneButton.setOnClickListener{
            finish()
        }
    }
}
