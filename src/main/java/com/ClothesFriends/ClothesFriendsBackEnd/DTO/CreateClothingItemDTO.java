package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
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
}
