package com.ClothesFriends.ClothesFriendsBackEnd.DTO.User;

import lombok.Data;

@Data
public class GetAllFriendsDTO {
    private String profilePicture;
    private String username;
    private Integer userId;

    public GetAllFriendsDTO(String profilePicture, String username, Integer userId) {
        this.profilePicture = profilePicture;
        this.username = username;
        this.userId = userId;
    }
}
