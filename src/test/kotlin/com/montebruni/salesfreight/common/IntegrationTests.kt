package com.montebruni.salesfreight.common

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = [IntegrationTestsInitializer::class])
abstract class IntegrationTests
