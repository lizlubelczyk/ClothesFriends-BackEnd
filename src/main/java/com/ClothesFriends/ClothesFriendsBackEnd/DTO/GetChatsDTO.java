package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import lombok.Data;

@Data
public class GetChatsDTO {
    private String user;
    private Integer userId;
    private Boolean isOpen;
    private String profilePicture;
    private String LastMessage;
    private Integer id;
    private String clothingItem;

    public GetChatsDTO(String user, Integer userId, Boolean isOpen, String profilePicture, String LastMessage, Integer id, String clothingItem) {
        this.user = user;
        this.userId = userId;
        this.isOpen = isOpen;
        this.profilePicture = profilePicture;
        this.LastMessage = LastMessage;
        this.id = id;
        this.clothingItem = clothingItem;

    }
}
