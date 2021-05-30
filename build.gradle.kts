val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val arrowVersion: String by project
val sqliteVersion: String by project
val exposedVersion: String by project
val hikariVersion: String by project
val liquibaseVersion: String by project

group = "in.francl"
version = "0.0.1"

application {
    mainClass.set("in.francl.bassoon.bootloader.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.arrow-kt:arrow-core:$arrowVersion")
    implementation("io.arrow-kt:arrow-meta:$arrowVersion")
    implementation("io.arrow-kt:arrow-fx-coroutines:$arrowVersion")

    implementation("org.xerial:sqlite-jdbc:$sqliteVersion")
    implementation("com.zaxxer:HikariCP:$hikariVersion")
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")

    liquibaseRuntime("org.liquibase:liquibase-core:$liquibaseVersion")
    liquibaseRuntime("org.xerial:sqlite-jdbc:$sqliteVersion")
    liquibaseRuntime("org.yaml:snakeyaml:1.15")
    liquibaseRuntime("javax.xml.bind:jaxb-api:2.3.1")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-client-gson:$ktorVersion")
    implementation("io.ktor:ktor-client-core:1.6.0")
    implementation("io.ktor:ktor-client-cio:1.6.0")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("io.kotest:kotest-runner-junit5:4.6.0")
    testImplementation("io.kotest:kotest-assertions-core:4.6.0")
    testImplementation("io.kotest:kotest-property:4.6.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

plugins {
    application
    kotlin("jvm") version "1.5.10"
    id("org.liquibase.gradle") version "2.0.4"

}

liquibase {
    activities.register("main") {
        arguments = mapOf(
            "logLevel" to "debug",
            "changeLogFile" to "src/main/resources/db.changelog.yaml",
            "url" to "jdbc:sqlite:file:build/bassoon?cache=shared"
        )
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    get("run").dependsOn("update")
}

task("stage") {
    dependsOn(arrayOf("clean", "update", "installDist"))
}
