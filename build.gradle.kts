@file:Suppress("SpellCheckingInspection")

import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.abs

plugins {
    id("java")
    id("io.freefair.lombok").version("6.4.2")
}

group = "org.example"
version = "1.0-SNAPSHOT"
description = "Spring Cucumber Testing Framework Skeleton"

val assertjVersion = "3.24.0"
val seleniumVersion = "4.8.0"
val junitVersion = "5.10.0"
val cucumberVersion = "7.13.0"
val slf4jVersion = "2.0.7"
val javaxVersion = "1.3.2"
val springVersion = "6.0.11"
val springBootVersion = "3.1.2"

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

// Utility functions

fun beautifyMap(name: String, map: Map<*, *>): String? {

    val boldStart = ""
    val boldEnd = ""

    val longestK = AtomicInteger()
    val longestV = AtomicInteger()
    map.keys.forEach { e ->
        if (e.toString().length > longestK.get()) {
            longestK.set(e.toString().length)
        }
    }
    map.values.forEach { e ->
        if (e.toString().length > longestV.get()) {
            longestV.set(e.toString().length)
        }
    }

    val fullSize = (if (name.length > longestK.get() + longestV.get()) name.length else longestK.get() + longestV.get()) + 7

    val sb: StringBuilder = StringBuilder()

    sb.append("+").append("-".repeat(fullSize - 2)).append("+").append("\n")
    val spaceSize = fullSize - name.length
    val nameF: String = " ".repeat(spaceSize / 2) + name + " ".repeat(spaceSize / 2)
    sb.append("|").append(nameF.dropLast(abs((nameF.length + 2) - fullSize))).append("|").append("\n")
    sb.append("+").append("-".repeat(fullSize - 2)).append("+").append("\n")
    map.forEach { (k, v) ->
        val nK = "$boldStart${k.toString()}$boldEnd" + " ".repeat(longestK.get() - k.toString().length)
        val nV = v.toString() + " ".repeat(longestV.get() - v.toString().length)
        sb.append("| ").append(nK).append(" | ").append(nV).append(" |").append("\n")
    }
    sb.append("+").append("-".repeat(fullSize - 2)).append("+")
    return sb.toString()
}

dependencies {
    implementation("org.springframework.boot:spring-boot:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-parent:$springBootVersion")

    implementation("org.springframework:spring-tx:$springVersion")
    implementation("org.springframework:spring-aspects:$springVersion")
    implementation("org.springframework:spring-orm:$springVersion")
    implementation("io.cucumber:cucumber-spring:$cucumberVersion")
    implementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")

    testImplementation("org.slf4j:slf4j-simple:2.0.6")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("io.cucumber:cucumber-java:$cucumberVersion")
    testImplementation("io.cucumber:cucumber-junit:$cucumberVersion")
    testImplementation("org.projectlombok:lombok:1.18.26")
    implementation("javax.annotation:javax.annotation-api:$javaxVersion")

    compileOnly("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")
}

tasks {
    test {
        systemProperties(
                System.getProperties().filter {
                    it.key.toString().startsWith("test.") ||
                            it.key.toString().startsWith("cucumber.") ||
                            it.key.toString().startsWith("spring.") ||
                            it.key.toString().startsWith("app.") ||
                            it.key.toString().startsWith("appium.") ||
                            it.key.toString().startsWith("backend") ||
                            it.key.toString().startsWith("browserstack.") ||
                            it.key.toString().startsWith("driver.") ||
                            it.key.toString().startsWith("device.") ||
                            it.key.toString().startsWith("api.") ||
                            it.key.toString().startsWith("framework.")
                }.mapKeys { it.key as String }
        )

        testLogging {
            showStandardStreams = true
            events("passed", "skipped", "failed", "standardOut", "standardError")
        }

        val tests = mutableMapOf<String, String>()

        // Save the failed tests
        afterTest(KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
            val statusMark = if (result.resultType == TestResult.ResultType.FAILURE) "❌" else "✅"
            tests.put("$statusMark ${desc.className} :: ${desc.name}", result.toString())
        }))

        // Print Test summary.
        afterSuite(KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
            if (desc.parent == null) {
                val testSummary = mutableMapOf<String, String>()
                testSummary["Overall Status"] = "${result.resultType}"
                testSummary["Total Tests"] = "${result.successfulTestCount}"
                testSummary["Failed Tests"] = "${result.failedTestCount}"
                testSummary["Skipped Tests"] = "${result.skippedTestCount}"
                println("\n")
                println(beautifyMap("Test Summary", testSummary))
                println("\n")
                println(beautifyMap("Detailed Test Report", tests))
            }
        }))
    }
}