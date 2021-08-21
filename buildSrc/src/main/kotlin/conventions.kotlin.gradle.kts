import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("conventions.java")
    kotlin("jvm")
}

// Override spring dependency management
extra["kotlin.version"] = getKotlinPluginVersion()

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all")
        jvmTarget = TARGET_JAVA_VERSION_STRING
    }
}
