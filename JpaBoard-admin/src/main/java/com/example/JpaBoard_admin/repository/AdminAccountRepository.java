package com.example.JpaBoard_admin.repository;

import com.example.JpaBoard_admin.domain.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAccountRepository extends JpaRepository<AdminAccount, String> {
}