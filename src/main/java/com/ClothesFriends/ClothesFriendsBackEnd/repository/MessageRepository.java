package com.ClothesFriends.ClothesFriendsBackEnd.repository;

import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("SELECT m FROM Message m WHERE m.chat.id = :chatId")
    List<Message> findAllByChatId(@Param("chatId") Integer chatId);
    @Query("SELECT m FROM Message m WHERE m.chat.id = :chatId")
    List<Message> findByChatId(@Param("chatId") Integer chatId);}
