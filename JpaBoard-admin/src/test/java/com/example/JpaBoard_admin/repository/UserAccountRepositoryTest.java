package com.example.JpaBoard_admin.repository;

import com.example.JpaBoard_admin.domain.UserAccount;
import com.example.JpaBoard_admin.domain.constant.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@Import(JpaRepositoryTest.TestJpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {
    private final UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(@Autowired UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    //이렇게 하면 delete 테스트는 통과 된다.
    @BeforeEach
    public void setUp() {
        userAccountRepository.deleteAll();

        // 테스트 데이터 삽입
        userAccountRepository.save(UserAccount.of("uno", "{noop}asdf1234", new HashSet<>(Set.of(RoleType.ADMIN)), "Uno", "uno@mail.com", "I am Uno."));
        userAccountRepository.save(UserAccount.of("mark", "{noop}asdf1234", new HashSet<>(Set.of(RoleType.MANAGER)), "Mark", "mark@mail.com", "I am Mark."));
        userAccountRepository.save(UserAccount.of("susan", "{noop}asdf1234", new HashSet<>(Set.of(RoleType.MANAGER, RoleType.DEVELOPER)), "Susan", "susan@mail.com", "I am Susan."));
        userAccountRepository.save(UserAccount.of("jim", "{noop}asdf1234", new HashSet<>(Set.of(RoleType.USER)), "Jim", "jim@mail.com", "I am Jim."));
    }



    @DisplayName("회원 정보 select 테스트")
    @Test
    void givenUserAccounts_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<UserAccount> userAccounts = userAccountRepository.findAll();

        // Then
        assertThat(userAccounts)
                .isNotNull()
                .hasSize(4);
    }

    @DisplayName("회원 정보 insert 테스트")
    @Test
    void givenUserAccount_whenInserting_thenWorksFine() {
        // Given
        long previousCount = userAccountRepository.count();
        UserAccount userAccount = UserAccount.of("test", "pw", Set.of(RoleType.DEVELOPER), null, null, null);

        // When
        userAccountRepository.save(userAccount);

        // Then
        assertThat(userAccountRepository.count()).isEqualTo(previousCount + 1);
    }

    //rollback mode에 트랜잭션이 걸린 상태이다. 업데이트가 반영이 안되게 한다.
    @DisplayName("회원 정보 update 테스트")
    @Test
    void givenUserAccountAndRoleType_whenUpdating_thenWorksFine() {
        // Given
        UserAccount userAccount = userAccountRepository.getReferenceById("uno");
        userAccount.addRoleType(RoleType.DEVELOPER);
        userAccount.addRoleTypes(List.of(RoleType.USER, RoleType.USER));
        userAccount.removeRoleType(RoleType.ADMIN);

        // When
        UserAccount updatedAccount = userAccountRepository.saveAndFlush(userAccount);

        // Then
        assertThat(updatedAccount)
                .hasFieldOrPropertyWithValue("userId", "uno")
                .hasFieldOrPropertyWithValue("roleTypes", Set.of(RoleType.DEVELOPER, RoleType.USER));
    }

    @DisplayName("회원 정보 delete 테스트")
    @Test
    void givenUserAccount_whenDeleting_thenWorksFine() {
        // Given
        long previousCount = userAccountRepository.count(); //지웠으니까 하나가 줄어든다.
        UserAccount userAccount = userAccountRepository.getReferenceById("uno");

        // When
        userAccountRepository.delete(userAccount);

        // Then
        assertThat(userAccountRepository.count()).isEqualTo(previousCount - 1);
    }

    @EnableJpaAuditing
    @TestConfiguration
    static class TestJpaConfig {
        @Bean
        AuditorAware<String> auditorAware() {
            return () -> Optional.of("uno");
        }
    }
}