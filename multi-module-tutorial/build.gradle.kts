import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * plugin 에 apply false 를 하는 이유
 * - root build.gradle.kts 에서 플러그인을 선언
 * - 이후 subprojects 에서는 version 없이 플러그인만 명시하여 사용
 * - 따라서 일관된 버전을 root 에서만 관리할 수 있음.
 */
plugins {
    kotlin("jvm") version "1.9.25"
    // kotlin("kapt") --> 테스트용 프로젝트라 QueryDSL 을 쓰지 않고 진행하려고 해서 적용하지 않음

    // 해당 플러그인 자체만으로는 크게 바뀌는건 없음. 다른 플러그인과 같이 사용될 때 이를 감지하여 그에 따라 반응함.
    // @see https://docs.spring.io/spring-boot/gradle-plugin/reacting.html
    // - dependency-management 와 같이 사용되면, spring-boot-dependencies BOM 을 자동으로 import 한다.
    id("org.springframework.boot") version "3.4.3" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false

    // - `@Entity`, `@MappedSuperclass`, `@Embeddable` annotation 이 명시된 클래스에 no-arg constructor 를 자동으로 생성해주는 기능
    //  - @see https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support
    // - id("org.jetbrains.kotlin.plugin.jpa") version xxxx 와 동일한 내용
    kotlin("plugin.jpa") version "1.9.25" apply false // no-args 를 wrapping 하는 플러그인
    kotlin("plugin.spring") version "1.9.25" apply false // all-open 을 wrapping 하는 플러그인
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
    // plugin(apply = "<plugin id>") --> legacy (권장되지 않는 방법)
    plugins.apply("org.jetbrains.kotlin.jvm") // gradle docs 기준으로 권장되는 형식

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation(kotlin("test"))
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            // spring은 Java로 만들어져서, Kotlin 의 null-safety 를 활용하기 위해서는
            // Spring framework 의 특정 기능에 대해서 더욱 엄격하게 확인이 필요하다.
            // 이를 위한 옵션이 바로 아래에 명시되어 있는 것.
            // JSR 305: Annotations for Software Defect Detection
            // @see https://jcp.org/en/jsr/detail?id=305
            freeCompilerArgs += "-Xjsr305=strict"
        }
    }

    // spring 플러그인을 root에 선언하지 않아서, bootJar 에 대한 제어는 불가능
    tasks.getByName("jar") {
        enabled = true
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
