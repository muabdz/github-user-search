package com.astro.test.muadzabdurrahman.feature.main.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.astro.test.muadzabdurrahman.R
import com.astro.test.muadzabdurrahman.data.entity.User
import com.astro.test.muadzabdurrahman.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var searchAdapter : SearchUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportActionBar?.subtitle = getString(R.string.astro_test)

        val searchEventManager = viewModel.searchEventManager
        with(searchEventManager) {
            onLoading = {
                showLoading()
            }
            onSuccess = {
                hideLoading()
                viewBinding.rvUser
                with(viewModel) {
                    page++
                    addUserList(it)
                    searchAdapter.addUsers(it)
                }
            }
            onFailure = {
                hideLoading()
                showError(it.localizedMessage ?: getString(R.string.an_error_occurred))
            }
        }

        setupView()
    }

    private fun setupView() {
        searchAdapter = SearchUserAdapter(viewModel.userList, listener)
        val llm = LinearLayoutManager(this, VERTICAL, false)
        with(viewBinding) {
            rvUser.apply {
                adapter = searchAdapter
                layoutManager = llm
                setHasFixedSize(true)
            }
            etSearch.apply {
                setText(viewModel.keyword)
                doAfterTextChanged {
                    val keyword = etSearch.text.toString()
                    if (keyword.isNotEmpty()) {
                        viewModel.page = 1
                        viewModel.keyword = keyword
                        Handler(Looper.myLooper() ?: mainLooper).removeCallbacks(runnable)
                        Handler(Looper.myLooper() ?: mainLooper).postDelayed(runnable, 300)
                    }
                }
            }
        }
    }

    private val runnable = Runnable {
        kotlin.run {
            viewModel.searchUser()
        }
    }

    private val listener = object : SearchUserAdapter.UserListener {
        override fun onFavoriteClick(user: User) {
            // TODO: handle favorite click MU
        }

    }

    private fun showLoading() {
        viewBinding.pbLoading.isVisible = true
    }

    private fun hideLoading() {
        viewBinding.pbLoading.isVisible = false
    }

    private fun showError(message: String) {
        Snackbar.make(viewBinding.root, message, Snackbar.LENGTH_INDEFINITE).setAction(getString(R.string.retry)) { viewModel.searchUser() }.show()
    }

}