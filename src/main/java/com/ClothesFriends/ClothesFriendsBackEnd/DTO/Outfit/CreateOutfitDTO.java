package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Outfit;

public class CreateOutfitDTO {
    private byte[] image;
    private String description;
    private Integer userId;

    public CreateOutfitDTO(byte[] image, String description, Integer userId) {
        this.image = image;
        this.description = description;
        this.userId = userId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
