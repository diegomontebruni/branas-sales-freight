package com.montebruni.salesfreight.usecase

import com.montebruni.salesfreight.domain.valueobjects.PositiveDouble

data class CalculateFreightInput(
    val fromCep: String,
    val toCep: String,
    val items: List<Item>
) {
    data class Item(
        val quantity: Int,
        val height: PositiveDouble,
        val width: PositiveDouble,
        val length: PositiveDouble,
        val weight: PositiveDouble
    )
}
