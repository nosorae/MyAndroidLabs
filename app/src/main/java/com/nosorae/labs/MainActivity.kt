package com.nosorae.labs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nosorae.labs.ui.theme.MyAndroidLabsTheme
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
}


