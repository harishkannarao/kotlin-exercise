plugins {
    application
    id("com.github.johnrengelman.shadow")
}

application {
    mainClassName = "com.harishkannarao.kotlin.exercise.DemoApplication"
}

tasks {
    named<JavaExec>("run") {
        val properties = System.getProperties().entries.map { it.key.toString() to it.value }.toMap()
        systemProperties(properties)
    }
}