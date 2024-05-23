package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import lombok.Data;

@Data
public class GetAllFriendsInspirationsDTO {

    private Integer inspirationId;

    private String image;

    public GetAllFriendsInspirationsDTO(Integer inspirationId, String image) {
        this.inspirationId = inspirationId;
        this.image = image;
    }

}
