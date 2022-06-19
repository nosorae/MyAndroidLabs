package com.nosorae.labs.ui.compose

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class BasicLayoutComponents {

    @Composable
    fun BasicLayoutComponents() {
        SearchBar()
    }

    @Composable
    private fun SearchBar(
        modifier: Modifier = Modifier
    ) {
        TextField(
            value = "",
            onValueChange = {

            },
            modifier = modifier
        )
    }
}