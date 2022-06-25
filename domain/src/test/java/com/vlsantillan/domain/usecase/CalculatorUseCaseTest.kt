package com.vlsantillan.domain.usecase

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.vlsantillan.domain.repository.CalculatorRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

/**
 * CalculatorUseCase test
 *
 * Created by Vincent Santillan on 26/06/2022.
 */
class CalculatorUseCaseTest {

    companion object {
        fun create(testClass: Any) = TestRule { statement, _ ->
            initMocks(testClass)
            statement
        }
    }

    @Suppress("LeakingThis")
    @Rule
    @JvmField
    val injectMocks = create(this)

    @Mock
    lateinit var calculatorRepository: CalculatorRepository

    lateinit var calculatorUseCase: CalculatorUseCase

    @Before
    fun setup() {
        calculatorUseCase = CalculatorUseCase(calculatorRepository)
    }

    @Test
    fun calculate_addition_verified() {
        val argument1 = 1F
        val argument2 = 2F
        val operator = "+"
        calculatorUseCase.calculate(argument1, argument2, operator)
        verify(calculatorRepository).add(argument1, argument2)
        verifyNoMoreInteractions(calculatorRepository)
    }

    @Test
    fun calculate_subtraction_verified() {
        val argument1 = 1F
        val argument2 = 2F
        val operator = "-"
        calculatorUseCase.calculate(argument1, argument2, operator)
        verify(calculatorRepository).subtract(argument1, argument2)
        verifyNoMoreInteractions(calculatorRepository)
    }

    @Test
    fun calculate_multiplication_verified() {
        val argument1 = 1F
        val argument2 = 2F
        val operator = "*"
        calculatorUseCase.calculate(argument1, argument2, operator)
        verify(calculatorRepository).multiply(argument1, argument2)
        verifyNoMoreInteractions(calculatorRepository)
    }

    @Test
    fun calculate_division_verified() {
        val argument1 = 1F
        val argument2 = 2F
        val operator = "/"
        calculatorUseCase.calculate(argument1, argument2, operator)
        verify(calculatorRepository).divide(argument1, argument2)
        verifyNoMoreInteractions(calculatorRepository)
    }
}