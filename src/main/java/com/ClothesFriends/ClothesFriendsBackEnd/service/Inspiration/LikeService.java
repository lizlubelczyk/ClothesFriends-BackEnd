package com.ClothesFriends.ClothesFriendsBackEnd.service.Inspiration;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Inspiration;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Like;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.Inspiration.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public Integer countLikes(Integer inspirationId) {
        return likeRepository.countByInspirationId(inspirationId);
    }

    public List<Like> getLikesByUserId(Integer userId) {
        return likeRepository.findAllByUserId(userId);
    }

    public List<Inspiration> getLikedInspirationsByUserId(Integer userId) {
        return likeRepository.findAllByUserId(userId).stream().map(like -> like.getInspiration()).toList();
    }

    public Like likeInspiration(Inspiration inspiration, User user) {
        Like like = new Like();
        like.setInspiration(inspiration);
        like.setUser(user);
        likeRepository.save(like);
        return like;
    }

    public void unlikeInspiration(Inspiration inspiration, User user) {
        likeRepository.deleteByInspirationAndUser(inspiration, user);
    }
}
