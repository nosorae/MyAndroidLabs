package com.thingsflow.app.core.views.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

object Typography {
    val Headline03 = TypographyInfo(32.dp, weight = FontWeight.Bold, height = 40.dp)
    val Headline02 = TypographyInfo(24.dp, weight = FontWeight.Bold, height = 30.dp)
    val Headline01 = TypographyInfo(20.dp, weight = FontWeight.Bold, height = 26.dp)

    val Navigation = TypographyInfo(17.dp, weight = FontWeight.Bold, height = 22.dp)

    val Subhead03 = TypographyInfo(16.dp, weight = FontWeight.Bold, height = 22.dp)
    val Subhead02 = TypographyInfo(14.dp, weight = FontWeight.Bold, height = 20.dp)
    val Subhead01 = TypographyInfo(13.dp, weight = FontWeight.Bold, height = 18.dp)

    val Body03 = TypographyInfo(16.dp, weight = FontWeight.Normal, height = 22.dp)
    val Body02 = TypographyInfo(14.dp, weight = FontWeight.Normal, height = 20.dp)
    val Body01 = TypographyInfo(13.dp, weight = FontWeight.Normal, height = 18.dp)

    val Caption = TypographyInfo(12.dp, weight = FontWeight.Normal, height = 16.dp)
}

data class TypographyInfo(
    private val size: Dp,
    val weight: FontWeight,
    private val height: Dp,
    private val letterSpacing: Dp = (-0.3).dp
) {
    @Composable
    fun fontSize(): TextUnit = dpToSp(this.size)

    @Composable
    fun lineHeight(): TextUnit = dpToSp(this.height)

    @Composable
    fun letterSpacing(): TextUnit = dpToSp(this.letterSpacing)
}
