package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Outfit;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetArchivedOutfitDTO {
    private String image;
    private LocalDateTime created_at;

    public GetArchivedOutfitDTO(String image, LocalDateTime created_at) {
        this.image = image;
        this.created_at = created_at;
    }
}
