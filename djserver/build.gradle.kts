import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    application
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

tasks {
    getByName<JavaExec>("run") {
        fun JavaExec.env(name: String) = environment(name, project.parent!!.property(name)!!)
        env("TVDB_API_TOKEN")
        env("TVDB_USER")
        env("TVDB_USER_KEY")
        env("SONARR_PROTOCOL")
        env("SONARR_PORT")
        env("SONARR_TOKEN")
        env("SONARR_HOST")
        environment("PORT", 8083)
        environment("DEBUG", true)
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            useIR = true
            jvmTarget = "11"
        }
    }
}

dependencies {
    implementation(project(":djcommon"))

    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-client-core:${property("ktor_version")}")
    implementation("io.ktor:ktor-server-core:${property("ktor_version")}")
    implementation("io.ktor:ktor-server-netty:${property("ktor_version")}")
    implementation("io.ktor:ktor-serialization:${property("ktor_version")}")
    implementation("ch.qos.logback:logback-classic:${property("logback_version")}")
}
