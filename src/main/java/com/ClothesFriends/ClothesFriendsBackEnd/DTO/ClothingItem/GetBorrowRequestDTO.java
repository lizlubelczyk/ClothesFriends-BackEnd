package com.ClothesFriends.ClothesFriendsBackEnd.DTO.ClothingItem;

import lombok.Data;

@Data
public class GetBorrowRequestDTO {
    private String username;
    private String clothingItemImage;

    public GetBorrowRequestDTO(String username, String clothingItemImage) {
        this.username = username;
        this.clothingItemImage = clothingItemImage;
    }
}


