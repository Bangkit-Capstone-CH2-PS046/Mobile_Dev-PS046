package com.rian.bodyfittest.ui.ResultPhoto

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.rian.bodyfittest.databinding.ActivityResultPhotoBinding
import com.rian.bodyfittest.ui.CameraX.CameraX
import com.rian.bodyfittest.ui.Detail.DetailTrainingActivity
import com.rian.bodyfittest.ui.Workout.WorkoutActivity

class ResultPhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultPhotoBinding

    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.cancelButton.setOnClickListener {
            finish()
        }
        binding.scanBtn.setOnClickListener {
            val intent = Intent(this, WorkoutActivity::class.java)
            startActivity(intent)
            finish()
        }

        currentImageUri = Uri.parse(intent.getStringExtra(CameraX.EXTRA_CAMERAX_IMAGE))

        showImage()

    }

    private fun showImage() {
        showLoading(false)
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
    }




    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}