package com.vlsantillan.imagetoresult.ui.camera

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

/**
 * ViewModel for the CameraScreen
 *
 * Created by Vincent Santillan on 23/06/2022.
 */
class CameraViewModel : ViewModel() {

    private val textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    fun readImage(bitmap: Bitmap) {
        val image = InputImage.fromBitmap(bitmap, 0)
        textRecognizer.process(image)
            .addOnSuccessListener {
                Log.e(CameraViewModel::class.java.simpleName, "Success: ${it.text}")
            }
            .addOnFailureListener {
                Log.e(CameraViewModel::class.java.simpleName, "Error: ${it.message}")
            }
    }
}