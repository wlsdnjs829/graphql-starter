import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.resolve.sam.recreateTypeParametersAndReturnMapping

plugins {
    id("org.springframework.boot") version "2.6.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    id("com.netflix.dgs.codegen") version "5.6.0"
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.jpa") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
}

group = "jinwon"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter:5.4.3")
    implementation("com.netflix.graphql.dgs.codegen:graphql-dgs-codegen-gradle:5.6.2")
    implementation("com.graphql-java:graphql-java:19.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-web")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.graphql:spring-graphql-test")
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
    generateClient = true
    packageName = "jinwon.graphql.starter"
    typeMapping = mutableMapOf(
        "Timestamp" to "kotlin.Long",
    )
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
