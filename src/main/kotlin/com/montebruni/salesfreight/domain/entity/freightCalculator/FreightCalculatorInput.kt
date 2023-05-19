package com.montebruni.salesfreight.domain.entity.freightCalculator

import java.math.BigDecimal

data class FreightCalculatorInput(
    val height: Double,
    val width: Double,
    val length: Double,
    val weight: Double,
    val from: Coordinates,
    val to: Coordinates,
    var calculatedValue: Double = 0.0
) {

    fun isSameCoordinates() = from.latitude == to.latitude && from.longitude == to.longitude

    data class Coordinates(val latitude: BigDecimal, val longitude: BigDecimal)
}
