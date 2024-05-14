package com.montebruni.salesfreight.resource.client.salescatalog

import com.montebruni.salesfreight.domain.entity.Product
import com.montebruni.salesfreight.domain.port.ProductRepository
import com.montebruni.salesfreight.extensions.toPositiveDouble
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SalesCatalogAdapter(
    @Autowired private val client: SalesCatalogClient
) : ProductRepository {

    override fun findProductById(id: UUID): Product =
        client.findById(id).let {
            Product(
                height = it.height.toPositiveDouble(),
                width = it.width.toPositiveDouble(),
                length = it.length.toPositiveDouble(),
                weight = it.weight.toPositiveDouble()
            )
        }
}
