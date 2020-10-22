plugins {
    kotlin("multiplatform") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
    id("com.github.node-gradle.node") version "3.0.0-rc2"
}
group = "me.ozavodny"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}
kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js(IR) {
        browser {
            binaries.executable()
        }
    }
    
    sourceSets {
        all {
            languageSettings.useExperimentalAnnotation("kotlin.js.ExperimentalJsExport")
        }

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0-RC2")
            }
        }
//        val jvmMain by getting
        val jsMain by getting {
            dependencies {
            }
        }
    }
}

tasks.register<Copy>("copyTypes") {
    println(buildDir)
    from(file("$rootDir/build/js/packages/multiplatform-shared/kotlin/multiplatform-shared.d.ts"))
    into(file("$rootDir/frontend/src/app/types"))
}

tasks.build {
    dependsOn("copyTypes")
}
