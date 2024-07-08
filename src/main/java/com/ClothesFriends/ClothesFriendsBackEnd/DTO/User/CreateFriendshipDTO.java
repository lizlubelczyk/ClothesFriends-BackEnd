package com.ClothesFriends.ClothesFriendsBackEnd.DTO.User;


public class CreateFriendshipDTO {
    private Integer user1;
    private Integer user2;

    public CreateFriendshipDTO(Integer user1, Integer user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public Integer getUser1() {
        return user1;
    }

    public void setUser1(Integer user1) {
        this.user1 = user1;
    }

    public Integer getUser2() {
        return user2;
    }

    public void setUser2(Integer user2) {
        this.user2 = user2;
    }
}
