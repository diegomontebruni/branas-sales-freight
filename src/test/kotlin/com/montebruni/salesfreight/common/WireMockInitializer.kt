package com.montebruni.salesfreight.common

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.Slf4jNotifier
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent

internal class WireMockInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        val wmServer = WireMockServer(
            WireMockConfiguration()
                .dynamicPort()
                .notifier(Slf4jNotifier(true))
        )

        wmServer.start()

        applicationContext.beanFactory.registerSingleton("wmServer", wmServer)

        applicationContext.addApplicationListener { applicationEvent ->
            if (applicationEvent is ContextClosedEvent) {
                wmServer.stop()
            }
        }

        val httpClientUrls = mapOf(
            "client.sales-catalog.product.host" to ""
        ).map { entry -> "${entry.key}=http://localhost:${wmServer.port()}${entry.value}" }

        TestPropertyValues.of(httpClientUrls).applyTo(applicationContext)
    }
}
