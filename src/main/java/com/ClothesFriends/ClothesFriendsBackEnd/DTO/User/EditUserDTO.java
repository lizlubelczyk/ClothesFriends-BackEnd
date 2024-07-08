package com.ClothesFriends.ClothesFriendsBackEnd.DTO.User;


public class EditUserDTO {
    private String email;
    private String fullName;
    private String username;

    private String profilePicture;
    private Boolean isPublic;

    public EditUserDTO(String email, String fullName, String username, String profilePicture, Boolean isPublic) {
        this.email = email;
        this.fullName = fullName;
        this.username = username;
        this.profilePicture = profilePicture;
        this.isPublic = isPublic;
    }
}
