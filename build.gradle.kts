// alldeps plugin
plugins {
    kotlin("jvm") version libs.versions.kotlin

    alias(libs.plugins.plugin.publish)
}

assert(JavaVersion.current().isCompatibleWith(JavaVersion.VERSION_17))

group = "com.sinch.gradle"
version = "0.1.0"

repositories {
    mavenCentral()

    gradlePluginPortal()
}

dependencies {
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
}

val gitRepoUrl = "https://github.com/sinch/gradle-alldeps"

@Suppress("UnstableApiUsage")
gradlePlugin {
    website = gitRepoUrl
    vcsUrl = "$gitRepoUrl.git"

    plugins {
        create("alldepsPlugin") {
            id = "$group.${rootProject.name}"
            displayName = "Gradle All Subproject Dependencies in `dependencies`"
            description =
                "A plugin adding printing all subproject dependencies to root project's `dependencies` task."
            tags = listOf("dependencies")
            implementationClass = "$id.AlldepsPlugin"
        }
    }
}
