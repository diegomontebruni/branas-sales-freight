package com.montebruni.salesfreight.common

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

class IntegrationTestsInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    private val wireMock = CustomWireMock()

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        wireMock.configure(applicationContext)
    }
}
