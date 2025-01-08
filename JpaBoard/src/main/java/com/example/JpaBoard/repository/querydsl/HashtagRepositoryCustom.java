package com.example.JpaBoard.repository.querydsl;

import java.util.List;

public interface HashtagRepositoryCustom {
    List<String> findAllHashtagNames();
}
