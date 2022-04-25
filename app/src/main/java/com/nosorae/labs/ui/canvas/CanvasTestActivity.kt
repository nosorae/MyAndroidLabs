package com.nosorae.labs.ui.canvas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

class CanvasTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCanvas()
        }
    }
}

@Composable
fun MyCanvas() {
    // 사이즈가 필요하니 모디파이어 필요
    Canvas(modifier = Modifier
        .padding(20.dp)
        .size(300.dp)
    ) {
        drawRect(
            color = Color.Black,
            size = size,
        )
        drawRect(
            color = Color.Red,
            topLeft = Offset(100f,100f),
            size = Size(100f, 100f),
            style = Stroke(
                width = 5.dp.toPx()
            )
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.Red, Color.Yellow),
                center = Offset(300f, 300f),
                radius = 100f
            ),
            radius = 100f,
            center = Offset(300f, 300f)
        )
        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = false,
            topLeft = Offset(100f,500f),
            size = Size(200f, 200f),
            style = Stroke(
                width = 3.dp.toPx()
            )
        )

        drawOval(
            color = Color.Magenta,
            topLeft = Offset(500f,100f),
            size = Size(200f, 300f)
        )
        drawLine(
            color = Color.Cyan,
            start = Offset(300f, 700f),
            end = Offset(700f, 700f),
            strokeWidth = 5.dp.toPx()
        )
    }
}