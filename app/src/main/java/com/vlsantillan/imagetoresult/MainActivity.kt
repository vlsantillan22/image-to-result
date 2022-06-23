package com.vlsantillan.imagetoresult

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vlsantillan.imagetoresult.navigation.ImageToResultNavGraph
import com.vlsantillan.imagetoresult.ui.theme.ImageToResultTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageToResultTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        title = { Text("Image to Result") },
                        backgroundColor = MaterialTheme.colors.primary
                    ) // todo change based on flavor
                }) {
                    ImageToResultNavGraph()
                }
            }
        }
    }
}
