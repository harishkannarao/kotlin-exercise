rootProject.name = "kotlin-exercise"

include(
        "application"
)

pluginManagement {
    // variables for gradle.properties
    val kotlinVersion: String by settings
    val shadowJarVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "com.github.johnrengelman.shadow" -> useVersion(shadowJarVersion)
            }
        }
    }
}
