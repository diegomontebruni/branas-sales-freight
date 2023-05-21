package com.montebruni.salesfreight.resource.client.branasstorage

import com.montebruni.salesfreight.domain.entity.Product
import com.montebruni.salesfreight.domain.port.StorageClient
import com.montebruni.salesfreight.extensions.toPositiveDouble
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BranasStorageAdapter(
    @Autowired private val client: BranasStorageClient
) : StorageClient {
    override fun findProductById(id: UUID): Product = client.findById(id).let {
        Product(
            height = it.height.toPositiveDouble(),
            width = it.width.toPositiveDouble(),
            length = it.length.toPositiveDouble(),
            weight = it.weight.toPositiveDouble()
        )
    }
}
