package com.rian.bodyfittest.ui.Workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rian.bodyfittest.MainActivity
import com.rian.bodyfittest.data.adapter.ArticleListAdapter
import com.rian.bodyfittest.data.adapter.TrainingAdapter
import com.rian.bodyfittest.data.model.DataSource
import com.rian.bodyfittest.databinding.ActivityWorkoutBinding

class WorkoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val trainingList = DataSource.dummyTraining.shuffled()

        val recyclerView: RecyclerView = binding.rvTraining
        val layoutManager = LinearLayoutManager(this)
        val adapter = TrainingAdapter(trainingList)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.progressBar.visibility =  View.GONE
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
        }, 500)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}
