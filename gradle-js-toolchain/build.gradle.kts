import ch.viseon.Versions
import com.moowork.gradle.node.yarn.YarnTask
import org.jetbrains.kotlin.gradle.dsl.KotlinJsDce
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
  id("kotlin2js")
  id("kotlin-dce-js")
  id("com.moowork.node")
}

dependencies {
  implementation(kotlin("stdlib-js"))
  implementation("org.jetbrains.kotlinx:kotlinx-html-js:${Versions.kotlinxHtmlCurrent}")
  testImplementation(kotlin("test-js"))
}

tasks {
  "compileKotlin2Js"(Kotlin2JsCompile::class) {
    kotlinOptions {
      moduleKind = "umd"
      outputFile = "${sourceSets.main.get().output}/jsToolchain.js"
      sourceMap = true
      sourceMapEmbedSources = "always"
    }
  }
  "compileTestKotlin2Js"(Kotlin2JsCompile::class) {
    kotlinOptions {
      moduleKind = "umd"
    }
  }

  "runDceTestKotlinJs"(KotlinJsDce::class) {
    dceOptions {
      devMode = true
    }
  }

  register<Copy>("populateNodeModules") {
    dependsOn(compileTestKotlin2Js)

    from(compileKotlin2Js)

    configurations.testCompileClasspath.get().forEach {
      from(zipTree(it).matching {
        include("*.js")
      })
    }
    into("./node_modules")
  }

  node {
    download = true
  }

  register<YarnTask>("yarnInstall") {
    args = setOf("install")
  }

  register<YarnTask>("runKarma") {
    dependsOn("yarnInstall")
    args = setOf("test")
  }

  register<YarnTask>("bundle") {
    dependsOn("runDceKotlinJs", "yarnInstall")
    args = setOf("bundle")
  }

  register<YarnTask>("dev-server") {
    dependsOn("runDceKotlinJs", "yarnInstall")
    args = setOf("dev-server")
  }

  //Not working ATM
//  test {
//    dependsOn("runKarma")
//  }

}

repositories {
  mavenCentral()
  jcenter()
}