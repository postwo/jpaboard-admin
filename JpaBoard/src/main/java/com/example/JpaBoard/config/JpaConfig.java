package com.example.JpaBoard.config;

import com.example.JpaBoard.dto.security.BoardPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {  //jpa auditing 기능을 쓰기 위해 `JpaConfig` 추가

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated) //인증이 되었는지 확인작업
                .map(Authentication::getPrincipal)
                .map(BoardPrincipal.class::cast)
                .map(BoardPrincipal::getUsername);
    }
}