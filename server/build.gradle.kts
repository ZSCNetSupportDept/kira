plugins {
    id("conventions.spring.app")
    id("conventions.spring.liquibase")
    id("conventions.kotlin.jpa")
    id("conventions.spring.jib")
}

idea.module.settings.packagePrefix.setKotlin("$group")

springBoot {
    mainClass.set("love.sola.kira.KiraApp")
}

dependencies {
    implementation(project(":slf4k"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.zalando:problem-spring-web:$PROBLEM_VERSION")

    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-oauth2-resource-server")
    implementation("org.springframework.security:spring-security-data")

    implementation("org.springdoc:springdoc-openapi-ui:$SPRING_DOC_VERSION")
    implementation("org.springdoc:springdoc-openapi-webmvc-core:$SPRING_DOC_VERSION")
    implementation("org.springdoc:springdoc-openapi-data-rest:$SPRING_DOC_VERSION")
    implementation("org.springdoc:springdoc-openapi-security:$SPRING_DOC_VERSION")
    implementation("org.springdoc:springdoc-openapi-kotlin:$SPRING_DOC_VERSION")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5")
    kapt("org.hibernate:hibernate-jpamodelgen")
    runtimeOnly("mysql:mysql-connector-java")

    implementation("org.liquibase:liquibase-core")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("com.tngtech.archunit:archunit-junit5:$ARCH_UNIT_VERSION")
    testImplementation("com.h2database:h2")
}
