package com.montebruni.salesfreight.resource.freightCalculator.handlers

import com.montebruni.salesfreight.resource.freightCalculator.FreightCalculatorInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DistanceFreightCalculator : FreightCalculatorHandler {

    @Autowired
    private lateinit var nextHandle: DefaultFreightCalculator

    override fun calculate(input: FreightCalculatorInput): Double {
        if (input.isSameCoordinates()) return nextHandle.calculate(input)

        val radlat1 = calculateRad(input.from.latitude.toDouble())
        val radlat2 = calculateRad(input.to.latitude.toDouble())
        val theta = input.from.longitude - input.to.longitude
        val radtheta = theta.toDouble() * Math.PI / 180.0
        return convertMilesToKm(
            calculateInitialDist(radlat1, radlat2, radtheta).let { if (it > 1.0) 1.0 else it }
        ).let {
            nextHandle.calculate(input.copy(calculatedValue = it))
        }
    }

    fun calculateRad(value: Double): Double = value * Math.PI / 180.0
    fun calculateInitialDist(radLat1: Double, radLat2: Double, radTheta: Double) =
        Math.sin(radLat1) * Math.sin(radLat2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta)
    fun convertMilesToKm(dist: Double) = Math.acos(dist) *
        dist * 180.0 / Math.PI *
        dist * 60.0 * 1.1515 *
        dist * 1.609344
}
