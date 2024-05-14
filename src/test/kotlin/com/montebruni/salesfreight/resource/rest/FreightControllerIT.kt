package com.montebruni.salesfreight.resource.rest

import com.montebruni.salesfreight.common.BaseRestIT
import com.montebruni.salesfreight.fixture.resource.rest.createCalculateFreightRequest
import com.montebruni.salesfreight.usecase.CalculateFreight
import com.montebruni.salesfreight.usecase.input.CalculateFreightInput
import com.ninjasquad.springmockk.MockkBean
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [FreightController::class])
class FreightControllerIT : BaseRestIT() {

    @MockkBean
    private lateinit var calculateFreight: CalculateFreight

    @AfterEach
    internal fun tearDown() {
        confirmVerified(calculateFreight)
    }

    private val baseUrl = "/v1/freights"

    @Test
    fun `should return double when creation is successfully`() {
        val request = createCalculateFreightRequest()
        val expectedOutput = 10.0

        val useCaseSlot = slot<CalculateFreightInput>()

        every { calculateFreight.execute(capture(useCaseSlot)) } returns expectedOutput

        mockMvc.perform(
            MockMvcRequestBuilders.post("$baseUrl/simulate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andExpect(MockMvcResultMatchers.jsonPath("freightAmount").value(expectedOutput.toString()))
            .run {
                assertEquals(request.fromCep, useCaseSlot.captured.fromCep)
                assertEquals(request.toCep, useCaseSlot.captured.toCep)
                assertEquals(request.items.size, useCaseSlot.captured.items.size)
            }

        verify { calculateFreight.execute(useCaseSlot.captured) }
    }

    @Test
    fun `should return error 400 when try to find an invalid cep`() {
        val request = createCalculateFreightRequest()
        val useCaseSlot = slot<CalculateFreightInput>()

        every { calculateFreight.execute(capture(useCaseSlot)) } throws IllegalArgumentException()

        mockMvc.perform(
            MockMvcRequestBuilders.post("$baseUrl/simulate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
            .run {
                assertEquals(request.items.size, useCaseSlot.captured.items.size)
            }

        verify { calculateFreight.execute(useCaseSlot.captured) }
    }
}
