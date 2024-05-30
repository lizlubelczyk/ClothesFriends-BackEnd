package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Notifications.NotificationType;
import lombok.Data;

@Data
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



}