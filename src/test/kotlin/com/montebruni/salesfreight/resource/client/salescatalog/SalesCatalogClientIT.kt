package com.montebruni.salesfreight.resource.client.salescatalog

import com.github.tomakehurst.wiremock.client.WireMock
import com.montebruni.salesfreight.common.BaseHTTPClientIT
import com.montebruni.salesfreight.common.stubGet
import com.montebruni.salesfreight.resource.client.salescatalog.response.SalesCatalogResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.UUID

class SalesCatalogClientIT(
    @Autowired private val client: SalesCatalogClient
) : BaseHTTPClientIT() {

    private val baseUrl = "/v1/products"

    @Test
    fun `should successfully call catalog sales and return a product`() {
        val productId = UUID.randomUUID()
        val expectedResult = SalesCatalogResponse(
            height = 1.0,
            width = 2.0,
            length = 3.0,
            weight = 4.0
        )
        val url = "$baseUrl/$productId"

        wmServer.stubGet(
            url = url,
            responseBody = mapper.writeValueAsString(expectedResult)
        )

        val result = client.findById(productId)

        assertEquals(expectedResult.height, result.height)
        assertEquals(expectedResult.length, result.length)
        assertEquals(expectedResult.weight, result.weight)
        assertEquals(expectedResult.width, result.width)

        wmServer.verify(
            WireMock.getRequestedFor(WireMock.urlEqualTo(url))
        )
    }
}
