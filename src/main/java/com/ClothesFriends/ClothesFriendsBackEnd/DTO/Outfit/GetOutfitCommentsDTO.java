package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Outfit;

import lombok.Data;

@Data
public class GetOutfitCommentsDTO {
    private Integer commentId;
    private Integer userId;
    private String username;
    private String comment;

    private String profilePicture;

    public GetOutfitCommentsDTO(Integer userId, String username, String comment, String profilePicture, Integer commentId) {
        this.userId = userId;
        this.username = username;
        this.comment = comment;
        this.profilePicture = profilePicture;
        this.commentId = commentId;
    }


}
