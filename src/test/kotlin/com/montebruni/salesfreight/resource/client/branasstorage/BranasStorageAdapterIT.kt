package com.montebruni.salesfreight.resource.client.branasstorage

import com.github.tomakehurst.wiremock.client.WireMock
import com.montebruni.salesfreight.common.BaseHTTPClientIT
import com.montebruni.salesfreight.domain.port.StorageClient
import com.montebruni.salesfreight.fixture.domain.createProduct
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import java.util.*

class BranasStorageAdapterIT(
    @MockK private val branasStorageAdapter: StorageClient
) : BaseHTTPClientIT() {

    val url = ""

    @Test
    fun `should successfully call branas storage and return a product`() {
        val productId = UUID.randomUUID()
        val expectedResult = createProduct()

        wmServer.stubFor(
            WireMock.post(url)
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(expectedResult))
                )
        )

        val result = branasStorageAdapter.findProductById(productId)!!

        assertEquals(expectedResult.height, result.height)
        assertEquals(expectedResult.length, result.length)
        assertEquals(expectedResult.weight, result.weight)
        assertEquals(expectedResult.width, result.width)

        wmServer.verify(WireMock.postRequestedFor(WireMock.urlEqualTo(url)))
    }
}
