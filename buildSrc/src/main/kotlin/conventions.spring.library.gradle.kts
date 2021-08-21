plugins {
    id("conventions.kotlin")
    id("conventions.spring.bom")
    kotlin("kapt")
    kotlin("plugin.spring")
}

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
}
