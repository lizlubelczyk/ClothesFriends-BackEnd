package com.ClothesFriends.ClothesFriendsBackEnd.DTO.ClothingItem;

public class GetClothingItemDTO {
    private String name;
    private String description;
    private String image;
    private boolean available;

    public GetClothingItemDTO(String name, String description, String image, boolean available) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.available = available;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
