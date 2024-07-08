package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration;


public class LikeInspirationDTO {
    private Integer inspirationId;
    private Integer userId;

    public LikeInspirationDTO(Integer inspirationId, Integer userId) {
        this.inspirationId = inspirationId;
        this.userId = userId;
    }

    public Integer getInspirationId() {
        return inspirationId;
    }

    public void setInspirationId(Integer inspirationId) {
        this.inspirationId = inspirationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
