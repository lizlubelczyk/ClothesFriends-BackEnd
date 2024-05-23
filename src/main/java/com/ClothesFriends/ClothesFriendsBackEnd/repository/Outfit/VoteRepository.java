package com.ClothesFriends.ClothesFriendsBackEnd.repository.Outfit;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.Outfit;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.Vote;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.model.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Integer countByOutfitIdAndVoteType(Integer outfitId, VoteType voteType);

    Vote findByOutfitAndUser(Outfit outfit, User user);
}
