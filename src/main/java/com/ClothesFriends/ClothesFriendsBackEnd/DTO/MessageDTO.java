package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import java.time.LocalDateTime;

public class MessageDTO {
    private Integer id;
    private String message;
    private Integer userId;
    private String username;
    private LocalDateTime sentAt;
    private Integer chatId;

    public MessageDTO(Integer id, String message, Integer userId, String username, LocalDateTime sentAt, Integer chatId) {
        this.id = id;
        this.message = message;
        this.userId = userId;
        this.username = username;
        this.sentAt = sentAt;
        this.chatId = chatId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }
}