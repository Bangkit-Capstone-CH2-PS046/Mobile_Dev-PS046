package com.ubaya.workout_app_caps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ubaya.workout_app_caps.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupAction()



    }

    private fun setupAction() {
        val regViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(RegisterMainModel::class.java)
        binding.btnReg.setOnClickListener {
            val email = binding.txtInputEmailReg.text.toString()
            val name = binding.txtInputUsernameReg.text.toString()
            val pass = binding.txtInputPassReg.text.toString()
            binding.progBarReg.visibility = View.VISIBLE



            lifecycleScope.launch {
                var txt = regViewModel.regUser(name, email, pass)!!
                binding.progBarReg.visibility = View.GONE
                showDialog(txt)
//                if (regViewModel.regUser(name, email, pass)!!){
//                    showDialog("error true")
//
//
//                }
//                else{
//                    showDialog("error false")
//                }

            }



//            AlertDialog.Builder(this).apply {
//                setTitle("Yeah!")
//                setMessage("Akun dengan $email sudah jadi nih. Yuk, login dan belajar coding.")
//                setPositiveButton("Lanjut") { _, _ ->
//                    finish()
//                }
//                create()
//                show()
//            }
        }

        binding.txtLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }


    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this).apply {
            setTitle("Message")
            setMessage(message)
            if (message == "User created"){
                setPositiveButton("Lanjut") { _, _ ->
                    finish()
                }
            }
            create()
            show()

        }
    }
}