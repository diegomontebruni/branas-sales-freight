package com.montebruni.salesfreight.infra.repository.postgres.repository

import com.montebruni.salesfreight.common.DatabaseIT
import com.montebruni.salesfreight.fixture.infra.repository.postgres.createAddressCoordinatesPostgresqlModel
import com.montebruni.salesfreight.infra.repository.postgres.port.AddressCoordinatesPostgresqlRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class AddressCoordinatesPostgresqlRepositoryIT(
    @Autowired private val addressCoordinatesRepository: AddressCoordinatesPostgresqlRepository
) : DatabaseIT() {

    @Test
    fun `should find an address coordinates when given a valid cep`() {
        val address = createAddressCoordinatesPostgresqlModel().let { addressCoordinatesRepository.save(it) }

        val savedAddress = addressCoordinatesRepository.findByCep(address.cep)

        assertNotNull(savedAddress)
        assertEquals(address.id, savedAddress?.id)
        assertEquals(address.cep, savedAddress?.cep)
    }

    @Test
    fun `should return null when given an invalid cep`() {
        assertNull(addressCoordinatesRepository.findByCep("123"))
    }
}
