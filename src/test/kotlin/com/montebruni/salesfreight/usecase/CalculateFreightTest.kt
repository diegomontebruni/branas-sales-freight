package com.montebruni.salesfreight.usecase

import com.montebruni.salesfreight.common.UnitTests
import com.montebruni.salesfreight.domain.entity.Freight
import com.montebruni.salesfreight.domain.port.AddressCoordinatesRepository
import com.montebruni.salesfreight.domain.port.FreightCalculator
import com.montebruni.salesfreight.domain.port.ProductRepository
import com.montebruni.salesfreight.fixture.domain.createAddressCoordinate
import com.montebruni.salesfreight.fixture.domain.createProduct
import com.montebruni.salesfreight.fixture.usecase.createCalculateFreightInput
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.util.UUID

class CalculateFreightTest(
    @MockK private val freightCalculator: FreightCalculator,
    @MockK private val addressCoordinatesRepository: AddressCoordinatesRepository,
    @MockK private val productRepository: ProductRepository
) : UnitTests() {

    @InjectMockKs
    private lateinit var useCase: CalculateFreight

    @AfterEach
    internal fun tearDown() {
        confirmVerified(freightCalculator, addressCoordinatesRepository, productRepository)
    }

    @Test
    fun `should calculate a freight when given 3 products`() {
        val input = createCalculateFreightInput()
        val expectedTotalValue = 440.0
        val camFreight = 10.0
        val guitarFreight = 30.0
        val refrigeratorFreight = 400.0
        val fromAddressCoordinates = createAddressCoordinate()
        val toAddressCoordinates = createAddressCoordinate()
            .copy(cep = "89783627", latitude = BigDecimal(609), longitude = BigDecimal(199))

        val freightSlot = mutableListOf<Freight>()
        val addressCoordinateSlot = mutableListOf<String>()
        val productSlot = mutableListOf<UUID>()

        every { freightCalculator.calculate(capture(freightSlot)) } returns
            camFreight andThen guitarFreight andThen refrigeratorFreight
        every { addressCoordinatesRepository.findByCep(capture(addressCoordinateSlot)) } returns
            fromAddressCoordinates andThen toAddressCoordinates
        every { productRepository.findProductById(capture(productSlot)) } returns createProduct()

        val calculatedFreight = useCase.execute(input)

        assertEquals(expectedTotalValue, calculatedFreight)
        assertEquals(input.fromCep, addressCoordinateSlot.first())
        assertEquals(input.toCep, addressCoordinateSlot.last())

        freightSlot.forEach { verify { freightCalculator.calculate(it) } }
        addressCoordinateSlot.forEach { verify { addressCoordinatesRepository.findByCep(it) } }
        productSlot.forEach { verify { productRepository.findProductById(it) } }
    }

    @Test
    fun `should throw exception when has a invalid cep`() {
        val input = createCalculateFreightInput()

        every { addressCoordinatesRepository.findByCep(input.fromCep) } throws IllegalArgumentException()

        assertThrows<IllegalArgumentException> { useCase.execute(input) }

        verify { addressCoordinatesRepository.findByCep(input.fromCep) }
    }

    @Test
    fun `should throw exception when has a invalid product`() {
        val input = createCalculateFreightInput()
        val fromAddressCoordinates = createAddressCoordinate()
        val toAddressCoordinates = createAddressCoordinate()
            .copy(cep = "89783627", latitude = BigDecimal(609), longitude = BigDecimal(199))

        val addressCoordinateSlot = mutableListOf<String>()
        val productSlot = mutableListOf<UUID>()

        every { addressCoordinatesRepository.findByCep(capture(addressCoordinateSlot)) } returns
            fromAddressCoordinates andThen toAddressCoordinates
        every { productRepository.findProductById(capture(productSlot)) } throws IllegalArgumentException()

        assertThrows<IllegalArgumentException> { useCase.execute(input) }

        assertEquals(input.fromCep, addressCoordinateSlot.first())
        assertEquals(input.toCep, addressCoordinateSlot.last())

        addressCoordinateSlot.forEach { verify { addressCoordinatesRepository.findByCep(it) } }
        productSlot.forEach { verify { productRepository.findProductById(it) } }
    }
}
