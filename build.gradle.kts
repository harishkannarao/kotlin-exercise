import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("java")
	id("org.jetbrains.kotlin.jvm").apply(false)

}

// variables for gradle.properties
val projectVersion: String by project
val javaVersion: String by project
val kotlinVersion: String by project
val coroutinesVersion: String by project
val testNgVersion: String by project
val kluentVersion: String by project
val mockkVersion: String by project

group = "com.harishkannarao.kotlin.exercise"
version = ""
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)

allprojects {

	apply(plugin= "java")
	apply(plugin= "org.jetbrains.kotlin.jvm")

	repositories {
		mavenCentral()
	}

	dependencies {
		implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${coroutinesVersion}")

		testImplementation("org.testng:testng:$testNgVersion")
		testImplementation("io.mockk:mockk:$mockkVersion")
		testImplementation("org.amshove.kluent:kluent:$kluentVersion")
	}

	tasks.withType<Test> {
		useTestNG {}
		val properties = System.getProperties().entries.map { it.key.toString() to it.value }.toMap()
		systemProperties(properties)
	}

	tasks.withType<Jar> {
		archiveVersion.set(projectVersion)
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict", "-Xopt-in=kotlin.RequiresOptIn")
			jvmTarget = javaVersion
		}
	}
}