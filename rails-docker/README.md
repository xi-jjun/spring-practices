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
