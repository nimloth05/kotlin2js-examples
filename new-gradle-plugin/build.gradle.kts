import ch.viseon.Versions
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
  id("org.jetbrains.kotlin.js")
}

kotlin {
  target {
    browser {
      configure(listOf(compilations["main"], compilations["test"])) {
        (tasks.getByName(compileKotlinTaskName) as Kotlin2JsCompile).kotlinOptions {
          sourceMap = true
          moduleKind = "umd"
          metaInfo = true
        }
      }
      webpackTask {
      }
      testTask {
        useKarma {
          usePhantomJS()
        }
      }
    }
  }

  sourceSets["main"].dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:${Versions.kotlinxHtmlCurrent}")
  }

  sourceSets["test"].dependencies {
    implementation(kotlin("test-js"))
  }

}

repositories {
  mavenCentral()
  jcenter()
}
