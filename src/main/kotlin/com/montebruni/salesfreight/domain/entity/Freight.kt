package com.montebruni.salesfreight.domain.entity

import java.math.BigDecimal

data class Freight(
    val product: Product,
    val from: Coordinates,
    val to: Coordinates,
    val quantity: Int
) {

    data class Coordinates(val latitude: BigDecimal, val longitude: BigDecimal)
}
