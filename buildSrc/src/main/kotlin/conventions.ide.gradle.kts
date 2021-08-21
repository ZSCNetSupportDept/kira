plugins {
    id("org.jetbrains.gradle.plugin.idea-ext")
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
