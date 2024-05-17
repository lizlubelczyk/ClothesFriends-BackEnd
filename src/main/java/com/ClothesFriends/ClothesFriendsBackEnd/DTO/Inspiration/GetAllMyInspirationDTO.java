package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration;

import lombok.Data;

@Data
public class GetAllMyInspirationDTO {
    private Integer inspirationId;
    private String image;

    public GetAllMyInspirationDTO(Integer inspirationId, String image) {
        this.inspirationId = inspirationId;
        this.image = image;
    }

}
