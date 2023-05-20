import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.flywaydb.flyway") version "9.8.1"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.7.22"
}

group = "com.montebruni"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

val mockkVersion = "1.13.4"
val kotlinLoggingVersion = "3.0.5"
val springMockkVersion = "3.1.2"
val testContainerVersion = "1.18.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingVersion")
	implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")

	// Database
	implementation("org.flywaydb:flyway-core")
	runtimeOnly("org.postgresql:postgresql")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Testcontainer
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude("org.mockito")
	}
	testImplementation("io.mockk:mockk:${mockkVersion}")
	testImplementation("com.ninja-squad:springmockk:${springMockkVersion}")
}

dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:$testContainerVersion")
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.0")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

flyway {
	url = "jdbc:postgresql://localhost:5432/sales_freight"
	user = "app_sales"
	password = "app_sales"
}
