package com.montebruni.salesfreight.domain.entity

import java.math.BigDecimal

data class AddressCoordinates(
    val cep: String,
    val latitude: BigDecimal,
    val longitude: BigDecimal
)
