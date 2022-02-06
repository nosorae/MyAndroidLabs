package com.nosorae.labs.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

class ConstraintLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val constraints = ConstraintSet {
                val greenBox = createRefFor("greenBox")
                val redBox = createRefFor("redBox")
                val guideline = createGuidelineFromTop(fraction = 0.5f)

                constrain(greenBox) {
                    top.linkTo(guideline)
                    start.linkTo(parent.start)
                    width = Dimension.percent(0.25f)
                    height = Dimension.value(100.dp)
                }
                constrain(redBox) {
                    top.linkTo(guideline)
                    start.linkTo(greenBox.end)
                    end.linkTo(parent.end)
                    width = Dimension.percent(0.25f)
                    height = Dimension.value(100.dp)

                }
                createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed)
            }
            ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.background(Color.Green).layoutId("greenBox"))
                Box(modifier = Modifier.background(Color.Red).layoutId("redBox"))

            }
        }
    }
}