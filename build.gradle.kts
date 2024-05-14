import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.flywaydb.flyway") version "9.16.3"
	id("io.gitlab.arturbosch.detekt") version "1.23.4"

	kotlin("jvm") version "1.9.21"
	kotlin("plugin.spring") version "1.9.21"
	kotlin("plugin.jpa") version "1.9.21"
}

group = "com.montebruni"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

extra["testContainerVersion"] = "1.19.3"
extra["springCloudVersion"] = "2023.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Feign client
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("io.github.openfeign:feign-okhttp")

	// Database
	implementation("org.flywaydb:flyway-core")
	runtimeOnly("org.postgresql:postgresql")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Testcontainer
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.13.8")
	testImplementation("com.ninja-squad:springmockk:4.0.2")
	testImplementation("com.github.tomakehurst:wiremock-jre8-standalone:3.0.1")

	detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.4")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:${property("testContainerVersion")}")
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

detekt {
	baseline = file("$projectDir/detekt/baseline.xml")
	config.setFrom("$projectDir/detekt/detekt.yml")
	buildUponDefaultConfig = true
	autoCorrect = true
}

tasks.detekt {
	reports {
		xml.required.set(false)
		html.required.set(false)
		txt.required.set(false)
		sarif.required.set(false)
	}

	include("**/*.kt", "**/*.kts")
	exclude("**/resources/**", "**/build/**")
}

tasks.detektBaseline {
	include("**/*.kt", "**/*.kts")
	exclude("**/resources/**", "**/build/**")
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
	url = "jdbc:postgresql://localhost:5432/sales-freight"
	user = "app_sales"
	password = "app_sales"
}
