package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Notifications.NotificationType;

public class GetNotificationDTO {
    private Integer id;
    private Integer userId;
    private String username;
    private String message;
    private String notificationURL;
    private String userProfilePicture;
    private NotificationType type;

    public GetNotificationDTO(Integer id, Integer userId, String username, String message, String notificationURL, String userProfilePicture, NotificationType type) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.message = message;
        this.notificationURL = notificationURL;
        this.userProfilePicture = userProfilePicture;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNotificationURL() {
        return notificationURL;
    }

    public void setNotificationURL(String notificationURL) {
        this.notificationURL = notificationURL;
    }

    public String getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}