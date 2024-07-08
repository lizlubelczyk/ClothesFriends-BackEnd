package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Outfit;

import java.time.LocalDateTime;

public class GetArchivedOutfitDTO {
    private String image;
    private LocalDateTime created_at;

    public GetArchivedOutfitDTO(String image, LocalDateTime created_at) {
        this.image = image;
        this.created_at = created_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }
}
