package com.montebruni.salesfreight.usecase.input

import java.util.UUID

data class CalculateFreightInput(
    val fromCep: String,
    val toCep: String,
    val items: List<Item>
) {
    data class Item(
        val quantity: Int,
        val productId: UUID
    )
}
