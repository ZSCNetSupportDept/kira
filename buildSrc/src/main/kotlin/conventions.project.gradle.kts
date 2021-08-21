import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.jetbrains.gradle.ext.ActionDelegationConfig
import org.jetbrains.gradle.ext.EncodingConfiguration.BomPolicy

plugins {
    id("conventions.ide")
}

idea {
    project {
        languageLevel = IdeaLanguageLevel(TARGET_JAVA_VERSION)
        settings {
            delegateActions {
                delegateBuildRunToGradle = true
                testRunner = ActionDelegationConfig.TestRunner.GRADLE
            }
            encodings {
                encoding = Charsets.UTF_8.name()
                bomPolicy = BomPolicy.WITH_NO_BOM
                properties {
                    encoding = Charsets.ISO_8859_1.name()
                    transparentNativeToAsciiConversion = true
                }
            }
        }
    }
}
