package com.nosorae.labs.ui.compose

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.nosorae.labs.R
import com.nosorae.labs.databinding.ActivityMainBinding
import com.nosorae.labs.ui.compose.store.StoreItems
import com.nosorae.labs.ui.theme.MyAndroidLabsTheme
import kotlinx.coroutines.launch

/**
 *  대부분의 Compose UI 요소는 modifier 매개변수를 선택적으로 허용
 *  수정자는 상위 요소 레이아웃 내에서 UI 요소가 배치되고 표시되고 동작하는 방식을 UI 요소에 알려줍니다.
 * Compose의 세 가지 기본 표준 레이아웃 요소는 Column, Row, Box입니다.
 *
 */
class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContent {
            StoreItems().Appbar()
        }

        val imgRrc = listOf<Int>(
            R.drawable.esfp,
            R.drawable.esfj2,
            R.drawable.isfp,
            R.drawable.istj
        )
        val views = listOf<View>(
            binding.v1,
            binding.v2,
            binding.v3,
            binding.v4
        )
        listOf<ImageView>(
            binding.iv1,
            binding.iv2,
            binding.iv3,
            binding.iv4
        ).forEachIndexed { i, iv ->

            Glide.with(iv)
                .asBitmap()
                .centerCrop()
                .load(imgRrc[i])
                .into(object : BitmapImageViewTarget(iv) {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap?>?
                    ) {
                        super.onResourceReady(resource, transition)
                        Palette.from(resource).maximumColorCount(32).generate {
                            Log.e("sorae.no", "${it?.dominantSwatch?.rgb}")
                            it?.let {
                                val dominantColor = it.dominantSwatch?.rgb


                                views[i].setBackgroundColor(dominantColor!!)
                            }
                        }

                    }
                })
        }


//        setContent {
//            MyAndroidLabsTheme {
//                Screen()
//            }

//            val scaffoldState = rememberScaffoldState()
//            var textFieldSate by remember {
//                // block recomposition
//                mutableStateOf("")
//            }
//            val scope = rememberCoroutineScope()
//
//            Scaffold(
//                modifier = Modifier.fillMaxSize(),
//                scaffoldState = scaffoldState
//            ) {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(horizontal = 30.dp)
//                ) {
//                    TextField(
//                        value = textFieldSate,
//                        label = {
//                            Text(text = "Enter your name")
//                        },
//                        onValueChange = { updatedString ->
//                            textFieldSate = updatedString
//
//                        },
//                        singleLine = true,
//                    )
//                    Spacer(modifier = (Modifier.height(16.dp)))
//                    Button(onClick = {
//                        scope.launch {
//                            scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldSate")
//                        }
//                    }) {
//                        Text(text = "please greet me")
//                    }
//                }
//            }
//            Snackbar {
//                Text(text = "hello snackbar")
//            }

    }

    @Preview(showBackground = true, name = "Text preview", widthDp = 320)
    @Composable
    fun DefaultPreView() {
        MyAndroidLabsTheme {
            Greeting3(listOf("Android", "Compose", "Hi"))
        }
    }

    @Composable
    fun Greeting(text: String) {
        Surface(color = MaterialTheme.colors.primary) {
            Text(text = "Hello $text", modifier = Modifier.padding(30.dp))
        }
    }

    @Composable
    fun Greeting2(text: String) {
        Surface(color = MaterialTheme.colors.primary) {
            Column(modifier = Modifier.padding(30.dp)) {
                Text(text = "Hello")
                Text(text = text)
            }
        }
    }

    @Composable
    fun Greeting3(list: List<String>) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            list.forEach {
                Greeting3_2(text = it)
            }

        }
    }

    @Composable
    fun Greeting3_1(text: String) {

        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {

            Row(modifier = Modifier.padding(16.dp)) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Hello")
                    Text(text = text)
                }

                OutlinedButton(onClick = { /*TODO*/ }) {
                    Text(text = "Show more")
                }
            }

        }
    }

    @Composable
    fun Greeting3_2(text: String) {
        val expanded = remember { mutableStateOf(false) }
        val extraPadding = if (expanded.value) 48.dp else 0.dp
        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {

            Row(modifier = Modifier.padding(16.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = extraPadding)
                ) {
                    Text(text = "Hello")
                    Text(text = text)
                }

                OutlinedButton(onClick = { expanded.value = !expanded.value }) {
                    Text(text = if (expanded.value) "Show more" else "Show less")
                }
            }

        }
    }

    @Composable
    fun Greeting3_3(text: String) {
        val expanded = remember { mutableStateOf(false) }
        val extraPadding by animateDpAsState(
            targetValue = if (expanded.value) 48.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {

            Row(modifier = Modifier.padding(16.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = extraPadding.coerceAtLeast(0.dp))
                ) {
                    Text(text = "Hello")
                    Text(
                        text = text, style = MaterialTheme.typography.h4.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }

                OutlinedButton(onClick = { expanded.value = !expanded.value }) {
                    Text(text = if (expanded.value) "Show more" else "Show less")
                }
            }

        }
    }

    @Composable
    fun Greeting4(list: List<String> = List(1000) { "$it" }) {
        LazyColumn() {
            items(list) { name ->
                Card(name)
            }
        }
    }

    @Composable
    fun OnBoardingScreen(onContinueClick: () -> Unit) {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Welcome to the Basics Codelab!")
                Button(
                    modifier = Modifier.padding(top = 24.dp),
                    onClick = onContinueClick
                ) {
                    Text(text = "Continue")
                }
            }
        }
    }

    @Composable
    fun Card(name: String) {
        Card(
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            CardContent(name = name)
        }
    }

    @Composable
    fun CardContent(name: String) {
        var expanded by rememberSaveable { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(text = "Hello, ")
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (expanded) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4)
                    )
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = ""
                )
            }
        }
    }

    @Composable
    fun Screen() {
        var shouldShowOnBoarding by rememberSaveable {
            mutableStateOf(true)
        }

        if (shouldShowOnBoarding) {
            OnBoardingScreen {
                shouldShowOnBoarding = false
            }
        } else {
            Greeting4()
        }
    }


}