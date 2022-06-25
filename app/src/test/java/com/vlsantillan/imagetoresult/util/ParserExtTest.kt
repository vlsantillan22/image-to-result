package com.vlsantillan.imagetoresult.util

import com.vlsantillan.imagetoresult.util.ParserExt.removeInvalidChar
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for ParserExt functions
 *
 * Created by Vincent Santillan on 25/06/2022.
 */
class ParserExtTest {

    @Test
    fun removeInvalidChar_addition_isSuccess() {
        val equation = "testi12.34+321sdfl#\$%"
        val expectedResult = "12.34+321"
        val actual = equation.removeInvalidChar()

        assertEquals(expectedResult, actual)
    }

    @Test
    fun removeInvalidChar_subtraction_isSuccess() {
        val equation = "testi12.34-321sdfl#\$%"
        val expectedResult = "12.34-321"
        val actual = equation.removeInvalidChar()

        assertEquals(expectedResult, actual)
    }

    @Test
    fun removeInvalidChar_multiplication_isSuccess() {
        val equation1 = "testi12.34*321sdfl#\$%"
        val expectedResult1 = "12.34*321"

        val equation2 = "testi12.34x321sdfl#\$%"
        val expectedResult2 = "12.34*321"

        val actual1 = equation1.removeInvalidChar()
        val actual2 = equation2.removeInvalidChar()

        assertEquals(expectedResult1, actual1)
        assertEquals(expectedResult2, actual2)
    }

    @Test
    fun removeInvalidChar_division_isSuccess() {
        val equation = "testi12.34/321sdfl#\$%"
        val expectedResult = "12.34/321"

        val actual = equation.removeInvalidChar()

        assertEquals(expectedResult, actual)
    }
}