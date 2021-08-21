package love.sola.kira.arch

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.Priority.HIGH
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.priority
import com.tngtech.archunit.library.GeneralCodingRules
import com.tngtech.archunit.library.GeneralCodingRules.USE_JODATIME
import love.sola.kira.KiraApp

@AnalyzeClasses(
    packagesOf = [KiraApp::class],
    importOptions = [ImportOption.DoNotIncludeTests::class]
)
class GeneralArchTest {

    @ArchTest
    fun `check access to standard streams`(classes: JavaClasses) {
        GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS
            .because("proper logging utility should be used instead of accessing standard streams directly")
            .check(classes)
    }

    @ArchTest
    fun `check logging`(classes: JavaClasses) {
        GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING
            .check(classes)
    }

    @ArchTest
    fun `check JodaTime`(classes: JavaClasses) {
        priority(HIGH)
            .noClasses()
            .should(USE_JODATIME)
            .because("modern Java projects use the [java.time] API instead")
            .check(classes)
    }
}
