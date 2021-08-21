plugins {
    id("com.gorylenko.gradle-git-properties")
}

gitProperties {
    extProperty = "gitProps"
}

// Always get git properties for gradle tasks use
tasks.generateGitProperties {
    outputs.upToDateWhen { false }
}
