package com.montebruni.salesfreight.fixture.resource.rest

import com.montebruni.salesfreight.resource.rest.request.CalculateFreightRequest
import java.util.*

fun createCalculateFreightRequest() = CalculateFreightRequest(
    fromCep = "12341241",
    toCep = "09809876",
    items = listOf(
        CalculateFreightRequest.ItemRequest(
            quantity = 1,
            productId = UUID.randomUUID()
        )
    )
)
