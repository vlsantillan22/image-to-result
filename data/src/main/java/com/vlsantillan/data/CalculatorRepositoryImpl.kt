package com.vlsantillan.data

import com.vlsantillan.domain.repository.CalculatorRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Concrete implementation of CalculatorRepository
 *
 * Created by Vincent Santillan on 24/06/2022.
 */
@Singleton
class CalculatorRepositoryImpl @Inject constructor() : CalculatorRepository {
    override fun add(addend1: Float, addend2: Float): Float {
        return addend1 + addend2
    }

    override fun subtract(minuend: Float, subtrahend: Float): Float {
        return minuend - subtrahend
    }

    override fun multiply(multiplicand: Float, multiplier: Float): Float {
        return multiplicand * multiplier
    }

    override fun divide(dividend: Float, divisor: Float): Float {
        return dividend / divisor
    }
}