plugins {
    id("org.jetbrains.kotlin.js")
}

dependencies {
    implementation(project(":common"))
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.1")
    implementation("org.jetbrains:kotlin-react:${property("kotlinReactVersion")}")
    implementation("org.jetbrains:kotlin-react-dom:${property("kotlinReactVersion")}")
    implementation("org.jetbrains:kotlin-react-router-dom:${property("kotlinReactRouterVersion")}")
    implementation("org.jetbrains:kotlin-styled:${property("kotlinStyledVersion")}")
    implementation("io.ktor:ktor-client-js:${property("ktorVersion")}")
    implementation("io.ktor:ktor-client-serialization-js:${property("ktorVersion")}")
    implementation(npm("react", property("reactVersion").toString()))
    implementation(npm("react-dom", property("reactVersion").toString()))
    implementation(npm("react-router-dom", "5.1.2"))
    implementation(npm("styled-components", "5.0.0"))
    implementation(npm("inline-style-prefixer", "5.1.0"))
    implementation(npm("text-encoding", "0.7.0"))
    implementation(npm("abort-controller", "3.0.0"))
}

kotlin {
    js {
        useCommonJs()
        browser {
            dceTask {
                keep("ktor-ktor-io.\$\$importsForInline\$\$.ktor-ktor-io.io.ktor.utils.io")
            }
        }
        binaries.executable()
    }
}
