package com.montebruni.salesfreight.domain.entity.freightCalculator.handle

import com.montebruni.salesfreight.common.UnitTests
import com.montebruni.salesfreight.domain.entity.freightCalculator.handlers.DensityFreightCalculator
import com.montebruni.salesfreight.domain.entity.freightCalculator.FreightCalculatorInput
import com.montebruni.salesfreight.domain.entity.freightCalculator.handlers.VolumeFreightCalculator
import com.montebruni.salesfreight.fixture.domain.createFreightCalculatorInput
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VolumeFreightCalculatorTest(
    @MockK private val nextHandler: DensityFreightCalculator
) : UnitTests() {

    @InjectMockKs
    private lateinit var volumeFreightCalculator: VolumeFreightCalculator

    @Test
    fun `should calculate a volume when given a valid input`() {
        val input = createFreightCalculatorInput()
        val expectedVolume =  0.003
        val expectedOutput = 333.0

        val nextHandlerInput = slot<FreightCalculatorInput>()

        every { nextHandler.calculate(capture(nextHandlerInput)) } returns expectedOutput

        val result = volumeFreightCalculator.calculate(input)

        assertEquals(expectedVolume, nextHandlerInput.captured.calculatedValue)
        assertEquals(expectedOutput, result)

        verify { nextHandler.calculate(nextHandlerInput.captured) }
    }
}
