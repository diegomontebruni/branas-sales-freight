package com.montebruni.salesfreight.resource.client.salescatalog

import com.montebruni.salesfreight.common.UnitTests
import com.montebruni.salesfreight.extensions.toPositiveDouble
import com.montebruni.salesfreight.resource.client.salescatalog.response.SalesCatalogResponse
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class SalesCatalogAdapterTest(
    @MockK private val client: SalesCatalogClient
) : UnitTests() {

    @InjectMockKs
    private lateinit var salesCatalog: SalesCatalogAdapter

    @Test
    fun `should successfully call catalog sales and return a product`() {
        val productId = UUID.randomUUID()
        val response = SalesCatalogResponse(
            height = 10.0,
            width = 10.0,
            length = 10.0,
            weight = 10.0
        )

        every { client.findById(productId) } returns response

        val result = salesCatalog.findProductById(productId)

        assertEquals(result.height, response.height.toPositiveDouble())
        assertEquals(result.length, response.length.toPositiveDouble())
        assertEquals(result.weight, response.weight.toPositiveDouble())
        assertEquals(result.width, response.width.toPositiveDouble())

        verify { client.findById(productId) }
    }
}
