package com.ClothesFriends.ClothesFriendsBackEnd.model.Notifications;

import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "sender_user_id", nullable = false)
    private User senderUser;

    @Column( name = "redirect_url", nullable = true)
    private String redirectUrl;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id", nullable = false)
    private User receiverUser;



    public void setNotificationURL(String notificationURL) {
        this.redirectUrl = notificationURL;
    }

    public void setMessage(String s) {
        this.message = s;
    }

    public void setType(NotificationType type) {
        this.notificationType = type;
    }



    public String getMessage() {
        return message;
    }



    public String getRedirectUrl() {
        return redirectUrl;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public Integer getId() {
        return id;
    }

    public void setReceiverUser(Object user) {
        this.receiverUser = (User) user;
    }

    public void setSenderUser(User user) {
        this.senderUser = user;
    }

    public User getSenderUser() {
        return senderUser;
    }

    public void setStatus(NotificationStatus read) {
        this.status = read;
    }

    public NotificationStatus getStatus() {
        return status;
    }
}
