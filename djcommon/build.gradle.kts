import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm()
    js(IR) {
        useCommonJs()
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("io.ktor:ktor-client-core:${property("ktor_version")}")
                implementation("io.ktor:ktor-client-serialization:${property("ktor_version")}")
                implementation("io.ktor:ktor-client-logging:${property("ktor_version")}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${property("kotlinx_serialization_version")}")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("io.ktor:ktor-client-apache:${property("ktor_version")}")
                implementation("io.ktor:ktor-client-serialization-jvm:${property("ktor_version")}")
                implementation("org.slf4j:slf4j-api:1.7.30")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("io.ktor:ktor-client-js:${property("ktor_version")}")
                implementation("io.ktor:ktor-client-serialization-js:${property("ktor_version")}")
            }
        }
    }
}

tasks {
    withType<KotlinCompileCommon> {
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
    withType<KotlinCompile> {
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
        kotlinOptions.jvmTarget = "11"
        kotlinOptions.useIR = true
    }
    withType<KotlinJsCompile> {
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}