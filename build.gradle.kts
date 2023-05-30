group = "com.example"
version = "0.0.1-SNAPSHOT"

plugins {
    java
    idea
    id("org.springframework.boot") version Config.Plugins.boot_version
    id("io.spring.dependency-management") version Config.Plugins.spring_dependency_management_version
    id("org.javamodularity.moduleplugin") version Config.Plugins.modularity_version
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(project(":module-demo"))
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.hamcrest", module = "hamcrest-core")
        exclude(group = "org.hamcrest", module = "hamcrest-library")
        exclude(group = "com.vaadin.external.google", module = "android-json")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.javamodularity.moduleplugin")

    repositories {
        jcenter()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web:${Config.boot_version}")
    }
}

tasks.test {
    useJUnitPlatform()

    extensions.configure(org.javamodularity.moduleplugin.extensions.TestModuleOptions::class) {
        runOnClasspath = true
    }
}
