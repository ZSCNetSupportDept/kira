plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    val ideaExtVersion = "1.0"
    implementation("org.jetbrains.gradle.plugin.idea-ext:org.jetbrains.gradle.plugin.idea-ext.gradle.plugin:$ideaExtVersion")

    val kotlinVersion = "1.5.21"
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-noarg:$kotlinVersion")

    val springBootVersion = "2.5.4"
    implementation("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    val springDependencyManagementVersion = "1.0.11.RELEASE"
    implementation("io.spring.gradle:dependency-management-plugin:$springDependencyManagementVersion")

    val gitPropertiesVersion = "2.3.1"
    implementation("com.gorylenko.gradle-git-properties:gradle-git-properties:$gitPropertiesVersion")

    val jibVersion = "3.1.2"
    implementation("gradle.plugin.com.google.cloud.tools:jib-gradle-plugin:$jibVersion")

    val liquibaseVersion = "2.0.4"
    implementation("org.liquibase:liquibase-gradle-plugin:$liquibaseVersion")
}
