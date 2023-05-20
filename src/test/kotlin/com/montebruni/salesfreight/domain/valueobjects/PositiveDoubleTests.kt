package com.montebruni.salesfreight.domain.valueobjects

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.math.BigDecimal

class PositiveDoubleTests {

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 1.0, 1.55, 2.34, 5.91, 1.11, 999.99, 9999.999])
    fun `should create Positive double from double values`(value: Double) {
        assertDoesNotThrow { PositiveDouble(value) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["0.0", "1.0", "1.55", "999.99", "9999.999"])
    fun `should create Positive double from string values`(value: String) {
        assertDoesNotThrow { PositiveDouble(value) }
    }

    @ParameterizedTest
    @ValueSource(longs = [0, 10, 155, 234, 591, 111, 99999, 9999999])
    fun `should create Positive double from long values`(value: Long) {
        assertDoesNotThrow { PositiveDouble(value) }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 5, 999, 123456])
    fun `should create Positive double from int values`(value: Int) {
        assertDoesNotThrow { PositiveDouble(value) }
    }

    @Test
    fun `should create Positive double from big decimal values`() {
        assertDoesNotThrow { PositiveDouble(BigDecimal(10)) }
    }

    @ParameterizedTest
    @ValueSource(doubles = [-1.0, -15.131, -999.99])
    fun `should throw exception when try to create positive double from negative values`(value: Double) {
        assertThrows<IllegalArgumentException> { PositiveDouble(value) }
    }
}
