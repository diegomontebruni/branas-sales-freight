package com.montebruni.salesfreight.usecase

import com.montebruni.salesfreight.domain.entity.Freight
import com.montebruni.salesfreight.domain.entity.Product
import com.montebruni.salesfreight.domain.port.AddressCoordinatesRepository
import com.montebruni.salesfreight.domain.port.FreightCalculator
import com.montebruni.salesfreight.domain.port.ProductRepository
import com.montebruni.salesfreight.usecase.input.CalculateFreightInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CalculateFreight(
    @Autowired private val freightCalculator: FreightCalculator,
    @Autowired private val addressCoordinatesRepository: AddressCoordinatesRepository,
    @Autowired private val productRepository: ProductRepository
){
    fun execute(input: CalculateFreightInput): Double {
        val fromCoordinates = addressCoordinatesRepository.findByCep(input.fromCep)
            ?: throw IllegalArgumentException("Invalid from cep")

        val toCoordinates = addressCoordinatesRepository.findByCep(input.toCep)
            ?: throw IllegalArgumentException("Invalid to cep")

        return input.items.sumOf {
            freightCalculator.calculate(
                Freight(
                    product = getProduct(it.productId),
                    quantity = it.quantity,
                    from = Freight.Coordinates(fromCoordinates.latitude, fromCoordinates.longitude),
                    to = Freight.Coordinates(toCoordinates.latitude, toCoordinates.longitude)
                )
            )
        }
    }

    private fun getProduct(id: UUID): Product = productRepository.findProductById(id)
        ?: throw IllegalArgumentException("Product not found with id $id")
}
