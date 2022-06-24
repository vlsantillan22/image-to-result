package com.vlsantillan.imagetoresult.ui.camera

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.vlsantillan.imagetoresult.util.ImageUtils
import java.util.concurrent.Executor

/**
 * UI for Camera
 *
 * Created by Vincent Santillan on 23/06/2022.
 */
@ExperimentalPermissionsApi
@Composable
fun CameraScreen(
    cameraViewModel: CameraViewModel = hiltViewModel(),
    onCaptureClick: () -> Unit
) {
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    LaunchedEffect(Unit) {
        cameraViewModel.clearBitmap()
    }

    when (cameraPermissionState.status) {
        is PermissionStatus.Granted -> {
            CameraFeed(context) { image ->
                context.findActivity()?.let {
                    cameraViewModel.readImage(
                        ImageUtils.convertImageProxyToBitmap(
                            image,
                            it
                        )
                    )
                }
                onCaptureClick()
            }
        }
        is PermissionStatus.Denied -> {
            LaunchedEffect(Unit) {
                cameraPermissionState.launchPermissionRequest()
            }
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    cameraPermissionState.launchPermissionRequest()
                }) {
                    Text("Request camera permission")
                }
            }
        }
    }
}

@Composable
fun CameraFeed(context: Context, onCaptureClick: (ImageProxy) -> Unit) {
    ConstraintLayout(
        Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {

        val (cameraPreview, captureButton) = createRefs()
        val executor = remember(context) { ContextCompat.getMainExecutor(context) }
        val imageCapture: MutableState<ImageCapture?> = remember { mutableStateOf(null) }

        CameraView(
            modifier = Modifier
                .constrainAs(cameraPreview) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(captureButton.top, 24.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }, imageCapture = imageCapture, executor = executor, context = context
        )

        FloatingActionButton(onClick = {
            imageCapture.value?.takePicture(
                executor,
                object : ImageCapture.OnImageCapturedCallback() {
                    override fun onCaptureSuccess(image: ImageProxy) {
                        onCaptureClick(image)
                    }

                    override fun onError(exception: ImageCaptureException) {
                        super.onError(exception)
                        Toast.makeText(context, exception.message, Toast.LENGTH_LONG)
                            .show()
                    }
                })
        }, modifier = Modifier.constrainAs(captureButton) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom, 32.dp)
        }, backgroundColor = Color.White) {
        }
    }
}

@Composable
fun CameraView(
    modifier: Modifier,
    imageCapture: MutableState<ImageCapture?>,
    executor: Executor,
    context: Context
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraPreview = remember { PreviewView(context) }
    val cameraProviderFuture = remember(context) { ProcessCameraProvider.getInstance(context) }
    val cameraProvider = remember(cameraProviderFuture) { cameraProviderFuture.get() }

    AndroidView(modifier = modifier, factory = {
        cameraProviderFuture.addListener(
            {
                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()

                imageCapture.value = ImageCapture.Builder().build()

                cameraProvider.unbindAll()

                val prev = Preview.Builder().build().also {
                    it.setSurfaceProvider(cameraPreview.surfaceProvider)
                }

                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    imageCapture.value,
                    prev
                )
            }, executor
        )
        cameraPreview
    })
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}