package com.vlsantillan.imagetoresult.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * HomeScreen for showing input and result
 *
 * Created by Vincent Santillan on 23/06/2022.
 */
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel(), onAddInputClick: () -> Unit) {
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
        ) {
            val result = homeViewModel.calculationResult.collectAsState()
            Text(text = "Result: ${result.value ?: ""}")
        }
        Button(
            onClick = onAddInputClick,
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text(text = "Add Input")
        }
    }
}