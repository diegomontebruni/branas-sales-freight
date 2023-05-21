package com.montebruni.salesfreight.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.Slf4jNotifier
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent
import org.springframework.test.context.DynamicPropertySource

abstract class BaseHTTPClientIT {

//    @Autowired
//    lateinit var wmServer: WireMockServer

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        wmServer.resetRequests()
        wmServer.resetAll()
    }

    companion object {
        @JvmStatic
        val wmServer = WireMockServer(
            WireMockConfiguration()
                .dynamicPort()
                .extensions(ResponseTemplateTransformer(true))
                .notifier(Slf4jNotifier(true))
        ).also { it.start() }

        @JvmStatic
        @DynamicPropertySource
        fun configure(applicationContext: ConfigurableApplicationContext) {
            applicationContext.beanFactory.registerSingleton("wiremock", wmServer)

            applicationContext.addApplicationListener {
                if (it is ContextClosedEvent) {
                    wmServer.stop()
                }
            }

            val httpClientUrls = mapOf(
                "client.branasstorage.product.host" to ""
            ).map { "${it.key}=http://localhost:${wmServer.port()}${it.value}" }

            TestPropertyValues.of(httpClientUrls).applyTo(applicationContext)
        }
    }
}
