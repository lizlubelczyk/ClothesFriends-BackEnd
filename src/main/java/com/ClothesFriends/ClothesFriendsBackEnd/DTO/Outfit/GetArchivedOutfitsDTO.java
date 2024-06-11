package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Outfit;

import lombok.Data;

@Data
public class GetArchivedOutfitsDTO {
    private int outfit_id;
    private String image;

    public GetArchivedOutfitsDTO(int outfit_id, String image) {
        this.outfit_id = outfit_id;
        this.image = image;
    }
}
