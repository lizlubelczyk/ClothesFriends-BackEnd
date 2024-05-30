package com.ClothesFriends.ClothesFriendsBackEnd.repository;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Notifications.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {


    List<Notification> getNotificationsByReceiverUserId(Integer userId);
}