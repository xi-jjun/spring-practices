rootProject.name = "multi-module-tutorial"

// 아래는 단순 gradle 관련 테스트용
//include(":module-a")
//include(":module-a:module-a-a") // : 을 쓰지 않으면 에러 발생

// web 모듈 추가
include(":tutorial-web")

// domain 모듈 추가 (순수 kotlin 객체)
include(":tutorial-domain")

// infra 모듈 추가
include(":infra:db:tutorial-postgresql")
