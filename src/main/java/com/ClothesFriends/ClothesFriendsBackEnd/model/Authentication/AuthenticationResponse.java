package com.ClothesFriends.ClothesFriendsBackEnd.model.Authentication;

public class AuthenticationResponse {
    private String token;
    private Integer id;

    public AuthenticationResponse(String token, Integer id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public Integer getId() {
        return id;
    }
}
