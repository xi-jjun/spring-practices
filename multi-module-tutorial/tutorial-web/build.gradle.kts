plugins {
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

// 실행 확인을 위한 옵션
//tasks.getByName("bootJar") {
//	enabled = true
//}
//
//tasks.getByName("jar") {
//	enabled = false
//}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}
