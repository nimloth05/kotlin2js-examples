import org.jetbrains.kotlin.gradle.dsl.KotlinJsDce
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
  id("kotlin2js")
  id("kotlin-dce-js")
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
      moduleKind = "umd"
      outputFile = "${sourceSets.main.get().output}/kotlinModule.js"
      sourceMap = true
      sourceMapEmbedSources = "always"
    }
  }

  "runDceKotlinJs"(KotlinJsDce::class) {
    //Needs to be the name of the output file
    keep("kotlinModule.helloKotlin")
  }

  val assembleWeb by registering(Copy::class) {
    group = "build"
    description = "Assemble the web application"
    includeEmptyDirs = false
    from("${buildDir.path}/kotlin-js-min/main")
    from(file("$projectDir/web"))
    into("$buildDir/web")
  }
  assemble {
    dependsOn("runDceKotlinJs")
    dependsOn(assembleWeb)
  }
}


//If we don't use DCE, we have to copy the kotlin std-lib js code
// our self to the web directory

//  val unpackKotlinJsStdlib by registering {
//    group = "build"
//    description = "Unpack the Kotlin JavaScript standard library"
//    val outputDir = file("$buildDir/$name")
//    inputs.property("compileClasspath", configurations.compileClasspath.get())
//    outputs.dir(outputDir)
//    doLast {
//      val kotlinStdLibJar = configurations.compileClasspath.get().single {
//        it.name.matches(Regex("kotlin-stdlib-js-.+\\.jar"))
//      }
//      copy {
//        includeEmptyDirs = false
//        from(zipTree(kotlinStdLibJar))
//        into(outputDir)
//        include("**/*.js")
//        exclude("META-INF/**")
//      }
//    }
//  }
//// Attach an additional copy step to the assemble Task
//val assembleWeb by registering(Copy::class) {
//  group = "build"
//  description = "Assemble the web application"
//  includeEmptyDirs = false
//  from(sourceSets.main.get().output) {
//    exclude("**/*.kjsm")
//  }
//  from(file("$projectDir/web"))
//  into("$buildDir/web")
//}
//assemble {
//  dependsOn("runDceKotlinJs")
//  dependsOn(assembleWeb)
//}
