package com.example.JpaBoard.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


@DisplayName("비즈니스 로직 - 페이지네이션")
// SpringBootTest.WebEnvironment.NONE ==  웹 환경을 전혀 사용하지 않겠다는 의미 , 테스트 시에 내장된 웹 서버(Tomcat 등)가 구동되지 않는다는 의미
// classes = PaginationService.class == 전체 애플리케이션의 컨텍스트를 로드하지 않고, 테스트에 필요한 서비스 또는 클래스만 로드하여 테스트를 진행할 수 있게 된다. 이를 통해 불필요한 의존성을 줄이고, 테스트 속도를 향상
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PaginationService.class)
class PaginationServiceTest {

    private final PaginationService sut;

    PaginationServiceTest(@Autowired PaginationService paginationService) {
        this.sut = paginationService;
    }

    @DisplayName("현재 페이지 번호와 총 페이지 수를 주면, 페이징 바 리스트를 만들어준다.")
    //테스트에 사용할 매개변수들을 제공하는 메서드를 지정하는 애노테이션입니다. 이 메서드는 테스트 메서드에 입력할 매개변수를 반환해야 하며, 보통 스트림(Stream), 컬렉션(Collection), 배열, 또는 Iterable 타입의 데이터를 반환
    @MethodSource
    // ParameterizedTest는 매개변수화된 테스트를 작성할 때 사용하는 애노테이션입니다. 다양한 입력을 통해 테스트를 반복해서 실행
    //매개변수화된 테스트를 작성할 때 사용하는 애노테이션입니다. 다양한 입력을 통해 테스트를 반복해서 실행할 수 있습니다.
    //
    //name 속성: 테스트 케이스마다 표시되는 이름을 지정할 수 있습니다. {} 안의 숫자는 매개변수의 인덱스를 나타냅니다.
    //{index}: 현재 테스트의 인덱스(몇 번째 테스트인지).
    //{0}: 첫 번째 매개변수 (currentPage).
    //{1}: 두 번째 매개변수 (totalPages).
    //{2}: 세 번째 매개변수 (expectedOutput).
    @ParameterizedTest(name = "[{index}] 현재 페이지: {0}, 총 페이지: {1} => {2}")
    void givenCurrentPageNumberAndTotalPages_whenCalculating_thenReturnsPaginationBarNumbers(int currentPageNumber, int totalPages, List<Integer> expected) {
        // Given
        // When
        List<Integer> actual = sut.getPaginationBarNumbers(currentPageNumber, totalPages);
        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // @MethodSource를 사용하기 위해 생성 메서드이름을 동일하게 가져가야 인식을한다
    static Stream<Arguments> givenCurrentPageNumberAndTotalPages_whenCalculating_thenReturnsPaginationBarNumbers() {
        return Stream.of(
                arguments(0, 13, List.of(0, 1, 2, 3, 4)), //페이지가0부터 시작
                arguments(1, 13, List.of(0, 1, 2, 3, 4)),
                arguments(2, 13, List.of(0, 1, 2, 3, 4)),
                arguments(3, 13, List.of(1, 2, 3, 4, 5)), //여기서부터는 중간으로 와야해서 이렇게 설정
                arguments(4, 13, List.of(2, 3, 4, 5, 6)), //4번페이지가 중간으로
                arguments(5, 13, List.of(3, 4, 5, 6, 7)),
                arguments(6, 13, List.of(4, 5, 6, 7, 8)),
                arguments(10, 13, List.of(8, 9, 10, 11, 12)), //이덱스로 다지면 12가 13을 뜻한다
                arguments(11, 13, List.of(9, 10, 11, 12)),
                arguments(12, 13, List.of(10, 11, 12))
        );
    }


    @DisplayName("현재 설정되어 있는 페이지네이션 바의 길이를 알려준다.")
    @Test
    void givenNothing_whenCalling_thenReturnsCurrentBarLength() {
        // Given
        // When
        int barLength = sut.currentBarLength();
        // Then
        assertThat(barLength).isEqualTo(5);
    }
}

