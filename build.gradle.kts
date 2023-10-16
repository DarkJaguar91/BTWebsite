import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
val ktorVersion: String by project

plugins {
    kotlin("multiplatform") version "1.9.10"
    kotlin("plugin.serialization") version "1.9.10"
    application
}

group = "com.brandontalbot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm("server") {
        jvmToolchain(8)
        withJava()
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }
    js("web") {
        binaries.executable()
        browser {
            commonWebpackConfig(Action {
                cssSupport {
                    enabled.set(true)
                }
             })
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val serverMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                implementation("io.ktor:ktor-server-html-builder-jvm:$ktorVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")

                implementation("io.ktor:ktor-client-java:$ktorVersion")
            }
        }
        val serverTest by getting
        val webMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.2.0-pre.346")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.2.0-pre.346")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.9.3-pre.346")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.3.0-pre.346")

                implementation("io.ktor:ktor-client-js:$ktorVersion")
            }
        }
        val webTest by getting
    }
}

application {
    mainClass.set("com.brandontalbot.application.ServerKt")
}

tasks.named<JavaExec>("run") {
    val webBrowserDistribution = tasks.named<KotlinWebpack>("webBrowserProductionWebpack")
    dependsOn(webBrowserDistribution)

    val serverJarTask = tasks.named<Jar>("serverJar")
    dependsOn(serverJarTask)
    classpath(serverJarTask)


    environment("WEB_DIR", webBrowserDistribution.get().outputDirectory.asFile.get().absolutePath.also { println(it) })
    jvmArgs("-DWEB_DIR=${webBrowserDistribution.get().outputDirectory.asFile.get().absolutePath}")
}