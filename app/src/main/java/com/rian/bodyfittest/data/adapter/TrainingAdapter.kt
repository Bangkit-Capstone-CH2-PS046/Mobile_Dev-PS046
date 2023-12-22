package com.rian.bodyfittest.data.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rian.bodyfittest.R
import com.rian.bodyfittest.data.model.Training
import com.rian.bodyfittest.databinding.PracticeItemsBinding
import com.rian.bodyfittest.ui.Detail.DetailTrainingActivity

class TrainingAdapter (
    private val training: List<Training>
) : RecyclerView.Adapter<TrainingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PracticeItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val training = training[position]
        holder.bind(training)
    }

    override fun getItemCount(): Int {
        return training.size
    }

    inner class ViewHolder(val binding: PracticeItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(training: Training) {
            Glide.with(itemView.context).load(training.image).into(binding.imgPractice)
            binding.tvPracticeTitle.text = training.title
            binding.tvStatus.text = training.category
            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, DetailTrainingActivity::class.java)
                intent.putExtra("EXTRA_ID", training.id)
                context.startActivity(intent)
            }
        }
    }
}