package com.example.JpaBoard_admin.repository;

import com.example.JpaBoard_admin.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}