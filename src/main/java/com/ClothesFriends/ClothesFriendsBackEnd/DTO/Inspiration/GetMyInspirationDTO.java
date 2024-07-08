package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration;


public class GetMyInspirationDTO {
    private String image;
    private String description;

    private Integer userId;
    private String fullName;

    public GetMyInspirationDTO(String image, String description, Integer userId, String fullName) {
        this.image = image;
        this.description = description;
        this.userId = userId;
        this.fullName = fullName;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
