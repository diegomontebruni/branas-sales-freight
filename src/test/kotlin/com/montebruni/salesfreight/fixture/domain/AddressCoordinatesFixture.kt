package com.montebruni.salesfreight.fixture.domain

import com.montebruni.salesfreight.domain.entity.AddressCoordinates
import java.math.BigDecimal

fun createAddressCoordinate() = AddressCoordinates(
    cep = "12312333",
    latitude = BigDecimal(10),
    longitude = BigDecimal(10)
)
