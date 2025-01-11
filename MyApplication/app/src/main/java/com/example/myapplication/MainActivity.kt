package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState

import androidx.compose.runtime.Composable

import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Navigation()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Navigation()
    }
}