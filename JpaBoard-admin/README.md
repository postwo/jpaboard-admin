# 어드민 서비스 - 패스트캠퍼스, 10개 프로젝트로 완성하는 백엔드 웹개발

[![GitKraken shield](https://img.shields.io/badge/GitKraken-Legendary%20Git%20Tools-teal?style=plastic&logo=gitkraken)](http://gitkraken.link/uno)

게시판 서비스의 관리 도구를 개발해보는 프로젝트입니다. 2022년 6월 기준 가장 최신의 스프링 부트와 관련 기술들, 자바 17 기능들, 개발 도구들을 경험할 수 있도록 만들어졌습니다.

이 서비스는 [패스트캠퍼스](https://fastcampus.co.kr/)의 [10개 프로젝트로 완성하는 백엔드 웹개발(Java/Spring) 초격차 패키지 Online](https://fastcampus.co.kr/dev_online_befinal) 강의의 강의용 프로젝트로 사용되었습니다.

## 개발 환경

* Intellij IDEA Ultimate 2022.2.1 ~ 2022.3.3
* Java 17
* Gradle 7.4.1
* Spring Boot 2.7.0

## 기술 세부 스택

Spring Boot

* Spring Boot Actuator
* Spring Data JPA
* Spring Security OAuth 2 Client
* Spring Security
* Thymeleaf
* Spring Web
* Spring WebSocket
* Lombok
* Spring Boot DevTools
* H2 Database
* MySQL Driver
* Spring Configuration Processor

그 외

* AdminLTE 3.2
* webjars-locator-core
* sockjs-client
* stomp-websocket
* Heroku

## 데모 페이지

비용 절감을 위해 클라우드 서버가 자동 슬립 모드로 설정되어 있습니다. 링크를 클릭하면 서비스가 깨어납니다. 따라서 첫번째 클릭 반응이 15초 이상으로 다소 늦을 수 있습니다.
이후에는 비교적 원활하게 서비스를 구경하실 수 있습니다.

*  https://project-board-admin.herokuapp.com/

## 강의 찾아가기

어드민 서비스는 강의와 공부 목적으로 만들어진 프로젝트입니다.
따라서 강의의 어떤 시점으로든 코드를 찾아가서 쉽게 관찰할 수 있도록 되어 있습니다.
특정 강의 시점의 소스코드를 좀 더 편리하게 확인하고 싶다면 릴리즈 탭을 확인해 보세요.

* https://github.com/djkeh/fastcampus-project-board-admin/releases

## 질문, 건의

프로젝트에 관해 궁금하신 점이나 건의 사항이 있으시다면 아래 항목을 이용해 주세요.

* 질문: https://fastcampus.co.kr/qna/211368/1
* 버그 리포트, 제안 사항: https://github.com/djkeh/fastcampus-project-board-admin/issues

## Reference

* 게시판 서비스: https://github.com/djkeh/fastcampus-project-board


# thymeleaf 에러 발생
Spring Boot 3.0 이상에서 Thymeleaf 3.1 버전이 도입되면서, #request, #session, #response와 같은 기본 제공 객체는 보안과 성능 개선을 위해 기본적으로 비활성화되었습니다. 이로 인해 #request.requestURI와 같은 표현식이 더 이상 기본적으로 작동하지 않습니다.
2024-12-15T14:05:20.912+09:00 DEBUG 12608 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Failed to complete request: org.thymeleaf.exceptions.TemplateProcessingException: Exception evaluating SpringEL expression: "requestURI.startsWith('/management')" (template: "layouts/layout-left-aside" - line 38, col 65)
2024-12-15T14:05:20.914+09:00 ERROR 12608 --- [nio-8081-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.thymeleaf.exceptions.TemplateProcessingException: Exception evaluating SpringEL expression: "requestURI.startsWith('/management')" (template: "layouts/layout-left-aside" - line 38, col 65)] with root cause

https://www.thymeleaf.org/doc/articles/thymeleaf31whatsnew.html
문서 참고하기

# adminLte 정식 사이트(프리미엄은 유료이다)
https://adminlte.io/
1. 다운로드를 클릭[css](src%2Fmain%2Fresources%2Fstatic%2Fcss)
2. https://github.com/ColorlibHQ/AdminLTE/releases 릴리즈 여기서 다운받으면 된다.
3. Assets 에서 Source code(zip)을 다운한다.

# 여기는 내가 개별적으로 알기 위해 작성 
admin 하고 jpaboard는 별개로 봐야한다 그러므로 
kakaooauth 키값(다른아이디 사용)도 다른걸써야하고 데이터베이스도 다른걸써야한다. 
이부분은 꼭 주의하기 

# #12 - JsGrid 플러그인 추가 이거는 static/plugins/jsgrid
그리는 기능만 제공하는 DataTable 말고
데이터의 추가, 수정, 삭제 기능을 제공하는 테이블 플러그인이 필요하다.
그래서 JsGrid 를 새로 선택하여 어드민 회원 관리 페이지에 적용하기로 함

# fontawesome 에는 여러가지 이미지를 가지고 와서 사용할수 있다.
 <link rel="stylesheet" href="/js/plugins/fontawesome-free/css/all.min.css">
templates/layouts/layout-head.html 에있다

# HAL Explorer 문서화 된거 볼려면 jpaboardapllication으로 run 하기 경로는 localhost:8080/api