import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm {}
    js {
        useCommonJs()
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("io.ktor:ktor-client-core:${property("ktorVersion")}")
                implementation("io.ktor:ktor-client-serialization:${property("ktorVersion")}")
                implementation("io.ktor:ktor-client-logging:${property("ktorVersion")}")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("io.ktor:ktor-client-apache:${property("ktorVersion")}")
                implementation("io.ktor:ktor-client-serialization-jvm:${property("ktorVersion")}")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("io.ktor:ktor-client-js:${property("ktorVersion")}")
                implementation("io.ktor:ktor-client-serialization-js:${property("ktorVersion")}")
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
        kotlinOptions.jvmTarget = "1.8"
    }
    withType<KotlinJsCompile> {
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}
