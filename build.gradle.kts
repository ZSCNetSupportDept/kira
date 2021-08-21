plugins {
    id("conventions.project")
    id("com.github.ben-manes.versions") version "0.39.0"
}

group = "love.sola.kira"
version = "0.0.1-SNAPSHOT"

subprojects {
    group = rootProject.group
    version = rootProject.version
}
