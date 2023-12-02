plugins {
    application
    kotlin("jvm") version "1.9.+"
    id("org.jlleitschuh.gradle.ktlint") version "12.+"
    id("org.sonarqube") version "3.5.+"
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("MainKt")
}

repositories {
    mavenCentral()
}

val exposedVersion: String by project

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.xerial:sqlite-jdbc:3.+")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")

    implementation("org.seleniumhq.selenium:selenium-java:3.+")
    implementation("org.seleniumhq.selenium:selenium-firefox-driver:3.+")
}

tasks.test {
    useJUnitPlatform()
}
