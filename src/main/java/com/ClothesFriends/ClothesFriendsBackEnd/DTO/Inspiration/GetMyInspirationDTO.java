package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration;

import lombok.Data;

@Data
public class GetMyInspirationDTO {
    private String image;
    private String description;

    private Integer userId;

    public GetMyInspirationDTO(String image, String description, Integer userId) {
        this.image = image;
        this.description = description;
        this.userId = userId;
    }
}
