package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import lombok.Data;

@Data
public class GetOtherUserDTO {
    private String profilePicture;
    private String username;
    private String fullName;

    public GetOtherUserDTO(String profilePicture, String username, String fullName) {
        this.profilePicture = profilePicture;
        this.username = username;
        this.fullName = fullName;
    }
}
