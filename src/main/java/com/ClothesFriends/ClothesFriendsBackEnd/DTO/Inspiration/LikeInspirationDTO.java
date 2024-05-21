package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration;

import lombok.Data;

@Data
public class LikeInspirationDTO {
    private Integer inspirationId;
    private Integer userId;

    public LikeInspirationDTO(Integer inspirationId, Integer userId) {
        this.inspirationId = inspirationId;
        this.userId = userId;
    }


}
