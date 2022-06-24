package com.vlsantillan.imagetoresult.ui.camera

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.vlsantillan.domain.usecase.CalculatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import net.objecthunter.exp4j.shuntingyard.ShuntingYard
import net.objecthunter.exp4j.tokenizer.NumberToken
import net.objecthunter.exp4j.tokenizer.OperatorToken
import javax.inject.Inject

/**
 * ViewModel for the CameraScreen
 *
 * Created by Vincent Santillan on 23/06/2022.
 */
@HiltViewModel
class CameraViewModel @Inject constructor(private val calculatorUseCase: CalculatorUseCase) :
    ViewModel() {

    private val _currentSource = MutableStateFlow<Bitmap?>(null)
    var currentSource: StateFlow<Bitmap?> = _currentSource

    val calculationResult = calculatorUseCase.currentResult

    private val textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    init {
        Log.e("ViewModel", "is init")
    }

    fun readImage(bitmap: Bitmap) {
        _currentSource.value = bitmap
        val image = InputImage.fromBitmap(bitmap, 0)
        textRecognizer.process(image)
            .addOnSuccessListener {
                Log.e(CameraViewModel::class.java.simpleName, "Success: ${it.text}")
                val tokens =
                    ShuntingYard.convertToRPN(it.text.replace("x", "*"), emptyMap(), emptyMap(), emptySet(), true)

                var input1: Double? = null
                var input2: Double? = null
                var operator = ""

                if (tokens.size < 3) {
                    return@addOnSuccessListener
                }

                tokens.asList().subList(0, 3).forEach { token ->
                    when (token) {
                        is OperatorToken -> {
                            if (operator.isEmpty()) {
                                operator = token.operator.symbol
                            }
                        }
                        is NumberToken -> {
                            if (input1 == null) {
                                input1 = token.value
                            } else {
                                input2 = token.value
                            }
                        }
                    }
                }
                if (input1 != null && input2 != null && operator.isNotEmpty()) {
                    calculatorUseCase.calculate(input1!!.toFloat(), input2!!.toFloat(), operator)
                }
            }
            .addOnFailureListener {
                Log.e(CameraViewModel::class.java.simpleName, "Error: ${it.message}")
            }
    }

    fun clearBitmap() {
        _currentSource.value = null
    }
}