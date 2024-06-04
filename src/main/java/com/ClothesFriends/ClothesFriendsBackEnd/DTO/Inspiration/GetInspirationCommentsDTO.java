package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration;

import lombok.Data;

@Data
public class GetInspirationCommentsDTO {
    private Integer commentId;
    private Integer userId;
    private String username;
    private String comment;
    private String profilePicture;

    public GetInspirationCommentsDTO(Integer userId, String username, String comment, String profilePicture, Integer commentId) {
        this.userId = userId;
        this.username = username;
        this.comment = comment;
        this.profilePicture = profilePicture;
        this.commentId = commentId;
    }
}
