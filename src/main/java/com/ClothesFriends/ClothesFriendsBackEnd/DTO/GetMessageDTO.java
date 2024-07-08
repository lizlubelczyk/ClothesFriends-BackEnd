package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import java.time.LocalDateTime;

public class GetMessageDTO {
    private String message;
    private String username;
    private String userId;
    private LocalDateTime sentAt;

    public GetMessageDTO(String message, String username, String userId, LocalDateTime sentAt) {
        this.message = message;
        this.username = username;
        this.userId = userId;
        this.sentAt = sentAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
