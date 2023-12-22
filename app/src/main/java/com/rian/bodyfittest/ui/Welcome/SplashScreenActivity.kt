package com.rian.bodyfittest.ui.Welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.rian.bodyfittest.MainActivity
import com.rian.bodyfittest.MainViewModel
import com.rian.bodyfittest.R
import com.rian.bodyfittest.ViewModelFactory
import com.rian.bodyfittest.ui.Login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({

            viewModel.getSession().observe(this) { user ->
                if (!user.isLogin) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                else{
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
        }, 2000)
        supportActionBar?.hide()
    }
}