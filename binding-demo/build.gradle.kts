import ch.viseon.Versions
import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
  id("kotlin2js")
  id("org.jetbrains.kotlin.frontend")
}

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  implementation(kotlin("stdlib-js"))
}

tasks {
  "compileKotlin2Js"(Kotlin2JsCompile::class) {
    kotlinOptions {
      moduleKind = "commonjs"
      sourceMap = true
      sourceMapEmbedSources = "always"
    }
  }
}

kotlinFrontend {
  sourceMaps = false

  npm {
    dependency("jquery", Versions.jQuery)
  }

  bundle<WebPackExtension>("webpack") {
    (this as WebPackExtension).apply {
      port = 8080
      bundleName = "main"
      contentPath = file("web")
    }
  }
}


tasks {
  register<Copy>("copyFiles") {
    val to = "$projectDir/web"
    from("${buildDir.path}/bundle/main.bundle.js")
    from("${buildDir.path}/classes/kotlin/main/binding-demo.js")
    into(file(to))
  }
}

afterEvaluate {
  tasks["assemble"].dependsOn("copyFiles")
}

