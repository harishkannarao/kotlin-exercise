import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("java")
	kotlin("jvm").apply(false)

}

// variables for gradle.properties
val javaVersion: String by project
val kotlinVersion: String by project
val coroutinesVersion: String by project
val testNgVersion: String by project
val assertkVersion: String by project
val kotlinMockitoVersion: String by project

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

		testImplementation("org.testng:testng:$testNgVersion")
		testImplementation("com.willowtreeapps.assertk:assertk-jvm:$assertkVersion")
		testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:$kotlinMockitoVersion")
	}

	tasks.withType<Test> {
		useTestNG {}
		val properties = System.getProperties().entries.map { it.key.toString() to it.value }.toMap()
		systemProperties(properties)
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict", "-Xopt-in=kotlin.RequiresOptIn")
			jvmTarget = javaVersion
		}
	}
}