package com.vlsantillan.imagetoresult.ui.home

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vlsantillan.imagetoresult.BuildConfig
import com.vlsantillan.imagetoresult.ui.camera.CameraViewModel
import com.vlsantillan.imagetoresult.util.ImageUtils
import java.io.IOException

/**
 * HomeScreen for showing input and result
 *
 * Created by Vincent Santillan on 23/06/2022.
 */
@Composable
fun HomeScreen(
    cameraViewModel: CameraViewModel = hiltViewModel(),
    onCameraSourceClick: () -> Unit
) {

    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            try {
                bitmap = ImageUtils.getBitmapFromContentUri(context.contentResolver, it)
            } catch (ex: IOException) {
                Log.e("HomeScreen", ex.message.toString())
            }
        }
    }

    Box(
        Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
        ) {
            val capturedImage = cameraViewModel.currentSource.collectAsState()
            val equation = cameraViewModel.calculationResult.collectAsState()
            val source = bitmap ?: capturedImage.value

            if (source != null) {
                cameraViewModel.readImage(source)
                Image(
                    bitmap = source.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .height(150.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillHeight
                )

                if (equation.value?.result != null) {
                    Text("Input: ${equation.value?.input1 ?: ""} ${equation.value?.operation ?: ""} ${equation.value?.input2 ?: ""}")
                    Text(text = "Result: ${equation.value?.result ?: ""}")
                } else {
                    Text(text = "Cannot read input. Please try again.")
                }
            } else {
                Text(text = "Add an image with simple arithmetic equation.")
            }
        }
        Button(
            onClick = {
                if (BuildConfig.FLAVOR_functionality == "camera") {
                    onCameraSourceClick()
                } else {
                    launcher.launch("image/*")
                    bitmap = null
                    cameraViewModel.clearResult()
                }
            },
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Add Input")
        }
    }
}