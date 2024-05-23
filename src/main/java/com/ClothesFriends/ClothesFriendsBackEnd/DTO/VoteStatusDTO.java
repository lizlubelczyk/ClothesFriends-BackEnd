package com.ClothesFriends.ClothesFriendsBackEnd.DTO;

import com.ClothesFriends.ClothesFriendsBackEnd.model.VoteType;
import lombok.Data;

@Data
public class VoteStatusDTO {
    private boolean hasVoted;
    private VoteType isLiked;

    public VoteStatusDTO(boolean hasVoted, VoteType isLiked) {
        this.hasVoted = hasVoted;
        this.isLiked = isLiked;
    }

}
