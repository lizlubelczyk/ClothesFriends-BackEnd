package com.ClothesFriends.ClothesFriendsBackEnd.DTO.ClothingItem;

import lombok.Data;

@Data
public class GetClothingItemBySubcategoryDTO {
    private String image;
    private Integer id;

    public GetClothingItemBySubcategoryDTO(String image, Integer id) {
        this.image = image;
        this.id = id;
    }
}
