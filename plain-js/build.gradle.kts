import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

/*
 * Apply kotlin2js plugin to activate the kotlinJs compiler
 * Apply kotlin-dce-js plugin, to activate DCE
 */
plugins {
  id("kotlin2js")
  id("kotlin-dce-js")
}

dependencies {
  /*
   * Library contains many useful things like Lists, leftPad(), and so forth.
   * It also contains some bindings to w3c APIs (e.g. DOM-API)
   * Its very big (over 2MB), so DCE is advised!
   */
  implementation(kotlin("stdlib-js"))
}

/*
 * Configuration of the compiler task.
 * We set umd because it works with plain javaScript.
 * SourceMap is enabled to show kotlin source code in chrome.
 */
tasks {
  "compileKotlin2Js"(Kotlin2JsCompile::class) {
    kotlinOptions {
      moduleKind = "umd" //"plain", "amd", "commonjs", "umd"
      sourceMap = true
      sourceMapEmbedSources = "always"
    }
  }
}

/*
 * Copies the minified files to the web directory
 */
tasks {
  register<Copy>("copyMinifiedFiles") {
    val to = "$projectDir/web"
    from("${buildDir.path}/kotlin-js-min/main")
    into(file(to))
  }
}

afterEvaluate {
  tasks["build"].dependsOn("runDceKotlinJs")
  tasks["build"].dependsOn("copyMinifiedFiles")
}

repositories {
  mavenCentral()
}
