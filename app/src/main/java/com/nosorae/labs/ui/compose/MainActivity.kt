package com.nosorae.labs.ui.compose

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nosorae.labs.ui.theme.MyAndroidLabsTheme
import kotlinx.coroutines.launch

/**
 *  대부분의 Compose UI 요소는 modifier 매개변수를 선택적으로 허용
 *  수정자는 상위 요소 레이아웃 내에서 UI 요소가 배치되고 표시되고 동작하는 방식을 UI 요소에 알려줍니다.
 * Compose의 세 가지 기본 표준 레이아웃 요소는 Column, Row, Box입니다.
 *
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAndroidLabsTheme {
                DefaultPreView()
            }

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
                Greeting3_1(text = it)
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

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        printLog("2 onCreateView")
        return super.onCreateView(name, context, attrs)

    }

    override fun onStart() {
        super.onStart()
        printLog("2 onStart")
    }

    override fun onResume() {
        super.onResume()
        printLog("2 onResume")
    }

    override fun onPause() {
        super.onPause()
        printLog("2 onPause")
    }

    override fun onStop() {
        super.onStop()
        printLog("2 onStop")
    }

    override fun onRestart() {
        super.onRestart()
        printLog("2 onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        printLog("2 onDestroy")
    }


    private fun printLog(message: String) {
        Log.e("activity's lifecylce", message)
    }

}