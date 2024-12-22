plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "me.practice"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    reports {
        junitXml.required.set(true) // XML 리포트 활성화
        junitXml.outputLocation.set(file("$buildDir/test-results/cases"))
    }

    // 테스트 케이스별로 하나의 파일이 만들어 짐.
    // 따라서 하나의 파일로 모으기 위한 task를 추가함.
    finalizedBy("mergeTestResults")
}

tasks.register("mergeTestResults") {
    doLast {
        val resultDir = file("$buildDir/test-results/cases")
        val mergedFile = file("$buildDir/test-results/merged-test-results.xml")

        val testFiles = resultDir.listFiles { file -> file.name.endsWith(".xml") } ?: return@doLast
        mergedFile.writeText("<testsuites>\n")

        testFiles.forEach { file ->
            var content = file.readText()
            content = content.replaceFirst("""<\?xml.*\?>""".toRegex(), "") // XML 선언 제거
            content = content.replaceFirst("<testsuites>", "").replaceFirst("</testsuites>", "") // testsuites 태그 제거
            mergedFile.appendText(content)
        }

        mergedFile.appendText("</testsuites>")
        println("Merged test results saved to: $mergedFile")
    }
}
