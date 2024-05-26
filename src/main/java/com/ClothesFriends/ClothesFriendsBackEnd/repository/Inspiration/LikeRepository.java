package com.ClothesFriends.ClothesFriendsBackEnd.repository.Inspiration;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Inspiration;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Like;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
    public Like findByUserId(Integer userId);

    public List<Like> findAllByInspirationId(Integer inspirationId);
    public List<Like> findAllByUserId(Integer userId);

    Integer countByInspirationId(Integer inspirationId);

    void deleteByInspirationAndUser(Inspiration inspiration, User user);

    Boolean existsByInspirationAndUser(Inspiration inspiration, User user);
}
