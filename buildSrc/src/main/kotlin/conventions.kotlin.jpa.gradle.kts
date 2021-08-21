plugins {
    id("conventions.kotlin")
    kotlin("plugin.jpa")
    kotlin("plugin.spring")
}

allOpen {
    // JPA's lazy-fetch requires classes to be open to properly create proxies
    annotations(
        "javax.persistence.Entity",
        "javax.persistence.MappedSuperclass",
        "javax.persistence.Embeddable"
    )
}
