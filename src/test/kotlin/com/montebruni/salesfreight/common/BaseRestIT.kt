package com.montebruni.salesfreight.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.montebruni.salesfreight.configuration.jackson.JacksonObjectMapperConfiguration
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

@Import(value = [JacksonObjectMapperConfiguration::class])
@ExtendWith(SpringExtension::class)
@Suppress("SpringJavaInjectionPointsAutowiringInspection")
open class BaseRestIT {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper
}
