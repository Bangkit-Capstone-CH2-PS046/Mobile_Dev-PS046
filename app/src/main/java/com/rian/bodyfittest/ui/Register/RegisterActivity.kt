package com.rian.bodyfittest.ui.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.rian.bodyfittest.ViewModelFactory
import com.rian.bodyfittest.data.response.RegisterResponse
import com.rian.bodyfittest.databinding.ActivityRegisterBinding
import com.rian.bodyfittest.ui.Login.LoginActivity
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnBack.setOnClickListener {
            finish()
        }

        setupAction()

    }

    private fun showLoading(isLoading: Boolean) {

        if (isLoading) {
            binding.progBarReg.visibility = View.VISIBLE
        } else {
            binding.progBarReg.visibility = View.GONE
        }
        binding.progBarReg.isEnabled = !isLoading
    }

    private fun setupAction() {

        binding.btnReg.setOnClickListener {
            val name = binding.txtInputUsernameReg.text.toString()
            val email = binding.txtInputEmailReg.text.toString()
            val password = binding.txtInputPassReg.text.toString()


//            val name = "pus"
//            val email = "smaugli@gmail.com"
//            val password = "12345678"
            showLoading(true)

            lifecycleScope.launch {
                try {
                    val response = viewModel.register(
                        name,
                        email,
                        password
                    )
                    Log.d("Registration", "Registration successful: $response")
                    runOnUiThread {
                        succesDialog(email)
                        showLoading(false)
                    }
                } catch (e: HttpException) {
                    showLoading(false)
                    val errorBody = e.response()?.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
                    val errorMessage = errorResponse.message
                    runOnUiThread {
                        errorMessage?.let {
                            failedDialog(errorMessage)
                            showLoading(false)
                        }
                    }
                }
            }
        }

    }

    private fun succesDialog(email : String?){
        AlertDialog.Builder(this@RegisterActivity).apply {
            setTitle("Congratulations!")
            setMessage("The account with $email is ready. Come on, let's training now!")
            setPositiveButton("Continue") { _, _ ->
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            create()
            show()
        }
    }
    private fun failedDialog(error : String?){
        AlertDialog.Builder(this@RegisterActivity).apply {
            setTitle("Yeah!")
            setMessage(error)
            setPositiveButton("Try Again") { _, _ ->
                Toast.makeText(this@RegisterActivity, "Try sign up again", Toast.LENGTH_SHORT).show()
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            create()
            show()
        }
    }
}