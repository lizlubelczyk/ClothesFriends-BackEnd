package com.ClothesFriends.ClothesFriendsBackEnd.service.Outfit;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.Outfit;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.OutfitComment;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.Outfit.OutfitCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutfitCommentService {

    @Autowired
    private OutfitCommentRepository outfitCommentRepository;

    public void commentOutfit(Outfit outfit, User user, String comment) {
        OutfitComment outfitComment = new OutfitComment();
        outfitComment.setOutfit(outfit);
        outfitComment.setUser(user);
        outfitComment.setComment(comment);
        outfitCommentRepository.save(outfitComment);
    }

    public List<OutfitComment> getComments(Integer outfitId) {
        return outfitCommentRepository.findAllByOutfitId(outfitId);
    }

    public Integer countComments(Integer outfitId) {
        return outfitCommentRepository.findAllByOutfitId(outfitId).size();
    }

    public OutfitComment getCommentById(Integer commentId) {
        return outfitCommentRepository.findById(commentId).get();
    }

    public void deleteComment(OutfitComment comment) {
        outfitCommentRepository.delete(comment);
    }
}
