plugins {
    kotlin("js") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
    id("com.github.node-gradle.node") version "3.0.0-rc2"
}
group = "me.ozavodny"
version = "1.0-SNAPSHOT"

val tailwindCss = tasks.register<com.github.gradle.node.npm.task.NpxTask>("tailwindcss") {

    // Output CSS location
    val generatedFile = "build/processedResources/js/main/tailwind-generated.css"

    // Location of the tailwind config file
    val tailwindConfig = "css/main.pcss"

    command.set("tailwind")
    args.set(listOf("build", tailwindConfig, "-o", generatedFile))

    dependsOn(tasks.npmInstall)

    // The location of the source files which Tailwind scans when running ```purgecss```
    inputs.dir("src/main/kotlin")

    inputs.file(tailwindConfig)
    outputs.file(generatedFile)
}

repositories {
    mavenCentral()
    jcenter()
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlinx")
    }
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    }
}
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable-js:0.3.3")
    implementation(project(":shared"))

    implementation("org.jetbrains:kotlin-react:16.13.1-pre.110-kotlin-1.4.0")
    implementation("org.jetbrains:kotlin-react-dom:16.13.1-pre.110-kotlin-1.4.0")
    implementation("org.jetbrains:kotlin-react-router-dom:5.1.2-pre.114-kotlin-1.4.0")

    implementation("org.reduxkotlin:redux-kotlin-threadsafe-js:0.5.5")
//    implementation(npm("react", "16.13.1"))
//    implementation(npm("react-dom", "16.13.1"))

    implementation(npm("postcss", "latest"))
    implementation(npm("postcss-loader", "latest"))
    implementation(npm("tailwindcss", "1.8.10"))
}
kotlin {
    js {
        browser {
            binaries.executable()
            webpackTask {
                dependsOn(tailwindCss)
            }
            runTask {
                dependsOn(tailwindCss)
            }
        }
    }
}

