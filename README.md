# Blog Project
Spring Boot Java Project 블로그 검색 서비스

## 개발환경
| Name | Version    | Description |
| --- |------------| --- |
| Java | `17`       |
| Gradle | `7.2`      |
| Spring Boot | `2.7.15` |
| Lombok | 
| H2Database |

## H2 Database console URL
http://localhost:8080/h2-console
- url : jdbc:h2:~/test
- name : sa

## 사용 라이브러리
| Name | Description |
| --- |-------------|
| openfeign | 외부 api 연동   |
| jackson | json 관련 처리  |

## API
| Method      | url | Description      |
|-------------|----|------------------|
| GET         | /search/blog  | 블로그 검색           |
| GET         | /keyword/popular  | 인기 검색어 Top 10 조회 |
| GET         | /keyword/recent  | 최근 검색어 Top 10 조회 |

## Executable jar
https://github.com/yoooon401/blog-project/tree/master/jar
- java -jar blog-project-0.0.1-SNAPSHOT.jar


## Postman
https://github.com/yoooon401/blog-project/tree/master/postman
- Blog Project.postman_collection.json
