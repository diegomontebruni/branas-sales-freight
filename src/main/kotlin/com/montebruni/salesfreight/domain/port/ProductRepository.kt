package com.montebruni.salesfreight.domain.port

import com.montebruni.salesfreight.domain.entity.Product
import java.util.UUID

interface ProductRepository {

    fun findProductById(id: UUID): Product?
}
