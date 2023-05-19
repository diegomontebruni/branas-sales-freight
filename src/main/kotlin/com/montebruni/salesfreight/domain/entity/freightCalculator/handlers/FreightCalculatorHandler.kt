package com.montebruni.salesfreight.domain.entity.freightCalculator.handlers

import com.montebruni.salesfreight.domain.entity.freightCalculator.FreightCalculatorInput

interface FreightCalculatorHandler {
    fun calculate(input: FreightCalculatorInput) : Double
}
