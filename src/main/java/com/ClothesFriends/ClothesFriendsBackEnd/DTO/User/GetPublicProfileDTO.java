package com.ClothesFriends.ClothesFriendsBackEnd.DTO.User;

public class GetPublicProfileDTO {
    private String username;
    private String profilePicture;
    private String fullName;

    public GetPublicProfileDTO(String username, String profilePicture, String fullName) {
        this.username = username;
        this.profilePicture = profilePicture;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
