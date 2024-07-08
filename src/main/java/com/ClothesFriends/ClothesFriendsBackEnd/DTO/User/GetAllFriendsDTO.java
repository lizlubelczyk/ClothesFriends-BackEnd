package com.ClothesFriends.ClothesFriendsBackEnd.DTO.User;


public class GetAllFriendsDTO {
    private String profilePicture;
    private String username;
    private Integer userId;

    public GetAllFriendsDTO(String profilePicture, String username, Integer userId) {
        this.profilePicture = profilePicture;
        this.username = username;
        this.userId = userId;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
