/**
 * root build.gradle.kts 에 subprojects 에서 사용할 플러그인을 모두 정의한 상태.
 * 따라서 다른 버전을 쓰려고 하면, 아래와 같은 에러 메세지 발생
 * Caused by: org.gradle.plugin.management.internal.InvalidPluginRequestException: The request for this plugin could not be satisfied because the plugin is already on the classpath with a different version (1.9.25).
 *
 * 덕분에 플러그인을 명시만 하면 되고, 버전은 root 에서 간편하게 관리할 수 있음.
 */
plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")

	// @Component annotation 을 사용할 때, open class 로 자동으로 만들기 위한 플러그인
	kotlin("plugin.spring")
}

dependencies {
	implementation(project(":tutorial-domain"))
	runtimeOnly(project(":infra:db:tutorial-postgresql"))

	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
	bootJar { enabled = true }
	jar { enabled = false }
}
