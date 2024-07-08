package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem.Message;
import com.ClothesFriends.ClothesFriendsBackEnd.service.ChatService;
import com.ClothesFriends.ClothesFriendsBackEnd.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class WebSocketController {

    private final WebSocketService webSocketService;
    private final ChatService chatService;

    @Autowired
    public WebSocketController(WebSocketService webSocketService, ChatService chatService) {
        this.webSocketService = webSocketService;
        this.chatService = chatService;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        // Determine the recipient based on the chat participants
        String recipientUsername= chatService.getRecipientUsername(message.getChatId(), message.getUserId());

        // Add any necessary logic before sending the message (e.g., saving to database)
        webSocketService.sendMessageToUser(recipientUsername, message);
    }
}
