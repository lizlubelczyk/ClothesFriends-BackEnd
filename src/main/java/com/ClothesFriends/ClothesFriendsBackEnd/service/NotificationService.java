package com.ClothesFriends.ClothesFriendsBackEnd.service;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.GetNotificationDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem.BorrowRequest;
import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem.Chat;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Notifications.Notification;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Notifications.NotificationStatus;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Notifications.NotificationType;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void createBorrowRequestNotification(BorrowRequest borrowRequest) {
        Notification notification = new Notification();
        String notificationURL = "/BorrowRequest/" + borrowRequest.getId();
        notification.setNotificationURL(notificationURL);
        notification.setMessage( " te quiere pedir prestado tu " + borrowRequest.getClothingItem().getName());
        notification.setType(NotificationType.BORROW_REQUEST);
        notification.setReceiverUser(borrowRequest.getClothingItem().getUser());
        notification.setSenderUser(borrowRequest.getUser());
        notificationRepository.save(notification);
    }

   public List<GetNotificationDTO> getNotifications(Integer userId) {
       List<Notification> notifications = notificationRepository.getNotificationsByReceiverUserId(userId);
       List<GetNotificationDTO> notificationDTOs = new ArrayList<GetNotificationDTO>();
       for (Notification notification : notifications) {
           notificationDTOs.add(new GetNotificationDTO(notification.getId(), notification.getSenderUser().getId(), notification.getSenderUser().getUsername(), notification.getMessage(), notification.getRedirectUrl(), notification.getSenderUser().getProfilePicture(), notification.getNotificationType()));
       }
         return notificationDTOs;
   }

    public void createUnfriendNotification(User user, User friend) {
        Notification notification = new Notification();
        notification.setMessage("dejó de ser tu amigo");
        notification.setType(NotificationType.UNFRIEND);
        notification.setReceiverUser(friend);
        notification.setSenderUser(user);
        notificationRepository.save(notification);
    }

    public void createBefriendNotification(User user, User friend) {
        Notification notification = new Notification();
        notification.setMessage("ahora es tu amigo");
        notification.setType(NotificationType.BEFRIEND);
        notification.setReceiverUser(friend);
        notification.setSenderUser(user);
        notificationRepository.save(notification);
    }

    public void markNotificationAsSeen(Integer notificationId) {
        Notification notification = notificationRepository.findById(notificationId).get();
        notification.setStatus(NotificationStatus.READ);
        notificationRepository.save(notification);
    }


    public List<GetNotificationDTO> getUnreadNotifications(Integer userId) {
        List<Notification> notifications = notificationRepository.getNotificationsByReceiverUserId(userId);
        List<GetNotificationDTO> notificationDTOs = new ArrayList<GetNotificationDTO>();
        for (Notification notification : notifications) {
            if (notification.getStatus() == NotificationStatus.UNREAD) {
                notificationDTOs.add(new GetNotificationDTO(notification.getId(), notification.getSenderUser().getId(), notification.getSenderUser().getUsername(), notification.getMessage(), notification.getRedirectUrl(), notification.getSenderUser().getProfilePicture(), notification.getNotificationType()));
            }
        }
        return notificationDTOs;
    }

    public void createAcceptBorrowRequestNotification(BorrowRequest borrowRequest, Chat chat) {
        Notification notification = new Notification();
        notification.setMessage(" ha aceptado tu solicitud de préstamo de " + borrowRequest.getClothingItem().getName());
        String notificationURL = "/Chat/" + chat.getId();
        notification.setNotificationURL(notificationURL);
        notification.setType(NotificationType.BORROW_REQUEST_ACCEPTED);
        notification.setReceiverUser(borrowRequest.getUser());
        notification.setSenderUser(borrowRequest.getClothingItem().getUser());
        notificationRepository.save(notification);

    }

    public void createRejectBorrowRequestNotification(BorrowRequest borrowRequest) {
        Notification notification = new Notification();
        notification.setMessage(" ha rechazado tu solicitud de préstamo de " + borrowRequest.getClothingItem().getName());
        notification.setType(NotificationType.BORROW_REQUEST_REJECTED);
        notification.setReceiverUser(borrowRequest.getUser());
        notification.setSenderUser(borrowRequest.getClothingItem().getUser());
        notificationRepository.save(notification);
    }
}
