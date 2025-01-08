# test 클래스 생성
ctrl + shift + T

# test 메서드 생성
alt + insert

# test 메서드 명 이렇게 설정하기
@Test
void given_when_then() {
//given

    //when

    //then
}


# h2 데이터베이스 접속 경로
http://localhost:8080/h2-console


# yml 설정
hibernate:
show_sql: true # 실행되는 쿼리 콘솔 출력
format_sql: true # 콘솔창에 출력되는 쿼리를 가동성이 좋게 포맷팅


# Jpa 설정
none: 사용하지 않음
create: 기존 테이블 삭제 후 테이블 생성
create-drop: 기존 테이블 삭제 후 테이블 생성, 종료 시점에 테이블 삭제
update: 변경된 스키마 적용
validate: 엔티티와 테이블 정상 매핑 확인


# Test 코드 yml 데이터베이스 따로 적용 (테스트코드에 밑의 내용 붙이기)
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})


# 더미 데이터 생성 사이트
mockaroo.com


# 이 라이브러리는 테스트등 편하게 보기 위해 사용
# 접속 방법
localhost:[포트 번호]/api
implementation 'org.springframework.data:spring-data-rest-hal-explorer'


# 테스트 코드 작성시 import가 안될시
1. MockMvcRequestBuilders,MockMvcResultMatchers 이러한 import가 뜰경우 밑 방식을 사용
2. ctrl + space
3. alt + enter
4. 위 순서대로 한후 improt를 해주면 된다

# 테스트 코드 작성시 import 2
1. get을 친다음에 ctrl + space를 한번더 눌러서 MockMvcRequestBuilders.get을 고른다음에 alt + enter를 눌러서 등록한다
2. import statically 를 골라주면 된다

# edit custom vm options가 안들어가질 경우
이 오류는 IntelliJ IDEA가 idea64.exe.vmoptions 파일에 쓸 수 없다는 것을 의미합니다. 이는 주로 파일이 존재하지 않거나 경로에 문제가 있을 때 발생합니다. 다음 단계를 따라 문제를 해결해 보세요:

AppData 폴더 접근:
파일 탐색기에서 %AppData%를 입력하고 Enter를 눌러 AppData 폴더로 이동합니다.
Roaming 폴더로 이동한 후 JetBrains 폴더를 찾습니다.
IdeaIC2024.2 폴더가 있는지 확인합니다.
폴더 및 파일 생성:
IdeaIC2024.2 폴더가 없다면 새로 생성합니다.
IdeaIC2024.2 폴더 안에 idea64.exe.vmoptions 파일을 생성합니다. 파일이 없다면 빈 텍스트 파일을 만들고 이름을 idea64.exe.vmoptions로 변경합니다.
파일 권한 확인:
idea64.exe.vmoptions 파일에 쓰기 권한이 있는지 확인합니다. 파일을 마우스 오른쪽 버튼으로 클릭하고 속성을 선택한 후, 보안 탭에서 현재 사용자에게 쓰기 권한이 있는지 확인합니다.


# query dsl 설정 방법 (참고 하기)
https://velog.io/@isohyeon/Spring-Boot-Gradle-%EB%B9%8C%EB%93%9C-%EC%8B%9C-Execution-failed-for-task-compileJava.-invalid-source-release-11-%EC%98%A4%EB%A5%98-%ED%95%B4%EA%B2%B0

# 이것 저것 참고 할 github
https://github.com/kitae104/ShoppingMall/blob/main/src/main/resources/application.yml


# querydsl 기능 (중요) == repository에 추가한다
QuerydslPredicateExecutor<entity> // 이거는 여기에 들어가는 entity에서 모든 필드들에 대한 기본 검색기능을 추가해준다  == 이것만 쓰면 검색(대소문자 상관없음)은 가능한데 부분검색은 안된다
QuerydslBinderCustomizer<QArticleComment> // 이거는 내가 원하는대로 검색될수 있게 하는 기능을 만들기위해 사용


#  query dsl 설정방법
// 스프링 부트 3.0 미만
//	implementation "com.querydsl:querydsl-jpa"
//	annotationProcessor "com.querydsl:querydsl-apt:${dependencyManagement.importedProperties['querydsl.version']}:jpa" // querydsl JPAAnnotationProcessor 사용 지정

	// 스프링 부트 3.0 이상
//	implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta'
//	annotationProcessor "com.querydsl:querydsl-apt:${dependencyManagement.importedProperties['querydsl.version']}:jakarta"


# ctrl + f9 이 재빌드 단축키 이다(중요)

# 검색 http://localhost:8080/api/articles?title=파라미터 값


