package com.montebruni.salesfreight.resource.rest

import com.montebruni.salesfreight.resource.rest.request.CalculateFreightRequest
import com.montebruni.salesfreight.resource.rest.request.toCalculateFreightInput
import com.montebruni.salesfreight.resource.rest.response.CalculateFreightResponse
import com.montebruni.salesfreight.usecase.CalculateFreight
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/freights")
class FreightController(
    private val calculateFreight: CalculateFreight
) {

    @Operation(
        summary = "Calculate the shipping cost of the order based on items dimensions.",
        description = "Calculate the total shipping cost for the items.",
        tags = ["Orders"]
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "The checkout process is successful."),
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/simulate")
    fun simulateFreight(@RequestBody body: CalculateFreightRequest): CalculateFreightResponse =
        CalculateFreightResponse(freightAmount = calculateFreight.execute(body.toCalculateFreightInput()))
}
