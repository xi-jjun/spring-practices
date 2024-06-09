# Rails docker 환경 구축
## environment
- rails 5.2
- ruby 2.5.7
- MySQL 8.0.33
<br>

## project
[여기](https://github.com/fastruby/rails_5_2_sample)서 rails 5.2 프로젝트를 가져왔음.<br>
`Gemfile.lock`을 삭제하고 `Gemfile`에서 ruby version을 2.5.7로 수정하면 셋팅 끝.
<br>

## docker files
- `Dockerfile-local` : Rails application
- `docker-compose.yml` : Rails app을 기존에 존재하는 MySQL container와 같은 네트워크를 사용하여 실행하도록 설정
  - `volumes`를 `.:/app`으로 해서 로컬환경에서 코드 수정 시 실행중인 container에 즉시 반영되도록 함. 
- `db-docker-compose.yml` : MySQL 8.0 container. mysql_db_network 를 생성하도록 함.

## 실행방법
```shell
# 참고) MySQL DB는 원래 실행되어 있었다는 가정 하에 이번 환경을 구성했다.
docker-compose -f db-docker-compose.yml up -d
```
<br>

먼저 Dockerfile-local 이미지 빌드를 위해서 아래 명령어 실행
```shell
docker-compose build
```
<br>

이미지 빌드가 끝났다면, 컨테이너 실행
```shell
docker-compose up -d
```

이후 로컬에서 개발을 위해 파일을 수정해도, 컨테이너를 재실행할 필요 없이 바로 컨테이너에 수정사항이 반영된다.

## Debug 실행방법
(찾는 중...)
