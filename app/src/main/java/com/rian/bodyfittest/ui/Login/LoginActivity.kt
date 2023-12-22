package com.rian.bodyfittest.ui.Login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.rian.bodyfittest.MainActivity
import com.rian.bodyfittest.R
import com.rian.bodyfittest.ViewModelFactory
import com.rian.bodyfittest.data.pref.UserModel
import com.rian.bodyfittest.data.response.ErrorResponse
import com.rian.bodyfittest.databinding.ActivityLoginBinding
import com.rian.bodyfittest.ui.Register.RegisterActivity
import com.rian.bodyfittest.ui.Welcome.WelcomeActivity
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.txtSignup.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()

        }
        setupAction()

    }

//   private fun setupAction() {
//        binding.btnLog.setOnClickListener {
//            binding.progLog.visibility = View.VISIBLE
//            val email = binding.txtInputEmailLog.text.toString()
//            val pass = binding.txtInputPassLog.text.toString()
//            lifecycleScope.launch {
//                var txt = viewModel.LogUser(email,pass)
//                if (txt == "success"){
////                    viewModel.saveSession(UserModel(email, "sample_token"))
//
//                }
//                else{
//
//                }
//                binding.progLog.visibility = View.GONE
//                showDialog(txt!!)
//            }
//        }
//        binding.btnBack.setOnClickListener {
//            finish()
//        }
//        binding.txtSignup.setOnClickListener {
//            val intent = Intent(this, WelcomeActivity::class.java)
//            startActivity(intent)
//            finish()
//
//        }
//   }
//
//
//    private fun showDialog(message: String) {
//        AlertDialog.Builder(this).apply {
//            setTitle("Message")
//            if (message == "success"){
//                setMessage(getString(R.string.succes_massage))
//                setPositiveButton(R.string.next) { _, _ ->
//                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
//                    finish()
//                }
//            }
//            else{
//                setMessage(message)
//            }
//            create()
//            show()
//
//        }
//    }

    private fun setupAction() {
        binding.btnLog.setOnClickListener {
            val email = binding.txtInputEmailLog.text.toString()
            val password = binding.txtInputPassLog.text.toString()

            viewModel.isLoading.observe(this) {
                showLoading(it)
            }




            lifecycleScope.launch {
                try {
                    viewModel.login(email, password)
                    runOnUiThread {
                        viewModel.loginResult.observe(this@LoginActivity) {
                            Log.e("Login", "it: ${it}")
                            if (it.error == false) {
                                viewModel.saveSession(UserModel( it.loginResult?.username.toString(), it.loginResult?.email.toString(), it.loginResult?.token.toString(), true))
                                succesDialog()
                            }
                        }
                    }
                } catch (e: HttpException) {
                    val errorBody = e.response()?.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                    val errorMessage = errorResponse.message
                    Log.e("Login", "HttpException: $errorMessage")
                    runOnUiThread {
                        errorMessage?.let { failedDialog(it) }
                        showLoading(false)
                    }
                }
            }
        }
    }

    private fun succesDialog(){
        AlertDialog.Builder(this).apply {
            setTitle("Great!")
            setMessage("You have successfully logged in!")
            setPositiveButton("Continue") { _, _ ->
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            create()
            show()
        }
    }
    private fun failedDialog(message: String?){
        AlertDialog.Builder(this).apply {
            setTitle("Oops!")
            setMessage(message)
            setPositiveButton("Try Again") { _, _ ->
                Toast.makeText(this@LoginActivity, "Try Log in again", Toast.LENGTH_SHORT).show()
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            create()
            show()
        }
    }

    private fun showLoading(isLoading: Boolean) {

        if (isLoading) {
            binding.progLog.visibility = View.VISIBLE
        } else {
            binding.progLog.visibility = View.GONE
        }
        binding.progLog.isEnabled = !isLoading
    }




}