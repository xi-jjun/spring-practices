description = "tutorial-domain module"

plugins {
    id("java-library")  // 현재 domain 모듈을 다른 모듈에서 참조할 수 있도록 라이브러리 모듈로 설정

    /**
     * 아래 plugin 없이 @Service annotation 을 쓰려고 하면, 당연하게도 import 가 안된다.
     * - @Service 는 spring-context dependency 에 존재. 하지만 아래 플러그인으로 관련 의존성을 가져오지 못하면 의미 없음.
     * 아래 plugin 의 역할에 대해서는 root build.gradle.kts 를 확인해라.
     */
    id("org.springframework.boot")
    id("io.spring.dependency-management")

    // @Component 등의 어노테이션이 붙은 클래스에 open keyword 를 자동으로 붙여주기 위해 선언
    // Kotlin 은 기본적으로 불변 객체라 final class 임.
    // 따라서 AOP, Proxy 등을 사용하기 위해서는 반드시 필요한 플러그인.
    // https://engineerinsight.tistory.com/54
    kotlin("plugin.spring")
}

dependencies {
    implementation(kotlin("stdlib")) // 순수 Kotlin 라이브러리 사용
    implementation("org.springframework:spring-context") // @Service ...
    implementation("org.springframework:spring-tx") // @Transactional

    // 테스트용
    testImplementation(kotlin("test"))
}

java {
    withSourcesJar() // 소스 코드 JAR 생성 (다른 모듈에서 사용 가능하도록)
}

tasks.getByName("bootJar") {
    enabled = false
}
