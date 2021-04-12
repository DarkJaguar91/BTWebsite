import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

group = "com.brandontalbot"
version = "1.0.0"

plugins {
    kotlin("multiplatform") version "1.4.32" apply false
    kotlin("plugin.serialization") version "1.4.32" apply false
    kotlin("jvm") version "1.4.32" apply false
    kotlin("js") version "1.4.32" apply false
    kotlin("android") version "1.4.32" apply false
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://dl.bintray.com/kotlin/ktor")
        maven("https://dl.bintray.com/kotlin/kotlinx")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    }
}

tasks {
    register("runAll") {
        val webpack =
            subprojects.find { it.name == "djweb" }!!.tasks.getByName<KotlinWebpack>("browserDevelopmentWebpack")

        val serverRun = subprojects.find { it.name == "djserver" }!!.tasks.getByName<JavaExec>("run") {
            environment("WEB_DIR", webpack.destinationDirectory)
        }

        finalizedBy(serverRun, webpack)
    }
}