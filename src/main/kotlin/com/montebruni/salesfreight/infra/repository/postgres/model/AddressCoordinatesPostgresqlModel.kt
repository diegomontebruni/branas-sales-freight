package com.montebruni.salesfreight.infra.repository.postgres.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "address_coordinates")
data class AddressCoordinatesPostgresqlModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    @Column(name = "cep")
    val cep: String,

    @Column(name = "latitude")
    val latitude: BigDecimal,

    @Column(name = "longitude")
    val longitude: BigDecimal
)
