package com.vlsantillan.imagetoresult

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.vlsantillan.imagetoresult.navigation.ImageToResultNavGraph
import com.vlsantillan.imagetoresult.ui.theme.ImageToResultTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPermissionsApi
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
                    )
                }) {
                    ImageToResultNavGraph()
                }
            }
        }
    }
}
