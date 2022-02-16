package com.nosorae.labs.di_test.hilt.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nosorae.labs.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity() {
    @Inject lateinit var viewModel: HiltTestViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt_test)

        viewModel.testViewModel(this)
    }
}