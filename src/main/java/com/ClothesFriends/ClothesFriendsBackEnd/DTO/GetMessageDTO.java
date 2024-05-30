package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
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
}
