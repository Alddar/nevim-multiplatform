plugins {
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
    application
    id("com.github.johnrengelman.shadow") version "5.0.0"
}
group = "me.ozavodny"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClassName
            )
        )
    }
}

//sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
//    kotlin.srcDir(file("src/main/myKotlin"))
//}

//shadowJar {
//    manifest {
//        attributes('Main-Class', 'ApplicationKt.main')
//    }
//}

repositories {
    mavenCentral()
    jcenter()
    maven {
        url = uri("https://dl.bintray.com/kotlin/ktor")
    }
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlinx")
    }
}
dependencies {
    implementation("io.ktor:ktor-server-core:1.4.0")
    implementation("io.ktor:ktor-server-netty:1.4.0")
    implementation("io.ktor:ktor-websockets:1.4.0")
    implementation("com.beust:klaxon:5.0.1")

    implementation("org.reduxkotlin:redux-kotlin-threadsafe-jvm:0.5.5")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0-RC2")
    implementation("ch.qos.logback:logback-classic:1.2.3")
}
//tasks.withType<KotlinCompile>() {
//    kotlinOptions.jvmTarget = "1.8"
//}

