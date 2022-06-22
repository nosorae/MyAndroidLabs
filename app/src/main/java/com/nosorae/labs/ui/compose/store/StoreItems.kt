package com.nosorae.labs.ui.compose.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nosorae.labs.R
import com.thingsflow.app.core.views.compose.Color
import com.thingsflow.app.core.views.compose.Typography

class StoreItems {
    @Composable
    fun Appbar() {
        TopAppBar(
            elevation = 0.dp,
            modifier = Modifier
                .height(52.dp)
                .fillMaxWidth(),
            backgroundColor = Color.White
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.esfp),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "스토어",
                    color = Color.Gray900,
                    fontSize = Typography.Navigation.fontSize(),
                    fontWeight = Typography.Navigation.weight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }


        }
    }
}