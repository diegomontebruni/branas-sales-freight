package com.montebruni.salesfreight

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SalesFreightApplication

fun main(args: Array<String>) {
	runApplication<SalesFreightApplication>(*args)
}
