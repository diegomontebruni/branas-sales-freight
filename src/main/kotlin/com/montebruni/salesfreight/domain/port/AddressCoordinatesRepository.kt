package com.montebruni.salesfreight.domain.port

import com.montebruni.salesfreight.domain.entity.AddressCoordinates

interface AddressCoordinatesRepository {

    fun findByCep(cep: String): AddressCoordinates?
}
