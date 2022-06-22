package com.nosorae.labs.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nosorae.labs.R

class BasicLayoutComponents {

    @Composable
    fun BasicLayoutComponents() {
        Story(
            R.drawable.esfj2,
            "차은우"
        )
    }

    @Composable
    private fun SearchBar(
        modifier: Modifier = Modifier
    ) {
        TextField(
            value = "hint",
            onValueChange = {

            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            placeholder = {
                Text(text = "Search")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.primary
            ),
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)

        )
    }

    @Composable
    private fun Story(
        @DrawableRes imgId: Int,
        text: String,
        modifier: Modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(MaterialTheme.colors.primary)
        ) {
            Image(
                painter = painterResource(id = imgId),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                text = text, style = MaterialTheme.typography.body1, modifier = Modifier
                    .paddingFromBaseline(
                        top = 24.dp,
                        bottom = 8.dp
                    )
                    .wrapContentWidth()
                    .wrapContentHeight(),
            )
        }
    }
}