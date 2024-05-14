package com.montebruni.salesfreight.resource.freightCalculator.handlers

import com.montebruni.salesfreight.resource.freightCalculator.FreightCalculatorInput
import org.springframework.stereotype.Component

@Component
class DefaultFreightCalculator : FreightCalculatorHandler {

    override fun calculate(input: FreightCalculatorInput): Double =
        if (input.calculatedValue <= FREIGHT_VALUE) FREIGHT_VALUE else input.calculatedValue

    companion object {
        private const val FREIGHT_VALUE = 10.0
    }
}
