package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    // get view model private variable -- ask chatgpt how to describe thi sin interview
    private val viewModel: InstrumentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    InstrumentScreen(viewModel) // pass in viewModel
                }
            }
        }
    }
    // create override for onResume, super.onResume and call fetch Instruments
    // implement fetch instruments using a try catch inside a lifecycle coroutine
    // in the try build the retrofit variable, and the api and response to getinstrumentdata
    // catch the (e: Exception) and e.printStackTrace()
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

