package com.montebruni.salesfreight.resource.client.branasstorage

import com.montebruni.salesfreight.resource.client.branasstorage.response.BranasStorageResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

@FeignClient(
    url = "\${client.branasstorage.product.host}",
    path = "/v1/product",
    name = "branas-storage-product-client"
)
interface BranasStorageClient {

    @GetMapping
    fun findById(@RequestParam id: UUID): BranasStorageResponse
}
