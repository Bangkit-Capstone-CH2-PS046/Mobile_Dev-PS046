package com.ubaya.workout_app_caps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.ubaya.workout_app_caps.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()

    }

    private fun setupAction() {
        binding.btnLog.setOnClickListener {
            binding.progLog.visibility = View.VISIBLE
            val email = binding.txtInputEmailLog.text.toString()
            val pass = binding.txtInputPassLog.text.toString()
            lifecycleScope.launch {
                var txt = viewModel.LogUser(email,pass)
                if (txt == "success"){
//                    viewModel.saveSession(UserModel(email, "sample_token"))

                }
                else{

                }
                binding.progLog.visibility = View.GONE
                showDialog(txt!!)
            }
//            AlertDialog.Builder(this).apply {
//                setTitle("Yeah!")
//                setMessage("Anda berhasil login. Sudah tidak sabar untuk belajar ya?")
//                setPositiveButton("Lanjut") { _, _ ->
//                    val intent = Intent(context, MainActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
//                    finish()
//                }
//                create()
//                show()
//            }
        }

        binding.txtSignup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }
    }


    private fun showDialog(message: String) {
        AlertDialog.Builder(this).apply {
            setTitle("Message")
            if (message == "success"){
                setMessage("Anda berhasil login. Sudah tidak sabar untuk belajar ya?")
                setPositiveButton("Lanjut") { _, _ ->
//                    val intent = Intent(context, StoryActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
//                    finish()
                }
            }
            else{
                setMessage(message)
            }
            create()
            show()

        }
    }
}