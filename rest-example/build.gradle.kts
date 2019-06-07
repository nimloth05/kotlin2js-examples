import ch.viseon.Versions
import org.jetbrains.kotlin.gradle.dsl.KotlinJsDce
import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
  id("kotlin2js")
  id("kotlin-dce-js")
  id("org.jetbrains.kotlin.frontend")
}

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  implementation(kotlin("stdlib-js"))
  implementation("org.jetbrains.kotlinx:kotlinx-html-js:${Versions.kotlinxHtml}")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.kotlinCoroutine}")
  testImplementation(kotlin("test-js"))
}

tasks {
  "compileKotlin2Js"(Kotlin2JsCompile::class) {
    kotlinOptions {
      moduleKind = "commonjs"
      sourceMap = true
      sourceMapEmbedSources = "always"
    }
  }
  "compileTestKotlin2Js"(Kotlin2JsCompile::class) {
    kotlinOptions {
      moduleKind = "commonjs"
      sourceMap = true
      sourceMapEmbedSources = "always"
    }
  }

  "runDceTestKotlinJs"(KotlinJsDce::class) {
    dceOptions {
      devMode = true
    }
  }
}

kotlinFrontend {
  sourceMaps = false

  npm {
    dependency("jquery", Versions.jQuery)
    dependency("kotlinx-html-js", Versions.kotlinxHtml)
    dependency("kotlinx-coroutines-core", Versions.kotlinCoroutine)
    devDependency("karma")
  }

  bundle<WebPackExtension>("webpack") {
    (this as WebPackExtension).apply {
      port = 8080
      bundleName = "main"
      contentPath = file("web")
      mode = "production"
    }
  }
}


tasks {
  register<Copy>("copyBundle") {
    from("${buildDir.path}/bundle/main.bundle.js")
    into(file("$projectDir/web"))
  }
}
afterEvaluate {
  tasks["build"].dependsOn("copyBundle")
}

