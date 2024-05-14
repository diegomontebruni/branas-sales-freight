package com.montebruni.salesfreight.resource.freightCalculator.handle

import com.montebruni.salesfreight.common.UnitTests
import com.montebruni.salesfreight.fixture.resource.freightCalculator.createFreightCalculatorInput
import com.montebruni.salesfreight.resource.freightCalculator.FreightCalculatorInput
import com.montebruni.salesfreight.resource.freightCalculator.handlers.DefaultFreightCalculator
import com.montebruni.salesfreight.resource.freightCalculator.handlers.DistanceFreightCalculator
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DistanceFreightCalculatorTest(
    @MockK private val nextHandle: DefaultFreightCalculator
) : UnitTests() {

    @InjectMockKs
    private lateinit var distanceFreightCalculator: DistanceFreightCalculator

    @Test
    fun `should calculate distance freight when given a valid input`() {
        val input = createFreightCalculatorInput().copy(calculatedValue = 0.009999999999999998)
        val expectedOutput = 2139.059195059149

        val nextHandleSlot = slot<FreightCalculatorInput>()

        every { nextHandle.calculate(capture(nextHandleSlot)) } returns expectedOutput

        val result = distanceFreightCalculator.calculate(input)

        assertEquals(expectedOutput, result)
        assertEquals(expectedOutput, nextHandleSlot.captured.calculatedValue)

        verify { nextHandle.calculate(nextHandleSlot.captured) }
    }
}
