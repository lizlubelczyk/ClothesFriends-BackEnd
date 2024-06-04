package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.GetChatDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.GetChatsDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.GetMessageDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/{chatId}/getMessages")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetMessageDTO>> getMessages(@PathVariable Integer chatId) {
        List<GetMessageDTO> messages = chatService.getMessages(chatId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/{userId}/{chatId}/getChat")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetChatDTO> getChat(@PathVariable Integer userId, @PathVariable Integer chatId) {
        GetChatDTO chat = chatService.getChat(userId, chatId);
        return ResponseEntity.ok(chat);
    }

    @GetMapping("/{chatId}/isOpen")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Boolean> isOpen(@PathVariable Integer chatId) {
        Boolean isOpen = chatService.isOpen(chatId);
        return ResponseEntity.ok(isOpen);
    }

    @PostMapping("/{chatId}/close")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Boolean> closeChat(@PathVariable Integer chatId) {
        chatService.closeChat(chatId);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/{chatId}/{userId}/createMessage")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> createMessage(@PathVariable Integer chatId, @PathVariable Integer userId, @RequestBody String message) {
        chatService.createMessage(chatId, userId, message);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/getChats")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetChatsDTO>> getChats(@PathVariable Integer userId) {
        List<GetChatsDTO> chats = chatService.getChats(userId);
        return ResponseEntity.ok(chats);
    }

    @GetMapping("/{userId}/getChats/Open")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetChatsDTO>> getOpenChats(@PathVariable Integer userId) {
        List<GetChatsDTO> chats = chatService.getOpenChats(userId);
        return ResponseEntity.ok(chats);
    }

}
