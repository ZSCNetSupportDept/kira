import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
import org.liquibase.gradle.LiquibaseTask
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

extra["liquibaseTaskPrefix"] = "liquibase"

plugins {
    id("conventions.kotlin")
    id("conventions.git")
    id("org.liquibase.gradle")
}

configurations {
    liquibaseRuntime {
        extendsFrom(configurations.runtimeClasspath.get())

        attributes {
            attribute(KotlinPlatformType.attribute, KotlinPlatformType.jvm)
        }
    }
}

// Please note that users can customize the following default settings
// by creating a liquibase.properties file at $projectDir.
// Don not change or commit your personal settings into the repository.
liquibase {
    activities {
        val changelogDir = "${sourceSets.main.get().resources.sourceDirectories.asPath}/config/liquibase/changelog"
        val changelogFile = "${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))}_changelog.xml"

        val defaults = mapOf(
            "logLevel" to "info",
            "url" to "jdbc:mysql://localhost/kira?useLegacyDatetimeCode=false&serverTimezone=UTC",
            "username" to "root"
        )

        // provide a way to override/customize properties via liquibase.properties file at projectDir.
        val userOverride = Properties()
        File(projectDir, "liquibase.properties").takeIf(File::exists)?.inputStream()?.use(userOverride::load)

        register("main") {
            val args = hashMapOf<Any, Any?>()
            args.putAll(defaults)
            args["changeLogFile"] = "$changelogDir/master.xml"
            args.putAll(userOverride)
            arguments = args
        }

        register("diffLog") {
            val args = hashMapOf<Any, Any?>()
            args.putAll(defaults)
            args["changeLogFile"] = "$changelogDir/$changelogFile"
            args["referenceUrl"] = "hibernate:spring:love.sola.kira.domain" +
                    "?dialect=org.hibernate.dialect.MySQL8Dialect" +
                    "&hibernate.id.new_generator_mappings=true" +
                    "&hibernate.physical_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy" +
                    "&hibernate.implicit_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy"
            args.putAll(userOverride)
            arguments = args
        }
    }

    runList = "main"
}

dependencies {
    liquibaseRuntime(project(":liquibase-runtime-patch", configuration = "shadow"))
    liquibaseRuntime(sourceSets.main.get().output)
}

// use the diffLog activity when executing diff tasks
listOf(
    tasks.named("liquibaseDiff"),
    tasks.named("liquibaseDiffChangeLog")
).forEach { task ->
    task {
        dependsOn(tasks.generateGitProperties, tasks.classes)
        doFirst {
            liquibase.runList = "diffLog"
            @Suppress("UNCHECKED_CAST")
            val args = liquibase.activities.named("diffLog").get().arguments!! as MutableMap<String, String?>
            args["changeLogFile"]?.let(Paths::get)?.let(Files::deleteIfExists)
            if ("changeSetAuthor" !in args) {
                val gitProps: Map<String, String?> by ext
                args["changeSetAuthor"] = gitProps["git.build.user.name"]
            }
        }
    }
}

// build the patched runtime first before running liquibase tasks
tasks.withType<LiquibaseTask> {
    dependsOn(project(":liquibase-runtime-patch").tasks.named("shadowJar"))
}
