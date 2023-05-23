package com.montebruni.salesfreight.domain.port

import com.montebruni.salesfreight.domain.entity.Freight

interface FreightCalculator {

    fun calculate(input: Freight): Double
}
