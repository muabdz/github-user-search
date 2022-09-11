package com.astro.test.muadzabdurrahman.feature.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.astro.test.muadzabdurrahman.R
import com.astro.test.muadzabdurrahman.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportActionBar?.subtitle = getString(R.string.astro_test)

        val searchEventManager = viewModel.searchEventManager
        with(searchEventManager) {
            onLoading = {

            }
            onSuccess = {

            }
            onFailure = {

            }
        }
    }
}