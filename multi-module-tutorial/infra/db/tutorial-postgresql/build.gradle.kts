description = "tutorial-postgresql module"

/**
 * root build.gradle.kts 에서 subproject 에서 사용할 플러그인 버전을 명시했기에, version 에 대해서는 명시 X
 * 다른 버전을 사용하려고 하면, 빌드 에러 발생.
 */
plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management") // spring boot bom 의존성을 편하게 가져올 수 있도록 해주는 플러그인

    kotlin("plugin.jpa")
}

dependencies {
    implementation(project(":tutorial-domain")) // domain 모듈을 참고. Entity --> Domain 객체로의 변환을 위해 참조 필요
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.3")
}

tasks {
    bootJar { enabled = false } // 외부에서 사용되기만 할 예정이라 bootJar 빌드는 OFF
//    jar { enabled = true } // 이미 root build.gradle.kts 에 선언되어 있음
}
