package com.montebruni.salesfreight.resource.client.salescatalog

import com.github.tomakehurst.wiremock.client.WireMock
import com.montebruni.salesfreight.common.BaseHTTPClientIT
import com.montebruni.salesfreight.domain.port.ProductRepository
import com.montebruni.salesfreight.fixture.domain.createProduct
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.test.context.ContextConfiguration
import java.util.*

class SalesCatalogAdapterIT(
    private val salesCatalog: SalesCatalogAdapter
) : BaseHTTPClientIT() {

    @Test
    fun `should successfully call catalog sales and return a product`() {
        val productId = UUID.randomUUID()
        val expectedResult = createProduct()

        wmServer.stubFor(
            WireMock.get("/$productId")
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(expectedResult))
                )
        )

        val result = salesCatalog.findProductById(productId)

        assertEquals(expectedResult.height, result.height)
        assertEquals(expectedResult.length, result.length)
        assertEquals(expectedResult.weight, result.weight)
        assertEquals(expectedResult.width, result.width)

        //wmServer.verify(WireMock.postRequestedFor(WireMock.urlEqualTo(url)))
    }
}
