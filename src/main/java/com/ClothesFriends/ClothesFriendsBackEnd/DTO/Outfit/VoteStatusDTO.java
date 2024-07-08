package com.ClothesFriends.ClothesFriendsBackEnd.DTO.Outfit;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.VoteType;

public class VoteStatusDTO {
    private boolean hasVoted;
    private VoteType isLiked;

    public VoteStatusDTO(boolean hasVoted, VoteType isLiked) {
        this.hasVoted = hasVoted;
        this.isLiked = isLiked;
    }

    public boolean getHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public VoteType getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(VoteType isLiked) {
        this.isLiked = isLiked;
    }

}
