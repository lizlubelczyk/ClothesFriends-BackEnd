package com.ClothesFriends.ClothesFriendsBackEnd.DTO.ClothingItem;

import lombok.Data;

@Data
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


}
