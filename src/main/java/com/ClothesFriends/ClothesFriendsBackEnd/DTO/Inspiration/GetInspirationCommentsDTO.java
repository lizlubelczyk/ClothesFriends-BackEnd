package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration;

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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}