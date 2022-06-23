package com.vlsantillan.imagetoresult.ui.camera

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.vlsantillan.domain.usecase.CalculatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the CameraScreen
 *
 * Created by Vincent Santillan on 23/06/2022.
 */
@HiltViewModel
class CameraViewModel @Inject constructor(private val calculatorUseCase: CalculatorUseCase) : ViewModel() {

    private val textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    fun readImage(bitmap: Bitmap) {
        val image = InputImage.fromBitmap(bitmap, 0)
        textRecognizer.process(image)
            .addOnSuccessListener {
                Log.e(CameraViewModel::class.java.simpleName, "Success: ${it.text}")
                val x = it.text[0].toString().toFloat()
                val y = it.text[2].toString().toFloat()
                val op = it.text[1].toString()
                val result = calculatorUseCase.calculate(x, y, op)
                Log.e(CameraViewModel::class.java.simpleName, "Success Res: $result")
            }
            .addOnFailureListener {
                Log.e(CameraViewModel::class.java.simpleName, "Error: ${it.message}")
            }
    }
}