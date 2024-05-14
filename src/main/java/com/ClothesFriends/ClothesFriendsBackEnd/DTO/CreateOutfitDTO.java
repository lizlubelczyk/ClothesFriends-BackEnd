package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class CreateOutfitDTO {
    private byte[] image;
    private String description;
    private Integer userId;
    private Date timestamp;

    public CreateOutfitDTO(byte[] image, String description, Integer userId, Date timestamp) {
        this.image = image;
        this.description = description;
        this.userId = userId;
        this.timestamp = timestamp;
    }
}
