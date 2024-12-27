package com.example.JpaBoard_admin.repository;

import com.example.JpaBoard_admin.domain.AdminAccount;
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
@Import(AdminAccountRepositoryTest.TestJpaConfig.class)
@DataJpaTest
class AdminAccountRepositoryTest {

    private final AdminAccountRepository adminAccountRepository;

    public AdminAccountRepositoryTest(@Autowired AdminAccountRepository adminAccountRepository) {
            this.adminAccountRepository = adminAccountRepository;
    }

    //이렇게 하면 delete 테스트도 통과 된다.
    @BeforeEach
    public void setUp() {
        adminAccountRepository.deleteAll();

        // 테스트 데이터 삽입
        adminAccountRepository.save(AdminAccount.of("uno", "{noop}asdf1234", new HashSet<>(Set.of(RoleType.ADMIN)), "Uno", "uno@mail.com", "I am Uno."));
        adminAccountRepository.save(AdminAccount.of("mark", "{noop}asdf1234", new HashSet<>(Set.of(RoleType.MANAGER)), "Mark", "mark@mail.com", "I am Mark."));
        adminAccountRepository.save(AdminAccount.of("susan", "{noop}asdf1234", new HashSet<>(Set.of(RoleType.MANAGER, RoleType.DEVELOPER)), "Susan", "susan@mail.com", "I am Susan."));
        adminAccountRepository.save(AdminAccount.of("jim", "{noop}asdf1234", new HashSet<>(Set.of(RoleType.USER)), "Jim", "jim@mail.com", "I am Jim."));
    }

    @DisplayName("회원 정보 select 테스트")
    @Test
    void givenAdminAccounts_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<AdminAccount> adminAccounts = adminAccountRepository.findAll();

            // Then
        assertThat(adminAccounts)
                .isNotNull()
                .hasSize(4);
    }

    @DisplayName("회원 정보 insert 테스트")
    @Test
    void givenAdminAccount_whenInserting_thenWorksFine() {
        // Given
        long previousCount = adminAccountRepository.count();
        AdminAccount adminAccount = AdminAccount.of("test", "pw", Set.of(RoleType.DEVELOPER), null, null, null);

        // When
        adminAccountRepository.save(adminAccount);

        // Then
        assertThat(adminAccountRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("회원 정보 update 테스트")
    @Test
    void givenAdminAccountAndRoleType_whenUpdating_thenWorksFine() {
        // Given
        AdminAccount adminAccount = adminAccountRepository.getReferenceById("uno");
        adminAccount.addRoleType(RoleType.DEVELOPER);
        adminAccount.addRoleTypes(List.of(RoleType.USER, RoleType.USER));
        adminAccount.removeRoleType(RoleType.ADMIN);

        // When
        AdminAccount updatedAccount = adminAccountRepository.saveAndFlush(adminAccount);

        // Then
        assertThat(updatedAccount)
                .hasFieldOrPropertyWithValue("userId", "uno")
                .hasFieldOrPropertyWithValue("roleTypes", Set.of(RoleType.DEVELOPER, RoleType.USER));
        }


        @DisplayName("회원 정보 delete 테스트")
        @Test
        void givenAdminAccount_whenDeleting_thenWorksFine() {
            // Given
            long previousCount = adminAccountRepository.count();
            AdminAccount adminAccount = adminAccountRepository.getReferenceById("uno");

            // When
            adminAccountRepository.delete(adminAccount);

            // Then
            assertThat(adminAccountRepository.count()).isEqualTo(previousCount - 1);
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