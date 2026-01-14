plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "com.test"
version = "1.0-SNAPSHOT"

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

javafx {
    version = "24"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.graphics")
}

application {
    mainClass.set("com.test.SwitchButtonShowcase")
}

tasks.withType<JavaExec> {
    doFirst {
        jvmArgs(
            "--module-path", localJfxPath,
            "--add-modules", "javafx.controls"
        )

        println("Using Local JFX Path: $localJfxPath")
    }
}