plugins {
    kotlin("js")
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains:kotlin-react:17.0.1-pre.148-kotlin-1.4.30")
    implementation("org.jetbrains:kotlin-react-dom:17.0.1-pre.148-kotlin-1.4.30")
    implementation("org.jetbrains:kotlin-react-router-dom:5.2.0-pre.148-kotlin-1.4.30")
    implementation("org.jetbrains:kotlin-styled:5.2.1-pre.148-kotlin-1.4.30")
    implementation(project(":djcommon"))

    testImplementation(kotlin("test-js"))
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
}