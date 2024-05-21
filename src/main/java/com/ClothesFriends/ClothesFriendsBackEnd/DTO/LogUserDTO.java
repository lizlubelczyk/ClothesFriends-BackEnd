package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import lombok.Data;

@Data
public class LogUserDTO {
    private String username;
    private String password;

    public LogUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
