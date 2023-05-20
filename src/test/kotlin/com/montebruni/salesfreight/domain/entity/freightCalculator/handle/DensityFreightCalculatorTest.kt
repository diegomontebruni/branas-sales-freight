package com.montebruni.salesfreight.domain.entity.freightCalculator.handle

import com.montebruni.salesfreight.common.UnitTests
import com.montebruni.salesfreight.domain.entity.freightCalculator.handlers.DensityFreightCalculator
import com.montebruni.salesfreight.domain.entity.freightCalculator.handlers.DistanceFreightCalculator
import com.montebruni.salesfreight.domain.entity.freightCalculator.FreightCalculatorInput
import com.montebruni.salesfreight.fixture.domain.createFreightCalculatorInput
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DensityFreightCalculatorTest(
    @MockK private val nextHandle: DistanceFreightCalculator
) : UnitTests() {

    @InjectMockKs
    private lateinit var densityFreightCalculator: DensityFreightCalculator

    @Test
    fun `should calculate density when given a valid input`() {
        val input = createFreightCalculatorInput().copy(calculatedValue = 0.003)
        val expected = 0.009999999999999998

        val nextHandleSlot = slot<FreightCalculatorInput>()

        every { nextHandle.calculate(capture(nextHandleSlot)) } returns expected

        val result = densityFreightCalculator.calculate(input)

        assertEquals(expected, nextHandleSlot.captured.calculatedValue)
        assertEquals(expected, result)

        verify { nextHandle.calculate(nextHandleSlot.captured) }
    }
}
