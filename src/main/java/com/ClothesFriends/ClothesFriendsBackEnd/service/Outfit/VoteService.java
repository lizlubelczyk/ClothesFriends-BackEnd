package com.ClothesFriends.ClothesFriendsBackEnd.service.Outfit;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.Outfit;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.Vote;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.VoteType;
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

    public Vote getUsersVote(Outfit outfit, User user) {
        return voteRepository.findByOutfitAndUser(outfit, user);
    }

    public void saveVote(Vote vote) {
        voteRepository.save(vote);
    }

    public void deleteVote(Vote vote) {
        voteRepository.delete(vote);
    }
}
