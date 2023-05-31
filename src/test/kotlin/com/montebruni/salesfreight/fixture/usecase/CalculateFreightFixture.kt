package com.montebruni.salesfreight.fixture.usecase

import com.montebruni.salesfreight.usecase.input.CalculateFreightInput
import java.util.*

fun createCalculateFreightInput() = CalculateFreightInput(
    fromCep = "12312541",
    toCep = "90710923",
    listOf(
        CalculateFreightInput.Item(
            quantity = 1,
            productId = UUID.randomUUID()
        ),
        CalculateFreightInput.Item(
            quantity = 1,
            productId = UUID.randomUUID()
        ),
        CalculateFreightInput.Item(
            quantity = 1,
            productId = UUID.randomUUID()
        )
    )
)
