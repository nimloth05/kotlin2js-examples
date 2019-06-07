//Deactivated to prevent any additional hiccupss
//buildCache {
//  local {
//    isEnabled = true
//  }
//  local(DirectoryBuildCache::class) {
//    directory = file("./.build-cache")
//    isEnabled = true
//    isPush = true
//    removeUnusedEntriesAfterDays = 2
//  }
//}

pluginManagement {
  repositories {
    gradlePluginPortal() //muss angegeben werden
    maven { setUrl("https://jcenter.bintray.com/") }
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
  }

  resolutionStrategy {
    eachPlugin {
      when (requested.id.id) {
        "kotlin2js" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
        "kotlin-dce-js" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
        "org.jetbrains.kotlin.frontend" -> useModule("org.jetbrains.kotlin:kotlin-frontend-plugin:${requested.version}")
      }
    }
  }
}
rootProject.name = "jugs_kotlinJs"
include(
    "plain-js",
    "binding-demo",
    "rest-example",
    "js-to-kotlin",
    "mpp-example",
    "node-toolchain",
    "gradle-js-toolchain")


