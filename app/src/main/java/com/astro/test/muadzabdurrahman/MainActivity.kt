package com.astro.test.muadzabdurrahman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.astro.test.muadzabdurrahman.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportActionBar?.subtitle = getString(R.string.astro_test)
    }
}