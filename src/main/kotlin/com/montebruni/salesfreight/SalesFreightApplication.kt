package com.montebruni.salesfreight

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients
@ServletComponentScan
class SalesFreightApplication

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<SalesFreightApplication>(*args)
}
