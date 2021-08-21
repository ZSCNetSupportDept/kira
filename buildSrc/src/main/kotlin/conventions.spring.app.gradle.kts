import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("conventions.kotlin")
    id("conventions.git")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("kapt")
    kotlin("plugin.spring")
}

springBoot {
    buildInfo()
}

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.withType<BootJar> {
    launchScript()
}
