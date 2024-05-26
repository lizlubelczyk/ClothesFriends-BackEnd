package com.ClothesFriends.ClothesFriendsBackEnd.repository.Outfit;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.OutfitComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutfitCommentRepository extends JpaRepository<OutfitComment, Integer> {
    public OutfitComment findByOutfitId(Integer outfitId);

    List<OutfitComment> findAllByOutfitId(Integer outfitId);
}
