# Spring-async
## 개요
spring servlet container가 요청/응답 시 thread pool에서 스레드를 어떻게 관리하는지 궁금하여 직접 확인하기 위해서 최초 생성하게된 프로젝트입니다.

## 테스트1
- package 위치 : `springasync/rewardevent`
  - 설명 : 유저가 '이벤트 참여'를 할 경우라는 상황을 가정한 코드
  - 이벤트는 총 3가지가 존재하고, 현재 진행중인건 2가지 이벤트이다. (`inProgress` 값에 따른 이벤트 진행중/종료 상태를 알 수 있다.)
- 일시 : 2024.05.15 테스트
- 내용 : 비지니스 로직에 '비동기 처리'가 존재할 경우, servlet container의 요청 스레드와 응답 스레드는 다를까?
- 내가 예상한 것
  1. 동기처리만 존재할 경우, servlet container의 스레드는 요청/응답 시 같을 것이다.
  2. 비동기 처리가 존재할 경우, servlet container의 스레드는 요청/응답 시 다를 것이다. (비동기 처리 중에는 요청 시 스레드가 유후 상태로 다른 처리를 할 것이기 때문)