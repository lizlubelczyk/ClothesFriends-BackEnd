package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration;

import lombok.Data;

@Data
public class CreateInspirationDTO {
    private byte[] image;
    private String description;
    private Integer userId;

    public CreateInspirationDTO(byte[] image, String description, Integer userId) {
        this.image = image;
        this.description = description;
        this.userId = userId;
    }
}
