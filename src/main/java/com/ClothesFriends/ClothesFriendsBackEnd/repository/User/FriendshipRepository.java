package com.ClothesFriends.ClothesFriendsBackEnd.repository.User;

import com.ClothesFriends.ClothesFriendsBackEnd.model.User.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {
    List<Friendship> findByUser1IdOrUser2Id(Integer userId, Integer userId2);
    Friendship findByUser1IdAndUser2IdOrUser1IdAndUser2Id(Integer userId1, Integer userId2, Integer userId3, Integer userId4);
    void deleteByUser1IdAndUser2IdOrUser1IdAndUser2Id(Integer userId1, Integer userId2, Integer userId3, Integer userId4);

}
