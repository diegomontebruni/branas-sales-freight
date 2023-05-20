package com.montebruni.salesfreight.fixture.resource.rest

import com.montebruni.salesfreight.resource.rest.request.CalculateFreightRequest

fun createCalculateFreightRequest() = CalculateFreightRequest(
    fromCep = "12341241",
    toCep = "09809876",
    items = listOf(
        CalculateFreightRequest.ItemRequest(
            quantity = 1,
            height = 20.0,
            width = 15.0,
            length = 10.0,
            weight = 1.0
        )
    )
)
