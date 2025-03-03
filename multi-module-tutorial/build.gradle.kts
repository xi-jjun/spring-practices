plugins {
    kotlin("jvm") version "1.9.25"
}

// 현재 모듈 + 모든 하위모듈들에 적용되는 옵션
allprojects {
    group = "me.practice"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }
}

// 모든 하위 모듈들에만 적용되는 옵션
subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
    }

    java.sourceCompatibility = JavaVersion.VERSION_17
    java.targetCompatibility = JavaVersion.VERSION_17

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation(kotlin("test"))
    }

    // spring 플러그인을 root에 선언하지 않아서, bootJar 에 대한 제어는 불가능
    tasks.getByName("jar") {
        enabled = true
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
