package com.nosorae.labs.ui.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.coroutines.delay
import java.lang.Thread.sleep

class ConstraintLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        printLog("1 onCreate")
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
                Box(
                    modifier = Modifier
                    .background(Color.Green)
                    .layoutId("greenBox")
                )
                Button(modifier = Modifier.layoutId("redBox"), onClick = {
                    startActivity(Intent(this, MainActivity::class.java))
                    //sendBroadcastMessage("sorae")
                }) {

                }

            }
        }

    }

    private fun sendBroadcastMessage(message: String) {
        val intent = Intent().also {
            Log.d("labslogtag", "sendBroadcastMessage")
            it.setAction("com.nosorae.labs.MY_NOTIFICATION")
            //intent.putExtra("data", "HaYeon is beautiful woman! message is $message")

        }
        val isOk = LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
        Log.d("labslogtag", "sendBroadcastMessage isOk : $isOk")
    }


    /**
     * lifecycle test
     */
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        printLog("1 onCreateView")
        return super.onCreateView(name, context, attrs)
    }

    override fun onStart() {
        super.onStart()
        printLog("1 onStart")
    }

    override fun onResume() {
        super.onResume()
        printLog("1 onResume")
    }

    override fun onPause() {
        super.onPause()
        printLog("1 onPause")
    }

    override fun onStop() {
        super.onStop()
        printLog("1 onStop")
    }

    override fun onRestart() {
        super.onRestart()
        printLog("1 onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        printLog("1 onDestroy")
    }


    private fun printLog(message: String) {
        Log.e("activity's lifecylce", message)
    }
}