plugins {
    id("conventions.spring.library")
}

idea.module.settings.packagePrefix.setKotlin("$group.slf4k")

dependencies {
    api("org.springframework.boot:spring-boot-starter")
    api("org.springframework.boot:spring-boot-starter-logging")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
