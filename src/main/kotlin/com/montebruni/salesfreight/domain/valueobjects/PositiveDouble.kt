package com.montebruni.salesfreight.domain.valueobjects

import java.math.BigDecimal
import java.math.RoundingMode

data class PositiveDouble(val value: Double) {

    init {
        require(value >= 0) { "Value is less than zero" }
    }

    constructor(value: String) : this(value.toDouble())
    constructor(value: Int) : this(value.toDouble())
    constructor(value: Long) : this(value.toDouble())
    constructor(value: BigDecimal) : this(value.setScale(2, RoundingMode.HALF_EVEN).toDouble())

    override fun toString(): String = value.toString()
}
