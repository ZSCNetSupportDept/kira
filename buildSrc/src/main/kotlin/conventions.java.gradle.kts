plugins {
    id("conventions.ide")
    java
}

java {
    sourceCompatibility = TARGET_JAVA_VERSION
    targetCompatibility = TARGET_JAVA_VERSION
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}

repositories {
    mavenLocal()
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
}
