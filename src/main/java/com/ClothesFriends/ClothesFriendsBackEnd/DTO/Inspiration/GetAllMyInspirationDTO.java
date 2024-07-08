package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration;


public class GetAllMyInspirationDTO {
    private Integer inspirationId;
    private String image;

    public GetAllMyInspirationDTO(Integer inspirationId, String image) {
        this.inspirationId = inspirationId;
        this.image = image;
    }

    public Integer getInspirationId() {
        return inspirationId;
    }

    public void setInspirationId(Integer inspirationId) {
        this.inspirationId = inspirationId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
