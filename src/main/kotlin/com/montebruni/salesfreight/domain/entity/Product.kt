package com.montebruni.salesfreight.domain.entity

import com.montebruni.salesfreight.domain.valueobjects.PositiveDouble

data class Product(
    val height: PositiveDouble,
    val width: PositiveDouble,
    val length: PositiveDouble,
    val weight: PositiveDouble,
)
