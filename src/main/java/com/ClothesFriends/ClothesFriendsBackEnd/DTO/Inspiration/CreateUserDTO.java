package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String username;
    private String password;
    private String email;
    private String fullName;


    public CreateUserDTO(String username, String password, String email, String fullName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }
}
