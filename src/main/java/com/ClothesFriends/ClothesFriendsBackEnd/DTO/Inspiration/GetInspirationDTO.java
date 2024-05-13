package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration;

import lombok.Data;

@Data
public class GetInspirationDTO {
    private String image;
    private Integer likes;
    private String username;
    private String description;

    public GetInspirationDTO(String image, Integer likes, String username, String description) {
        this.image = image;
        this.likes = likes;
        this.username = username;
        this.description = description;
    }
}
