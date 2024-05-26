package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Outfit;

import lombok.Data;

@Data
public class GetMyOutfitDTO {
    private Integer outfitId;
    private String image;
    private String description;



    public GetMyOutfitDTO(Integer outfitId, String image, String description) {
        this.outfitId = outfitId;
        this.image = image;
        this.description = description;
    }

}
