package com.montebruni.salesfreight.resource.freightCalculator.handle

import com.montebruni.salesfreight.common.UnitTests
import com.montebruni.salesfreight.fixture.resource.freightCalculator.createFreightCalculatorInput
import com.montebruni.salesfreight.resource.freightCalculator.handlers.DefaultFreightCalculator
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DefaultFreightCalculatorTest : UnitTests() {

    @InjectMockKs
    private lateinit var defaultFreightCalculator: DefaultFreightCalculator

    @ParameterizedTest
    @ValueSource(doubles = [1.0, 2.0, 3.5, 9.0, 8.0, 9.999999999999998])
    fun `should calculate default freight value when value is less than default freight`(value: Double) {
        assertEquals(
            10.0,
            defaultFreightCalculator.calculate(createFreightCalculatorInput().copy(calculatedValue = value))
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [10.0000000001, 11.0, 100.0, 10000.0])
    fun `should calculate default freight value when value is greater than default freight`(value: Double) {
        assertEquals(
            value,
            defaultFreightCalculator.calculate(createFreightCalculatorInput().copy(calculatedValue = value))
        )
    }
}
