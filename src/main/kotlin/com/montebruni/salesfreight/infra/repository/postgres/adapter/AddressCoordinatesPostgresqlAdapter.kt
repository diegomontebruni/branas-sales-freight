package com.montebruni.salesfreight.infra.repository.postgres.adapter

import com.montebruni.salesfreight.domain.entity.AddressCoordinates
import com.montebruni.salesfreight.domain.port.AddressCoordinatesRepository
import com.montebruni.salesfreight.extensions.repository.postgres.toAddressCoordinates
import com.montebruni.salesfreight.infra.repository.postgres.port.AddressCoordinatesPostgresqlRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AddressCoordinatesPostgresqlAdapter(
    @Autowired private val addressCoordinatesRepository: AddressCoordinatesPostgresqlRepository
) : AddressCoordinatesRepository {

    override fun findByCep(cep: String): AddressCoordinates? = addressCoordinatesRepository.findByCep(cep)
        ?.toAddressCoordinates()
}
