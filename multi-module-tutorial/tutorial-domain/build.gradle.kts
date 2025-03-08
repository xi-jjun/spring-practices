description = "tutorial-domain module"

plugins {
    id("java-library")  // 다른 모듈에서 참조할 수 있도록 라이브러리 모듈로 설정
}

dependencies {
    implementation(kotlin("stdlib")) // 순수 Kotlin 라이브러리 사용

    // 테스트용
    testImplementation(kotlin("test"))
}

java {
    withSourcesJar() // 소스 코드 JAR 생성 (다른 모듈에서 사용 가능하도록)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17" // JDK 17 사용
    }
}
