package com.ClothesFriends.ClothesFriendsBackEnd.service.Outfit;

import com.ClothesFriends.ClothesFriendsBackEnd.model.VoteType;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.Outfit.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public Integer countLikes(Integer outfitId) {
        return voteRepository.countByOutfitIdAndVoteType(outfitId, VoteType.LIKE);
    }

    public Integer countDislikes(Integer outfitId) {
        return voteRepository.countByOutfitIdAndVoteType(outfitId, VoteType.DISLIKE);
    }
}
