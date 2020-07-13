plugins {
    kotlin("jvm")
    application
}

dependencies {
    implementation(project(":common"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.ktor:ktor-server-netty:${property("ktorVersion")}")
    implementation("io.ktor:ktor-html-builder:${property("ktorVersion")}")
    implementation("io.ktor:ktor-serialization:${property("ktorVersion")}")
    implementation("io.ktor:ktor-client-apache:${property("ktorVersion")}")
    implementation("io.ktor:ktor-client-serialization-jvm:${property("ktorVersion")}")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.1")
}

application {
    mainClassName = "com.brandontalbot.server.ServerKt"
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    getByName<JavaExec>("run") {
        environment("WEB_DIR", project(":website").buildDir.absolutePath + "/distributions")
    }
}
