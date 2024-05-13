package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import lombok.Data;

@Data
public class GetOutfitDTO {
    private Integer outfitId;
    private byte[] image;
    private String description;
    private String username;
    private Integer likes;

}
