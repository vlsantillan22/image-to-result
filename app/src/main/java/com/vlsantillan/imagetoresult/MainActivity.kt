package com.vlsantillan.imagetoresult

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.vlsantillan.imagetoresult.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContent {
        //     ImageToResultTheme {
        //         Scaffold(topBar = {
        //             TopAppBar(
        //                 title = { Text("Image to Result") },
        //                 backgroundColor = MaterialTheme.colors.primary
        //             )
        //         }) {
        //             ImageToResultNavGraph()
        //         }
        //     }
        // }
    }
}
