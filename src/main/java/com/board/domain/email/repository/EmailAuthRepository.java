package com.board.domain.email.repository;

import com.board.domain.email.entity.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, UUID>, EmailAuthCustomRepository {
}
