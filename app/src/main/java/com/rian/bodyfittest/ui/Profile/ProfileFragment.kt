package com.rian.bodyfittest.ui.Profile

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rian.bodyfittest.R
import com.rian.bodyfittest.ViewModelFactory
import com.rian.bodyfittest.data.pref.UserModel
import com.rian.bodyfittest.databinding.FragmentProfileBinding
import com.rian.bodyfittest.ui.Login.LoginActivity

class ProfileFragment : Fragment() {

    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    private var _binding: FragmentProfileBinding? = null
    private var isDarkTheme: Boolean = false
    private lateinit var progressDialog: ProgressDialog

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        isDarkTheme = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        setThemeIcon()

        binding.logoutButton.setOnClickListener {
            viewModel.logout()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        binding.btnSettings.setOnClickListener {
            toggleTheme()
        }

        return root
    }

    private fun toggleTheme() {
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Wait a second")
        progressDialog.setMessage("Changing the theme...")
        progressDialog.setCancelable(true)
        progressDialog.show()
        isDarkTheme = !isDarkTheme

        setThemeIcon()
        Handler(Looper.getMainLooper()).postDelayed({
            progressDialog.dismiss()
            if (isDarkTheme) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }, 500)
    }

    private fun setThemeIcon() {
        val iconResId = if (isDarkTheme) {
            R.drawable.sun
        } else {
            R.drawable.moon
        }

        binding.btnSettings.setImageResource(iconResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        viewModel.getSession().observe(viewLifecycleOwner, sessionObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.getSession().removeObserver(sessionObserver)
    }

    private val sessionObserver = Observer<UserModel> { session ->
        val username = session?.username ?: "Guest"
        val email = session?.email ?: "guest@gmail.com"
        binding.tvUser.text = username
        binding.tvEmail.text = email
    }
}