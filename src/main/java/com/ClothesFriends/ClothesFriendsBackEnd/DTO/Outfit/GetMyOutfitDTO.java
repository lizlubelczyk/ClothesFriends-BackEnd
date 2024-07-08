package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Outfit;


public class GetMyOutfitDTO {
    private Integer outfitId;
    private String image;
    private String description;



    public GetMyOutfitDTO(Integer outfitId, String image, String description) {
        this.outfitId = outfitId;
        this.image = image;
        this.description = description;
    }

    public Integer getOutfitId() {
        return outfitId;
    }

    public void setOutfitId(Integer outfitId) {
        this.outfitId = outfitId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
