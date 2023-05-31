package com.montebruni.salesfreight.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired

abstract class BaseHTTPClientIT : IntegrationTests() {

    @Autowired
    lateinit var wmServer: WireMockServer

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        wmServer.resetRequests()
        wmServer.resetAll()
    }
}
