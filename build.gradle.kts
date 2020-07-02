import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("java")
	kotlin("jvm").apply(false)

}

// variables for gradle.properties
val javaVersion: String by project
val kotlinVersion: String by project
val coroutinesVersion: String by project
val junitVersion: String by project
val hamcrestVersion: String by project
val mockitoVersion: String by project

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

		testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
		testImplementation("org.hamcrest:hamcrest:$hamcrestVersion")
		testImplementation("org.mockito:mockito-core:$mockitoVersion")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
		val properties = System.getProperties().entries.map { it.key.toString() to it.value }.toMap()
		systemProperties(properties)
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = javaVersion
		}
	}
}