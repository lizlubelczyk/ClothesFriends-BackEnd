package com.ClothesFriends.ClothesFriendsBackEnd.DTO.User;

import lombok.Data;

@Data
public class UpdateUserRequestDTO {
    private String fullName;
    private String email;
    private String username;
    private String whatsappLink;
    public UpdateUserRequestDTO(String fullName, String email, String username, String whatsappLink) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.whatsappLink = whatsappLink;
    }
}
