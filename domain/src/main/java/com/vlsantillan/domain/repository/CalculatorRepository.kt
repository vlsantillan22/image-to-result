package com.vlsantillan.domain.repository

/**
 * Interface for CalculatorRepository which consists of
 * arithmetic operation
 *
 * Created by Vincent Santillan on 23/06/2022.
 */
interface CalculatorRepository {

    fun add(addend1: Float, addend2: Float): Float

    fun subtract(minuend: Float, subtrahend: Float): Float

    fun multiply(multiplicand: Float, multiplier: Float): Float

    fun divide(dividend: Float, divisor: Float): Float
}