package com.ClothesFriends.ClothesFriendsBackEnd.DTO.ClothingItem;

public class CreateClothingItemDTO {
    private String name;
    private String description;
    private byte[] image;
    private String subcategory;
    private boolean available;
    private Integer userId;

    public CreateClothingItemDTO(String name, String description, byte[] image, String subcategory, boolean available, Integer userId) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.subcategory = subcategory;
        this.available = available;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}
