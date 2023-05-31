package com.montebruni.salesfreight.resource.freightCalculator.handlers

import com.montebruni.salesfreight.resource.freightCalculator.FreightCalculatorInput

interface FreightCalculatorHandler {
    fun calculate(input: FreightCalculatorInput) : Double
}
