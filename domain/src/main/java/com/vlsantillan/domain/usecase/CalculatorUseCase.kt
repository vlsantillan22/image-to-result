package com.vlsantillan.domain.usecase

import com.vlsantillan.domain.model.Equation
import com.vlsantillan.domain.repository.CalculatorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Usecase for calculator
 *
 * Created by Vincent Santillan on 23/06/2022.
 */
@Singleton
class CalculatorUseCase @Inject constructor(private val repository: CalculatorRepository) {

    private val _currentResult = MutableStateFlow<Equation?>(null)
    val currentResult: StateFlow<Equation?> = _currentResult

    /**
     * This will calculate [x] and [y] using the [operator]
     * and returns the answer
     *
     * @param x the first number to compute
     * @param y the second number to compute
     * @param operator the operation to be performed
     */
    fun calculate(x: Float, y: Float, operator: String): Float? {
        val result = when (operator) {
            "+" -> repository.add(x, y)
            "-" -> repository.subtract(x, y)
            "x", "*" -> repository.multiply(x, y)
            "/" -> repository.divide(x, y)
            else -> null
        }
        result?.let {
            _currentResult.value = Equation(x, y, operator, it)
        }

        return result
    }
}