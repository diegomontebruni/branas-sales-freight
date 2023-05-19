package com.montebruni.salesfreight.domain.entity.freightCalculator

import com.montebruni.salesfreight.domain.entity.Freight
import com.montebruni.salesfreight.domain.entity.freightCalculator.handlers.VolumeFreightCalculator
import com.montebruni.salesfreight.domain.port.FreightCalculator
import com.montebruni.salesfreight.extensions.toDecimal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FreightCalculatorAdapter : FreightCalculator {

    @Autowired private lateinit var handler: VolumeFreightCalculator

    override fun calculate(input: Freight): Double = input.quantity * handler.calculate(
        FreightCalculatorInput(
            height = input.height,
            width = input.width,
            length = input.length,
            weight = input.weight,
            from = FreightCalculatorInput.Coordinates(latitude = input.from.latitude, longitude = input.from.longitude),
            to = FreightCalculatorInput.Coordinates(latitude = input.to.latitude, longitude = input.to.longitude)
        )
    ).toDecimal()
}
