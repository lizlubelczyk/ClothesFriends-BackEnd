package com.ClothesFriends.ClothesFriendsBackEnd.DTO.ClothingItem;


public class GetBorrowRequestDTO {
    private String username;
    private String clothingItemImage;

    public GetBorrowRequestDTO(String username, String clothingItemImage) {
        this.username = username;
        this.clothingItemImage = clothingItemImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClothingItemImage() {
        return clothingItemImage;
    }

    public void setClothingItemImage(String clothingItemImage) {
        this.clothingItemImage = clothingItemImage;
    }
}