# spring boot 3.0 이상 버전부터는 클래스의 생성자가 여러 개일 경우를 제외하면 더이상 @ConstructorBinding 어노테이션을 사용할 필요가 없다고 한다.
https://www.zodaland.com/tip/64


✔ MockMvc
웹 어플리케이션을 어플리케이션 서버에 배포하지 않고 테스트용 MVC 환경을 만들어 요청 및 전송,
응답기능을 제공하는 유틸리티 클래스

✔ Query DSL
JPA를 좀 더 효율적으로 사용할 수 있는 라이브러리
오픈소스 프로젝트로 JPQL을 Java 코드로 작성할 수 있도록 함
정적 타입을 이용해서 SQL과 같은 쿼리를 생성해줌


# thymeleaf (참고) ==Thymeleaf3Properties
https://gist.github.com/djkeh/6e1d557ce8c466135b1541d342b1c25c


# thymeleaf 를 사용하면서 security를 사용해서 의존성을 추가할시 밑의 부분이 필요하다
implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity6'

# 위의 시큐리티 의존성이 생성될려면
Spring Initializr 에서 라이브러리로 thymelaf,security 두개를 동시에 넣고  explorer를 해야 사용 할 수 있다

# 스프링 릴리즈 노트
https://spring.io/projects/spring-framework ,
https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.3-Release-Notes



# mappedby는 양뱡향 매핑에서만 사용한다 단방향 매핑에서는 사용x
JPA에서 "관계의 주인(owner of the relationship)"은 외래 키(foreign key)를
관리하는 쪽입니다. @ManyToOne 관계에서는 "다쪽", 즉 @ManyToOne을 가진 엔티티가
외래 키를 가지게 되므로, 다 쪽이 관계의 주인이 됩니다.

예를 들면 게시글 하나에는 여러개의 댓글을 보여줘야 하고 댓글에서는 게시글이 있어야
보일수 있기 떄문에 양방향 매핑을 사용해야한다


# 이미지 테이블 분리할지 아니면 컬럼을 추가할지
사용자 프로필 사진과 공구글 이미지를 동시에 관리하려면 여러 방법이 있을 수 있는데, 두 가지 주요 접근 방식으로 생각해볼 수 있습니다.

1. 별도의 테이블로 분리
   프로필 이미지와 공구글 이미지는 성격이 다르므로, 각각의 테이블로 분리하는 방법입니다.

장점:

각 이미지의 성격이 명확하게 구분되기 때문에 유지보수가 용이합니다.
프로필 이미지와 게시글 이미지를 처리하는 로직을 따로 관리할 수 있습니다.
두 이미지 간의 데이터가 섞일 우려가 없습니다.
단점:

테이블이 증가하게 되어 DB 구조가 복잡해질 수 있습니다.
구현 예시:
ProfileImageEntity:

java
코드 복사
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileImageEntity extends BaseEntity {

    private String imgName;      // 저장된 이미지 파일명
    private String oriImgName;   // 원본 이미지 파일명
    private String imgUrl;       // 이미지 저장 경로

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;     // 사용자와 일대일 관계
}
UserEntity에 프로필 이미지 추가:

java
코드 복사
@Entity
@Getter
@Setter
public class UserEntity extends BaseEntity {

    private String email;

    // 프로필 이미지 정보
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProfileImageEntity profileImage;
}
2. 카테고리 컬럼을 추가해서 관리
   하나의 ImageEntity를 사용하고, 이미지의 용도를 구분하기 위해 category 컬럼을 추가하는 방법입니다. 이 방법에서는 이미지가 어떤 용도로 사용되는지 구분할 수 있는 ENUM 타입 필드를 두어, 공구글 이미지와 프로필 이미지를 동일한 테이블에 저장하면서도 용도에 따라 관리할 수 있습니다.

장점:

테이블 수가 늘어나지 않아서 DB 구조가 단순합니다.
이미지를 관리하는 코드가 중복되지 않습니다.
단점:

이미지의 사용 용도가 섞일 수 있으므로, 데이터를 관리할 때 주의해야 합니다.
쿼리를 작성할 때 용도별로 분리해야 하기 때문에 복잡해질 수 있습니다.
구현 예시:
ImageEntity 수정:



# test코드 부분에서 DataRestTest 다시 해보기


# postgressql 유정 생성과 권한부여 방법 하는법(중요)
https://www.guru99.com/ko/postgresql-create-alter-add-user.html


# 댓글 수정
github에서 #댓글 업데이트 기능의 삭제  이거를 repository에서 검색해서 보기 

# 인증 기능고도화 카카오톡으로 로그인(1) 이부분에서 oauth설정하는 방법있음(배포한 url 넣는 방법도 있음)(매우 중요)

# javadoc 사용법 알아두기

# https://springdoc.org/