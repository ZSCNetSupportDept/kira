plugins {
    id("conventions.spring.app")
    id("com.google.cloud.tools.jib")
}

jib {
    from {
        image = "adoptopenjdk:16"
    }
    to {
        image = project.name
    }
    container {
        appRoot = "/app"
        workingDirectory = appRoot
        ports = listOf("8080")
    }
}
