package love.sola.kira.arch

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.Priority
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.library.GeneralCodingRules
import love.sola.kira.KiraApp

@AnalyzeClasses(
    packagesOf = [KiraApp::class],
    importOptions = [ImportOption.DoNotIncludeTests::class]
)
class SpringArchTest {

    private val basePackage: String = KiraApp::class.java.packageName

    @ArchTest
    fun `check @Transaction annotation`(classes: JavaClasses) {
        ArchRuleDefinition.priority(Priority.HIGH)
            .noMembers()
            .should()
            .beAnnotatedWith(javax.transaction.Transactional::class.java)
            .because("Spring @Transactional annotation should be used instead of javax.transaction.Transactional")
            .check(classes)
        ArchRuleDefinition.priority(Priority.HIGH)
            .noClasses()
            .should()
            .beAnnotatedWith(javax.transaction.Transactional::class.java)
            .because("Spring @Transactional annotation should be used instead of javax.transaction.Transactional")
            .check(classes)
    }

    @ArchTest
    fun `check isolation of abstraction layers`(classes: JavaClasses) {
        ArchRuleDefinition.priority(Priority.HIGH)
            .noClasses()
            .that()
            .resideInAnyPackage(
                "$basePackage.service..",
                "$basePackage.repository.."
            )
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("$basePackage.web..")
            .because("Services and repositories should not depend on web layer")
            .check(classes)
    }

    @ArchTest
    fun `check field injection`(classes: JavaClasses) {
        GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION
            .check(classes)
    }
}
