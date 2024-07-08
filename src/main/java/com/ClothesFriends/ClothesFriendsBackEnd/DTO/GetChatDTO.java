package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

public class GetChatDTO {
    private String user;
    private Integer userId;
    private Boolean isOpen;
    private String profilePicture;
    private String clothingItem;

    public GetChatDTO(String user, Integer userId, Boolean isOpen, String profilePicture, String clothingItem) {
        this.user = user;
        this.userId = userId;
        this.isOpen = isOpen;
        this.profilePicture = profilePicture;
        this.clothingItem = clothingItem;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getClothingItem() {
        return clothingItem;
    }

    public void setClothingItem(String clothingItem) {
        this.clothingItem = clothingItem;
    }
}