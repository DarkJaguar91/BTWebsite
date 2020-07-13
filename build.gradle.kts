group = "com.brandontalbot"
version = "1.0.0"

buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4-M2")
    }
}

plugins {
    kotlin("multiplatform") version "1.4-M2" apply false
    kotlin("plugin.serialization") version "1.4-M2" apply false
    kotlin("jvm") version "1.4-M2" apply false
    id("org.jetbrains.kotlin.js") version "1.4-M2" apply false
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

afterEvaluate {
    tasks {
        register<Copy>("dockerDist") {
            group = "docker"
            dependsOn(":server:distTar", ":website:browserDistribution")

            into(File(buildDir, "dist"))
            from(getByPath(":server:distTar").outputs.files)
            from(getByPath(":website:browserDistribution").outputs.files) {
                into("website")
            }
        }
    }
}
