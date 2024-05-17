package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration;

import lombok.Data;

@Data
public class GetMyInspirationDTO {
    private String image;
    private String description;

    public GetMyInspirationDTO(String image, String description) {
        this.image = image;
        this.description = description;
    }
}
