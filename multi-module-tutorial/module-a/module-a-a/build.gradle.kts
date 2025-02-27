// root dir의 settings.gradle.kts에서 현재 모듈을 포함시키기 위해서는 아래와 같이 선언해줘야 함
// include(":module-a:module-a-a")

println("Here is module-a-a build.gradle.tks")
/**
 * project 객체는 각 build.gradle.kts 마다 생성됨
 * @see https://docs.gradle.org/current/dsl/org.gradle.api.Project.html project 설명
 */
println(project.name)
