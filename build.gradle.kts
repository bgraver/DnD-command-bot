plugins {
    application
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
}

group = "com.uwu.dnd"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "com.uwu.dnd.RunnerKt"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("net.dv8tion:JDA:4.2.0_204")
    implementation("org.litote.kmongo:kmongo:4.1.2")
    implementation("org.slf4j:slf4j-simple:1.7.30")
    implementation("com.beust:klaxon:5.4")
  testImplementation("org.testng:testng:7.3.0")
}
