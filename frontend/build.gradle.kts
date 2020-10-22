plugins {
    id("com.github.node-gradle.node") version "3.0.0-rc2"
}

group = "me.ozavodny"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

//npm_run_build {
//  inputs.files(fileTree("public"))
//  inputs.files(fileTree("src"))
//  inputs.file("package.json")
//  inputs.file("package-lock.json")
//  outputs.dir("build")
//}
//assemble.dependsOn npm_run_build

//dependencies {
//    implementation(project(":shared"))
//}
