package com.rian.bodyfittest.ui.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.rian.bodyfittest.R
import com.rian.bodyfittest.ViewModelFactory
import com.rian.bodyfittest.data.model.DataSource
import com.rian.bodyfittest.data.response.TrainingResponse
import com.rian.bodyfittest.data.response.TrainingResponseItem
import com.rian.bodyfittest.databinding.ActivityDetailHomeTrainBinding
import com.rian.bodyfittest.databinding.ActivityDetailTrainingBinding
import com.rian.bodyfittest.ui.Home.HomeViewModel

class DetailHomeTrainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailHomeTrainBinding

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHomeTrainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnBack.setOnClickListener {
            finish()
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val trainingName = intent.getStringExtra("EXTRA_ID")
        viewModel.lisTrn.observe(this) { trainingList ->
            val training = trainingList.find { it.name.equals(trainingName) }

            training?.let {
                binding.tvTitle.text = it.name
                binding.descStep.text = it.instructions
                binding.tvStatus.text = it.type
                Glide.with(this@DetailHomeTrainActivity)
                    .load(R.drawable.workout_train_img_)
                    .into(binding.imageDetail)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {

        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
        binding.progressBar.isEnabled = !isLoading
    }
}