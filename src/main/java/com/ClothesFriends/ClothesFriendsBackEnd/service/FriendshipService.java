package com.ClothesFriends.ClothesFriendsBackEnd.service;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.GetNotificationDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.User.CreateFriendshipDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.Friendship;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.User.FriendshipRepository;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    public void deleteFriendship(Integer userId1, Integer userId2) {
        friendshipRepository.deleteByUser1IdAndUser2IdOrUser1IdAndUser2Id(userId1, userId2, userId2, userId1);
    }

    public Friendship saveFriendship(CreateFriendshipDTO createFriendshipDTO) {
        Friendship friendship = new Friendship();
        friendship.setUser1(userRepository.getUserById(createFriendshipDTO.getUser1()));
        friendship.setUser2(userRepository.getUserById(createFriendshipDTO.getUser2()));
        friendshipRepository.save(friendship);
        return friendship;
    }

    public List<Friendship> getFriendships(Integer userId) {
        return friendshipRepository.findByUser1IdOrUser2Id(userId, userId);
    }

    public List<User> getFriends(Integer userId) {
        List<Friendship> friendships = getFriendships(userId);
        List<User> friends = new ArrayList<>();

        for (Friendship friendship : friendships) {
            if (friendship.getUser1().getId().equals(userId)) {
                friends.add(friendship.getUser2());
            } else {
                friends.add(friendship.getUser1());
            }
        }

        return friends;
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
