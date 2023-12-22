package com.rian.bodyfittest.ui.Home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rian.bodyfittest.R
import com.rian.bodyfittest.data.response.TrainingResponseItem
import com.rian.bodyfittest.databinding.HomeTrainingItemsBinding
import com.rian.bodyfittest.ui.Detail.DetailHomeTrainActivity
import com.rian.bodyfittest.ui.Detail.DetailTrainingActivity

class TrainAdapter : ListAdapter<TrainingResponseItem, TrainAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = HomeTrainingItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)


    }
    class MyViewHolder(val binding: HomeTrainingItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(training: TrainingResponseItem){
            binding.tvPracticeTitle.text = "${training.name}"
            Glide.with(binding.root)
                .load(R.drawable.workout_train_img_)
                .into(binding.imgPractice)
            binding.root.setOnClickListener {
            val context = binding.root.context
                val intent = Intent(context, DetailHomeTrainActivity::class.java)
                intent.putExtra("EXTRA_ID", training.name)
                context.startActivity(intent)

            }
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TrainingResponseItem>() {
            override fun areItemsTheSame(oldItem: TrainingResponseItem, newItem: TrainingResponseItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: TrainingResponseItem, newItem: TrainingResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}