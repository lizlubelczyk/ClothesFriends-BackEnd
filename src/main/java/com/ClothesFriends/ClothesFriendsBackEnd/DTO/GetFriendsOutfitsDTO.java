package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import lombok.Data;

@Data
public class GetFriendsOutfitsDTO {
    private Integer outfitId;
    private String Description;
    private String image;
    private String username;
    private String profilePicture;
    private Integer userId;

    public GetFriendsOutfitsDTO(Integer outfitId, String description, String image, String username, String profilePicture, Integer userId) {
        this.outfitId = outfitId;
        Description = description;
        this.image = image;
        this.username = username;
        this.profilePicture = profilePicture;
        this.userId = userId;
    }
}
