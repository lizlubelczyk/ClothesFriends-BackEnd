package com.ClothesFriends.ClothesFriendsBackEnd.repository.Outfit;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public Comment findByOutfitId(Integer outfitId);
}
