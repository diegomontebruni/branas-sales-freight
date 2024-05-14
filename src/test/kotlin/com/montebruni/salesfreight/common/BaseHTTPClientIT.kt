package com.montebruni.salesfreight.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.montebruni.salesfreight.configuration.jackson.JacksonObjectMapperConfiguration
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(
    classes = [
        JacksonObjectMapperConfiguration::class,
        HttpMessageConvertersAutoConfiguration::class,
        FeignAutoConfiguration::class
    ]
)
@EnableFeignClients(basePackages = ["com.montebruni.salesfreight"])
@ActiveProfiles("httpclient")
@ContextConfiguration(initializers = [WireMockInitializer::class])
open class BaseHTTPClientIT {

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    lateinit var wmServer: WireMockServer

    @Autowired
    lateinit var mapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        wmServer.resetAll()
    }
}
