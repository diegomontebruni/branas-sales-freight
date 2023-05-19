package com.montebruni.salesfreight.configuration.jackson

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class JacksonObjectMapperConfiguration {

    @Bean
    fun defaultObjectMapper(): ObjectMapper = jacksonObjectMapper()
        .setPropertyNamingStrategy(SNAKE_CASE)
        .setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
        .registerModule(JavaTimeModule())
        .disable(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .enable(SerializationFeature.INDENT_OUTPUT)
        .setTimeZone(TimeZone.getTimeZone("UTC"))
}
