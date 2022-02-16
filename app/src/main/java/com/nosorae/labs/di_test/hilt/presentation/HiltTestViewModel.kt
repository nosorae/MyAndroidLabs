package com.nosorae.labs.di_test.hilt.presentation

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
class HiltTestViewModel: ViewModel() {

    fun testViewModel(context: Context) {
        Toast.makeText(context, "asdf", Toast.LENGTH_SHORT).show()
    }
}