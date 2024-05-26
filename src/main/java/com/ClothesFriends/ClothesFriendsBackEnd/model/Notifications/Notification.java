package com.ClothesFriends.ClothesFriendsBackEnd.model.Notifications;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity

public class Notification {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "status", nullable = false)
    private NotificationStatus status = NotificationStatus.UNREAD;

    @Column(name = "notification_type", nullable = false)
    private NotificationType notificationType;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column( name = "redirect_url", nullable = true)
    private String redirectUrl;



}
