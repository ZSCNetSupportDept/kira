import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.the
import org.gradle.plugins.ide.idea.model.IdeaModule
import org.gradle.plugins.ide.idea.model.IdeaProject
import org.jetbrains.gradle.ext.*

fun IdeaProject.settings(configure: ProjectSettings.() -> Unit) =
    (this as ExtensionAware).configure(configure)

fun ProjectSettings.delegateActions(configure: ActionDelegationConfig.() -> Unit) =
    (this as ExtensionAware).configure(configure)

fun ProjectSettings.encodings(configure: EncodingConfiguration.() -> Unit) =
    (this as ExtensionAware).configure(configure)

val IdeaModule.settings: ModuleSettings
    get() = (this as ExtensionAware).the()

fun IdeaModule.settings(configure: ModuleSettings.() -> Unit) =
    (this as ExtensionAware).configure(configure)

val ModuleSettings.packagePrefix: PackagePrefixContainer
    get() = (this as ExtensionAware).the()

fun PackagePrefixContainer.setKotlin(prefix: String) {
    this["src/main/kotlin"] = prefix
    this["src/test/kotlin"] = prefix
}
