import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
  kotlin("multiplatform")
  id("org.jetbrains.kotlin.frontend")
}


repositories {
  mavenCentral()
  jcenter()
}

kotlin {
  targets {
    jvm()
    js("js") {
      configure(listOf(compilations["main"], compilations["test"])) {
        (tasks.getByName(compileKotlinTaskName) as Kotlin2JsCompile).kotlinOptions {
          sourceMap = true
          moduleKind = "commonjs"
          metaInfo = true
        }
      }

      configure(listOf(compilations["main"])) {
        (tasks.getByName(compileKotlinTaskName) as Kotlin2JsCompile).kotlinOptions {
          main = "call"
        }
      }
    }
  }

  sourceSets {
    sourceSets {
      val commonMain by getting {
        dependencies {
          implementation(kotlin("stdlib-common"))
        }
      }
      val commonTest by getting {
        dependencies {
          implementation(kotlin("test-common"))
          implementation(kotlin("test-annotations-common"))
        }
      }

      // Default source set for JVM-specific sources and dependencies:
      jvm().compilations["main"].defaultSourceSet {
        dependencies {
          implementation(kotlin("stdlib-jdk8"))
        }
      }
      // JVM-specific tests and their dependencies:
      jvm().compilations["test"].defaultSourceSet {
        dependencies {
          implementation(kotlin("test-junit"))
        }
      }

      js().compilations["main"].defaultSourceSet { /* ... */ }
      js().compilations["test"].defaultSourceSet {
        dependencies {
          implementation(kotlin("test-js"))
        }
      }
    }
  }
}

kotlinFrontend {
  npm {
    devDependency("karma")
  }

  sourceMaps = true

  bundle<WebPackExtension>("webpack") {
    (this as WebPackExtension).apply {
      port = 8080
      bundleName = "main"
      contentPath = file("web")
    }
  }
}

