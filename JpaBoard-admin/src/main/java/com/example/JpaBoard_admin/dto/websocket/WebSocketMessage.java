package com.example.JpaBoard_admin.dto.websocket;

public record WebSocketMessage(String content) {
    public static WebSocketMessage of(String content) {
        return new WebSocketMessage(content);
    }
}