description = "tutorial-postgresql module"

/**
 * root build.gradle.kts 에서 subproject 에서 사용할 플러그인 버전을 명시했기에, version 에 대해서는 명시 X
 * 다른 버전을 사용하려고 하면, 빌드 에러 발생.
 */
plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management") // spring boot bom 의존성을 편하게 가져올 수 있도록 해주는 플러그인

    kotlin("plugin.jpa") // no-args 관련 플러그인
    // plugin.spring : @Component, @Async, @Transactional 등의 어노테이션이 붙언 클래스에 open 키워드를 자동으로 붙여줌.
    // 그리고 kotlin의 all-open plugin 도 포함하고 있어서, 추가적으로 open 키워드를 적용할 어노테이션을 allOpen block 으로 명시 가능
    // @see https://kotlinlang.org/docs/all-open-plugin.html#spring-support
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":tutorial-domain")) // domain 모듈을 참고. Entity --> Domain 객체로의 변환을 위해 참조 필요
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    runtimeOnly("org.postgresql:postgresql:42.7.3")

    // test dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
    bootJar { enabled = false } // 외부에서 사용되기만 할 예정이라 bootJar 빌드는 OFF
//    jar { enabled = true } // 이미 root build.gradle.kts 에 선언되어 있음
}

// JPA 관련 annotations 에 open keyword 적용
// 참고) plugin.spring 은 spring 관련된 annotation 만 자동으로 적용해줌
allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}
