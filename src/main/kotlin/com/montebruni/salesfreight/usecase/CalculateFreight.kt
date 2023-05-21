package com.montebruni.salesfreight.usecase

import com.montebruni.salesfreight.domain.entity.Freight
import com.montebruni.salesfreight.domain.port.AddressCoordinatesRepository
import com.montebruni.salesfreight.domain.port.FreightCalculator
import com.montebruni.salesfreight.domain.port.StorageClient
import com.montebruni.salesfreight.usecase.input.CalculateFreightInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CalculateFreight(
    @Autowired private val freightCalculator: FreightCalculator,
    @Autowired private val addressCoordinatesRepository: AddressCoordinatesRepository,
    @Autowired private val storageClient: StorageClient
){
    fun execute(input: CalculateFreightInput): Double {
        val fromCoordinates = addressCoordinatesRepository.findByCep(input.fromCep)
            ?: throw IllegalArgumentException("Invalid from Cep")

        val toCoordinates = addressCoordinatesRepository.findByCep(input.toCep)
            ?: throw IllegalArgumentException("Invalid to Cep")

        return input.items.sumOf {
            freightCalculator.calculate(
                Freight(
                    product = storageClient.findProductById(it.productId),
                    quantity = it.quantity,
                    from = Freight.Coordinates(fromCoordinates.latitude, fromCoordinates.longitude),
                    to = Freight.Coordinates(toCoordinates.latitude, toCoordinates.longitude)
                )
            )
        }
    }
}
