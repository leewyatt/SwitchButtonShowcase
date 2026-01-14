plugins {
    application
}

group = "com.test"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}

val osName = System.getProperty("os.name").lowercase()
val platform = when {
    osName.contains("win") -> "win"
    osName.contains("mac") -> "mac"
    else -> "linux"
}

val localJfxPath = file("${projectDir}/libs/$platform").absolutePath

repositories {
    mavenCentral()
}

dependencies {
    implementation(fileTree("libs/$platform") { include("*.jar") })
}

application {
    mainClass.set("com.test.SwitchButtonShowcase")
}

tasks.withType<JavaExec> {
    doFirst {
        println("Using Local JFX Path: $localJfxPath")
        println("Using Java Toolchain: ${java.toolchain.languageVersion.get()}")
    }

    jvmArgs(
        "--module-path", localJfxPath,
        "--add-modules", "javafx.controls",

        "-Djava.library.path=$localJfxPath",
        "--enable-native-access=javafx.graphics",
        "-Dprism.verbose=true"
    )
}