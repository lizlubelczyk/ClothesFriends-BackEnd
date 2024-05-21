package com.ClothesFriends.ClothesFriendsBackEnd.service;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.CreateFriendshipDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.Friendship;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.User.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserService userService;

    public void deleteFriendship(Integer userId1, Integer userId2) {
        friendshipRepository.deleteByUser1IdAndUser2IdOrUser1IdAndUser2Id(userId1, userId2, userId2, userId1);
    }

    public void saveFriendship(CreateFriendshipDTO createFriendshipDTO) {
        Friendship friendship = new Friendship();
        friendship.setUser1(userService.getUserById(createFriendshipDTO.getUser1()));
        friendship.setUser2(userService.getUserById(createFriendshipDTO.getUser2()));
        friendshipRepository.save(friendship);
    }

    public List<Friendship> getFriendships(Integer userId) {
        return friendshipRepository.findByUser1IdOrUser2Id(userId, userId);
    }

    public Friendship getFriendship(Integer userId1, Integer userId2) {
        return friendshipRepository.findByUser1IdAndUser2IdOrUser1IdAndUser2Id(userId1, userId2, userId2, userId1);
    }

    public boolean areFriends(Integer userId1, Integer userId2) {
        return friendshipRepository.findByUser1IdAndUser2IdOrUser1IdAndUser2Id(userId1, userId2, userId2, userId1) != null;
    }

    public Integer countFriends(Integer userId) {
        return friendshipRepository.findByUser1IdOrUser2Id(userId, userId).size();
    }


}
