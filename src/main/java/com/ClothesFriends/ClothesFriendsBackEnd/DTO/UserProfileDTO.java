package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import lombok.Data;

@Data
public class UserProfileDTO {
    private String username;
    private String profilePicture;

    private String fullName;

    public UserProfileDTO(String username, String profilePicture, String fullName) {
        this.username = username;
        this.profilePicture = profilePicture;
        this.fullName = fullName;
    }


}
