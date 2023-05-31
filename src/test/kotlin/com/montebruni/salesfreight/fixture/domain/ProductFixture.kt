package com.montebruni.salesfreight.fixture.domain

import com.montebruni.salesfreight.domain.entity.Product
import com.montebruni.salesfreight.extensions.toPositiveDouble

fun createProduct() = Product(
    height = 20.0.toPositiveDouble(),
    width = 15.0.toPositiveDouble(),
    length = 10.0.toPositiveDouble(),
    weight = 1.0.toPositiveDouble(),
)
