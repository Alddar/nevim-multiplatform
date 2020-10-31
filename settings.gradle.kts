
rootProject.name = "multiplatform"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        // add mavenLocal() if you are using a locally built version of the plugin
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.google.cloud.tools.appengine")) {
                useModule("com.google.cloud.tools:appengine-gradle-plugin:${requested.version}")
            }
//            else if (requested.id.id.startsWith("com.github.johnrengelman.shadow")) {
//                useModule("com.github.jengelman.gradle.plugins:shadow:${requested.version}")
//            }
        }
    }
}

include(":backend")
include(":frontend")

