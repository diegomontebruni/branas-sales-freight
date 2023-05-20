package com.montebruni.salesfreight.resource.rest.request

import com.montebruni.salesfreight.domain.valueobjects.PositiveDouble
import com.montebruni.salesfreight.usecase.CalculateFreightInput
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Request to simulate freight")
data class CalculateFreightRequest(
    @Schema(description = "Cep address from send")
    val fromCep: String,
    @Schema(description = "Cep address to send")
    val toCep: String,
    @Schema(description = "The items for the simulation")
    val items: List<ItemRequest>
) {
    data class ItemRequest(
        @Schema(description = "Quantity of the item", example = "10")
        val quantity: Int,
        @Schema(description = "Height of the item", example = "20.0")
        val height: Double,
        @Schema(description = "Width of the item", example = "15.0")
        val width: Double,
        @Schema(description = "Length of the item", example = "5.0")
        val length: Double,
        @Schema(description = "Weight of the item", example = "1.0")
        val weight: Double
    )
}

fun CalculateFreightRequest.toCalculateFreightInput() = CalculateFreightInput(
    fromCep = fromCep,
    toCep = toCep,
    items = items.map {
        CalculateFreightInput.Item(
            quantity = it.quantity,
            height = PositiveDouble(it.height),
            width = PositiveDouble(it.width),
            length = PositiveDouble(it.length),
            weight = PositiveDouble(it.weight)
        )
    }
)
