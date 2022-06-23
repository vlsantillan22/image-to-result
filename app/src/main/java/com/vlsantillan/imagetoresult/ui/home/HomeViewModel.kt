package com.vlsantillan.imagetoresult.ui.home

import androidx.lifecycle.ViewModel
import com.vlsantillan.domain.usecase.CalculatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for HomeScreen
 *
 * Created by Vincent Santillan on 23/06/2022.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(calculatorUseCase: CalculatorUseCase): ViewModel() {

    val calculationResult = calculatorUseCase.currentResult
}