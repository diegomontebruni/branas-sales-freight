package com.montebruni.salesfreight.common

import org.springframework.context.ConfigurableApplicationContext

interface IntegrationTestConfiguration {
    fun configure(applicationContext: ConfigurableApplicationContext)
}
