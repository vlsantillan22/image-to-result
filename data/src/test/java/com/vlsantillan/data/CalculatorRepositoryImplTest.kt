package com.vlsantillan.data

import com.vlsantillan.domain.repository.CalculatorRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Unit test for CalculatorRepositoryImpl
 *
 * Created by Vincent Santillan on 26/06/2022.
 */
class CalculatorRepositoryImplTest {

    lateinit var repository: CalculatorRepository

    @Before
    fun setUp() {
        repository = CalculatorRepositoryImpl()
    }

    @Test
    fun add_success() {
        val argument1 = 5F
        val argument2 = 5F
        val expectedResult = 10F

        val actual = repository.add(argument1, argument2)

        assertEquals(expectedResult, actual)
    }

    @Test
    fun subtract_success() {
        val argument1 = 5F
        val argument2 = 3F
        val expectedResult = 2F

        val actual = repository.subtract(argument1, argument2)

        assertEquals(expectedResult, actual)
    }

    @Test
    fun multiply_success() {
        val argument1 = 5F
        val argument2 = 5F
        val expectedResult = 25F

        val actual = repository.multiply(argument1, argument2)

        assertEquals(expectedResult, actual)
    }

    @Test
    fun divide_success() {
        val argument1 = 5F
        val argument2 = 5F
        val expectedResult = 1F

        val actual = repository.divide(argument1, argument2)

        assertEquals(expectedResult, actual)
    }
}