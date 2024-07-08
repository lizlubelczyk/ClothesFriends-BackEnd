package com.ClothesFriends.ClothesFriendsBackEnd.DTO.ClothingItem;


public class GetClothingItemBySubcategoryDTO {
    private String image;
    private Integer id;

    public GetClothingItemBySubcategoryDTO(String image, Integer id) {
        this.image = image;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
