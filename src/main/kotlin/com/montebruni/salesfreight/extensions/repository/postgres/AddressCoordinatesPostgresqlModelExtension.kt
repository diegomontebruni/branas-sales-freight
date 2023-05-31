package com.montebruni.salesfreight.extensions.repository.postgres

import com.montebruni.salesfreight.domain.entity.AddressCoordinates
import com.montebruni.salesfreight.infra.repository.postgres.model.AddressCoordinatesPostgresqlModel

fun AddressCoordinatesPostgresqlModel.toAddressCoordinates() = AddressCoordinates(
    cep = cep,
    latitude = latitude,
    longitude = longitude
)
