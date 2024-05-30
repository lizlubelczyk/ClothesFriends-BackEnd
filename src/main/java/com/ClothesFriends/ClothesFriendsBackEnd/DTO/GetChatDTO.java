package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import lombok.Data;

@Data
public class GetChatDTO {
    private String user;
    private Integer userId;
    private Boolean isOpen;
    private String profilePicture;
    private String clothingItem;

    public GetChatDTO(String user, Integer userId, Boolean isOpen, String profilePicture, String clothingItem) {
        this.user = user;
        this.userId = userId;
        this.isOpen = isOpen;
        this.profilePicture = profilePicture;
        this.clothingItem = clothingItem;
    }
}
