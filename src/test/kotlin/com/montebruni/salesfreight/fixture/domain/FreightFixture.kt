package com.montebruni.salesfreight.fixture.domain

import com.montebruni.salesfreight.domain.entity.Freight
import com.montebruni.salesfreight.domain.entity.freightCalculator.FreightCalculatorInput
import com.montebruni.salesfreight.extensions.toPositiveDouble
import java.math.BigDecimal

fun createFreight() = Freight(
    height = 20.0.toPositiveDouble(),
    width = 15.0.toPositiveDouble(),
    length = 10.0.toPositiveDouble(),
    weight = 1.0.toPositiveDouble(),
    quantity = 1,
    from = Freight.Coordinates(latitude = BigDecimal("123"), longitude = BigDecimal("123")),
    to = Freight.Coordinates(latitude = BigDecimal("456"), longitude = BigDecimal("456")),
)

fun createFreightCalculatorInput() = FreightCalculatorInput(
    width = 20.0,
    height = 15.0,
    length = 10.0,
    weight = 1.0,
    from = FreightCalculatorInput.Coordinates(latitude = BigDecimal(123), longitude = BigDecimal(123)),
    to = FreightCalculatorInput.Coordinates(latitude = BigDecimal(456), longitude = BigDecimal(456))
)
