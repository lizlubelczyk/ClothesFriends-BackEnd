package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Outfit;

public class GetFriendsOutfitsDTO {
    private Integer outfitId;
    private String Description;
    private String image;
    private String username;
    private String profilePicture;
    private Integer userId;

    public GetFriendsOutfitsDTO(Integer outfitId, String description, String image, String username, String profilePicture, Integer userId) {
        this.outfitId = outfitId;
        this.Description = description;
        this.image = image;
        this.username = username;
        this.profilePicture = profilePicture;
        this.userId = userId;
    }

    public Integer getOutfitId() {
        return outfitId;
    }

    public void setOutfitId(Integer outfitId) {
        this.outfitId = outfitId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}