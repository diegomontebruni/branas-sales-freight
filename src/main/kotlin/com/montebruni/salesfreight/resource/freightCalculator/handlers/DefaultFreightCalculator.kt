package com.montebruni.salesfreight.resource.freightCalculator.handlers

import com.montebruni.salesfreight.resource.freightCalculator.FreightCalculatorInput
import org.springframework.stereotype.Component

@Component
class DefaultFreightCalculator : FreightCalculatorHandler {

    private val defaultFreightValue = 10.0
    override fun calculate(input: FreightCalculatorInput): Double =
        if (input.calculatedValue <= defaultFreightValue) defaultFreightValue else input.calculatedValue
}
