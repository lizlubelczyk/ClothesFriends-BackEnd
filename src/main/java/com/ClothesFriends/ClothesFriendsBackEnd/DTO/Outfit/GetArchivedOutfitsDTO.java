package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Outfit;


public class GetArchivedOutfitsDTO {
    private int outfit_id;
    private String image;

    public GetArchivedOutfitsDTO(int outfit_id, String image) {
        this.outfit_id = outfit_id;
        this.image = image;
    }

    public int getOutfit_id() {
        return outfit_id;
    }

    public void setOutfit_id(int outfit_id) {
        this.outfit_id = outfit_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
