package com.example.JpaBoard.config;

import com.example.JpaBoard.dto.UserAccountDto;
import com.example.JpaBoard.service.UserAccountService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean
    private UserAccountService userAccountService;

    //각 테스트 메서드가 실행되기 직전에 실행됨
    //인증이나 권한 관련 설정을 테스트하기 전에 초기화 작업을 수행하는 데 사용
    // 존재 하지 않는 가상의 데이터이다
    @BeforeTestMethod //스프링 인증 테스트 할때는 이게 딱 좋다
    public void securitySetUp() { //anyString()== 어떤 문자열이 들어가도 좋다
        given(userAccountService.searchUser(anyString()))
                .willReturn(Optional.of(createUserAccountDto()));
        given(userAccountService.saveUser(anyString(), anyString(), anyString(), anyString(), anyString()))
                .willReturn(createUserAccountDto());
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "unoTest",
                "pw",
                "uno-test@email.com",
                "uno-test",
                "test memo"
        )   ;
    }
}
