package com.montebruni.salesfreight.domain.entity

import com.montebruni.salesfreight.domain.valueobjects.PositiveDouble
import java.math.BigDecimal

data class Freight(
    val height: PositiveDouble,
    val width: PositiveDouble,
    val length: PositiveDouble,
    val weight: PositiveDouble,
    val from: Coordinates,
    val to: Coordinates,
    val quantity: Int
) {

    data class Coordinates(val latitude: BigDecimal, val longitude: BigDecimal)
}
