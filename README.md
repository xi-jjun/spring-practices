# Spring 연습장
## 개요
해당 repo는 spring 을 공부하다가 모르는 내용이 생길 경우, 단순 찾기에서 끝나는 것이 아니라 직접 예시 코드를 작성하여 확인해보기 위한 repo입니다.<br>
spring 연습장이라고 되어 있지만, 다른 내용도 포함될 가능성도 있습니다.<br>
<br>

## 연습 목록
- spring-async
  - 2024.05.15 : servlet container의 메인 스레드가 비동기처리에서 요청/응답을 어떻게 처리하는지 확인을 하기 위함.
- spring-tomcat-install
  - tomcat debug를 위해 만들었던 project. WAR로 빌드된다.
- rails-docker : Rails 어플리케이션을 Docker container로 빌드하여 실행/debugging 할 수 있도록 셋팅한 project
- rails-app
  - 2024-07-28 : init project. Rspec 결과를 PR comment에서 보여주기 위해서 만들었던 샘플 어플리케이션
  - 2024-08-04 : Redis 설정 추가. Actions에서 Redis container를 띄워서 같이 테스트할 수 있도록 셋팅
  - 2024-08-05 : 특정 directory에 대한 변경사항이 push되었을 때에만 Rspec actions를 실행하도록 수정

