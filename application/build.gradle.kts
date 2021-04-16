plugins {
    id("application")
    id("com.github.johnrengelman.shadow")
}

application {
    mainClass.set("com.harishkannarao.kotlin.exercise.DemoApplication")
}

project.setProperty("mainClassName", application.mainClass.get())

tasks {
    named<JavaExec>("run") {
        val properties = System.getProperties().entries.associate { it.key.toString() to it.value }
        systemProperties(properties)
    }
}