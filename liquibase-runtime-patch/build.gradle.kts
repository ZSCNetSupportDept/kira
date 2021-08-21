plugins {
    id("conventions.java")
    id("conventions.spring.bom")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

dependencies {
    shadow("org.liquibase:liquibase-core")
    shadow("org.liquibase.ext:liquibase-hibernate5:4.3.5")
    shadow("org.springframework.boot:spring-boot-starter-data-jpa")
    shadow("javax.xml.bind:jaxb-api")
    shadow("ch.qos.logback:logback-core")
    shadow("ch.qos.logback:logback-classic")
}
