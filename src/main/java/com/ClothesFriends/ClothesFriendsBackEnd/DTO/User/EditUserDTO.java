package com.ClothesFriends.ClothesFriendsBackEnd.DTO.User;

import lombok.Data;

@Data
public class EditUserDTO {
    private String email;
    private String fullName;
    private String username;
    private String whatsappLink;

    private String profilePicture;

    public EditUserDTO(String email, String fullName, String username, String whatsappLink, String profilePicture) {
        this.email = email;
        this.fullName = fullName;
        this.username = username;
        this.whatsappLink = whatsappLink;
        this.profilePicture = profilePicture;
    }
}
