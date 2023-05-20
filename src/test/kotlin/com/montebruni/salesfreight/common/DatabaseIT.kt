package com.montebruni.salesfreight.common

import com.montebruni.salesfreight.configuration.jackson.JacksonObjectMapperConfiguration
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@DataJpaTest
@Suppress("UtilityClassWithPublicConstructor")
@Import(JacksonObjectMapperConfiguration::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DatabaseIT {

    @Autowired
    private lateinit var entityManager: EntityManager

    @BeforeEach
    fun cleanDatabase() {
        val tables = listOf("address_coordinates")
        entityManager
            .createNativeQuery("truncate ${tables.joinToString(separator = ", ") } cascade")
            .executeUpdate()
    }

    companion object {
        @JvmStatic
        val postgresContainer = PostgreSQLContainer(DockerImageName.parse("postgres:12-alpine")).apply {
            withUsername("app_sales")
            withPassword("app_sales")
            withDatabaseName("sales-freight")
        }.also { it.start() }

        @JvmStatic
        @DynamicPropertySource
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgresContainer::getJdbcUrl)
            registry.add("spring.datasource.username", postgresContainer::getUsername)
            registry.add("spring.datasource.password", postgresContainer::getPassword)
        }
    }
}
