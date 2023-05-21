package com.montebruni.salesfreight.infra.repository.postgres.port

import com.montebruni.salesfreight.infra.repository.postgres.model.AddressCoordinatesPostgresqlModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AddressCoordinatesPostgresqlRepository : JpaRepository<AddressCoordinatesPostgresqlModel, UUID> {

    fun findByCep(cep: String): AddressCoordinatesPostgresqlModel?
}
