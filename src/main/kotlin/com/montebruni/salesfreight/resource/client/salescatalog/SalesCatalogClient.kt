package com.montebruni.salesfreight.resource.client.salescatalog

import com.montebruni.salesfreight.resource.client.salescatalog.response.SalesCatalogResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

@FeignClient(
    url = "\${client.sales-catalog.product.host}",
    path = "/v1/product",
    name = "sales-catalog-product-client"
)
interface SalesCatalogClient {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): SalesCatalogResponse
}
