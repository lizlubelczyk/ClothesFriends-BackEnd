package com.ClothesFriends.ClothesFriendsBackEnd.service;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.GetChatDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.GetChatsDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.GetMessageDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem.Chat;
import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem.ClothingItem;
import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem.Message;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.ChatRepository;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    public List<GetMessageDTO> getMessages(Integer chatId) {
        List<Message> messages = messageRepository.findAllByChatId(chatId);
        List<GetMessageDTO> messageDTOs = new ArrayList<GetMessageDTO>();
        for (Message message : messages) {
            messageDTOs.add(new GetMessageDTO(message.getMessage(), message.getUser().getUsername(), message.getUser().getId().toString(), message.getSentAt()));
        }
        return messageDTOs;
    }


    public Chat createChat(User user, User user1, ClothingItem clothingItem) {
        Chat chat = new Chat();
        chat.setRequestingUser(user);
        chat.setRequestedUser(user1);
        chat.setClothingItem(clothingItem);
        return chatRepository.save(chat);
    }

    public GetChatDTO getChat(Integer userId, Integer chatId) {
        Chat chat = chatRepository.findById(chatId).get();
        if (chat.getRequestingUser().getId() == userId ) {
            return new GetChatDTO(chat.getRequestedUser().getUsername(), chat.getRequestedUser().getId(), isOpen(chatId), chat.getRequestedUser().getProfilePicture(), chat.getClothingItem().getName());
        }
        return new GetChatDTO(chat.getRequestingUser().getUsername(), chat.getRequestingUser().getId(), isOpen(chatId), chat.getRequestingUser().getProfilePicture(), chat.getClothingItem().getName());

    }

    public Boolean isOpen(Integer chatId) {
        Chat chat = chatRepository.findById(chatId).get();
        return chat.isOpen();
    }

    public void closeChat(Integer chatId) {
        Chat chat = chatRepository.findById(chatId).get();
        chat.close();
        chatRepository.save(chat);
    }

    public void createMessage(Integer chatId, Integer userId, String message) {
        Chat chat = chatRepository.findById(chatId).get();
        Message message1 = new Message();
        message1.setChat(chat);
        message1.setMessage(message);
        message1.setUser(userService.getUserById(userId));
        messageRepository.save(message1);
    }

    public List<GetChatsDTO> getChats(Integer userId) {
        List<Chat> chats = chatRepository.findAllByRequestingUserIdOrRequestedUserId(userId, userId);
        List<GetChatsDTO> chatDTOs = new ArrayList<GetChatsDTO>();
        for (Chat chat : chats) {
            if (chat.getRequestingUser().getId() == userId) {
                chatDTOs.add(new GetChatsDTO(chat.getRequestedUser().getUsername(), chat.getRequestedUser().getId(), chat.isOpen(), chat.getRequestedUser().getProfilePicture(), getLastMessage(chat.getId()), chat.getId(), chat.getClothingItem().getName()));
            } else {
                chatDTOs.add(new GetChatsDTO(chat.getRequestingUser().getUsername(), chat.getRequestingUser().getId(), chat.isOpen(), chat.getRequestingUser().getProfilePicture(), getLastMessage(chat.getId()), chat.getId(), chat.getClothingItem().getName()));
            }
        }
        return chatDTOs;
    }

    private String getLastMessage(Integer id) {
        List<Message> messages = messageRepository.findAllByChatId(id);
        if (messages.size() == 0) {
            return "";
        }
        return messages.get(messages.size() - 1).getMessage();
    }

    public List<GetChatsDTO> getOpenChats(Integer userId) {
        List<Chat> chats = chatRepository.findAllByRequestingUserIdOrRequestedUserId(userId, userId);
        List<GetChatsDTO> chatDTOs = new ArrayList<GetChatsDTO>();
        for (Chat chat : chats) {
            if (chat.isOpen()) {
                if (chat.getRequestingUser().getId() == userId) {
                    chatDTOs.add(new GetChatsDTO(chat.getRequestedUser().getUsername(), chat.getRequestedUser().getId(), chat.isOpen(), chat.getRequestedUser().getProfilePicture(), getLastMessage(chat.getId()), chat.getId(), chat.getClothingItem().getName()));
                } else {
                    chatDTOs.add(new GetChatsDTO(chat.getRequestingUser().getUsername(), chat.getRequestingUser().getId(), chat.isOpen(), chat.getRequestingUser().getProfilePicture(), getLastMessage(chat.getId()), chat.getId(), chat.getClothingItem().getName()));
                }
            }
        }
        return chatDTOs;
    }
}
