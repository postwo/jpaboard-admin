package com.example.JpaBoard_admin.dto.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 어드민 프로젝트 전용 프로퍼티
 *
 * @param board 게시판 관련 프로퍼티
 */
@ConfigurationProperties("project") //application.yml에 정의된 값을 쉽게 읽어올 수 있다
public record ProjectProperties(Board board) {
    /**
     * 게시판 관련 프로퍼티
     *
     * @param url 게시판 서비스 호스트명
     */
    // application.yml 파일에 있는 project.board.url 값을 읽어서 url 해당 필드에 주입하게 됩니다
    public record Board(String url) {}
}
