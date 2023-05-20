package com.montebruni.salesfreight.fixture.infra.repository.postgres

import com.montebruni.salesfreight.infra.repository.postgres.model.AddressCoordinatesPostgresqlModel
import java.math.BigDecimal
import java.util.*

fun createAddressCoordinatesPostgresqlModel() = AddressCoordinatesPostgresqlModel(
    id = UUID.randomUUID(),
    cep = "12312312",
    latitude = BigDecimal(-23.4947731),
    longitude = BigDecimal(-46.6163366)
)
