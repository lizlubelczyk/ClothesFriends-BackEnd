package com.ClothesFriends.ClothesFriendsBackEnd.DTO.User;

import lombok.Data;

@Data
public class CreateFriendshipDTO {
    private Integer user1;
    private Integer user2;

    public CreateFriendshipDTO(Integer user1, Integer user2) {
        this.user1 = user1;
        this.user2 = user2;
    }
}
