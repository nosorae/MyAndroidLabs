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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            var textFieldSate by remember {
                // block recomposition
                mutableStateOf("")
            }
            val scope = rememberCoroutineScope()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp)
                ) {
                    TextField(
                        value = textFieldSate,
                        label = {
                            Text(text = "Enter your name")
                        },
                        onValueChange = { updatedString ->
                            textFieldSate = updatedString

                        },
                        singleLine = true,
                    )
                    Spacer(modifier = (Modifier.height(16.dp)))
                    Button(onClick = {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldSate")
                        }
                    }) {
                        Text(text = "please greet me")
                    }
                }
            }
            Snackbar {
                Text(text = "hello snackbar")
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


