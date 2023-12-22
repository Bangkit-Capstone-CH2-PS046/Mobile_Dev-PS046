package com.rian.bodyfittest.ui.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rian.bodyfittest.MainViewModel
import com.rian.bodyfittest.ViewModelFactory
import com.rian.bodyfittest.data.adapter.ArticleAdapter
import com.rian.bodyfittest.data.adapter.TrainingAdapter
import com.rian.bodyfittest.data.model.DataSource
import com.rian.bodyfittest.data.pref.UserModel
import com.rian.bodyfittest.data.response.LoginResponse
import com.rian.bodyfittest.data.response.LoginResult
import com.rian.bodyfittest.data.response.TrainingResponseItem
import com.rian.bodyfittest.databinding.FragmentHomeBinding
import com.rian.bodyfittest.ui.Article.ArticleActivity
import com.rian.bodyfittest.ui.Login.LoginActivity
import com.rian.bodyfittest.ui.Login.LoginViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!


    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.viewAllArticles.setOnClickListener{
            val intent = Intent(requireContext(), ArticleActivity::class.java)
            startActivity(intent)
        }

        viewModel.isLoading.observe(requireActivity()) {
            showLoading(it)
        }

        val articleList = DataSource.dummyArticle.shuffled()

        val recyclerView: RecyclerView = binding.rvArticles
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = ArticleAdapter(articleList)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val layoutManager_trn = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)

        binding.rvTraining.layoutManager = layoutManager_trn

        viewModel.lisTrn.observe(viewLifecycleOwner) { consumerTraining ->
            setTrainingData(consumerTraining)
        }
    return root
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
        val hi = "Hi, "
        val username = session?.username ?: "Guest"
        binding.userTxt.text = hi + username
    }

    private fun setTrainingData(consumerUsers: List<TrainingResponseItem>) {
        val adapter = TrainAdapter()
        adapter.submitList(consumerUsers)
        binding.rvTraining.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {

        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
        binding.progressBar.isEnabled = !isLoading
    }

}


