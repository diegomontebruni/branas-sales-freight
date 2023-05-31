package com.montebruni.salesfreight.resource.rest.request

import com.montebruni.salesfreight.usecase.input.CalculateFreightInput
import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

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
        @Schema(description = "Id of the item")
        val productId: UUID
    )
}

fun CalculateFreightRequest.toCalculateFreightInput() = CalculateFreightInput(
    fromCep = fromCep,
    toCep = toCep,
    items = items.map {
        CalculateFreightInput.Item(
            quantity = it.quantity,
            productId = it.productId
        )
    }
)
