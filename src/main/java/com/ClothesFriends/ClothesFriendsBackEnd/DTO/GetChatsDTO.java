package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

public class GetChatsDTO {
    private String user;
    private Integer userId;
    private Boolean isOpen;
    private String profilePicture;
    private String LastMessage;
    private Integer id;
    private String clothingItem;

    public GetChatsDTO(String user, Integer userId, Boolean isOpen, String profilePicture, String LastMessage, Integer id, String clothingItem) {
        this.user = user;
        this.userId = userId;
        this.isOpen = isOpen;
        this.profilePicture = profilePicture;
        this.LastMessage = LastMessage;
        this.id = id;
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

    public String getLastMessage() {
        return LastMessage;
    }

    public void setLastMessage(String lastMessage) {
        LastMessage = lastMessage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClothingItem() {
        return clothingItem;
    }

    public void setClothingItem(String clothingItem) {
        this.clothingItem = clothingItem;
    }
}