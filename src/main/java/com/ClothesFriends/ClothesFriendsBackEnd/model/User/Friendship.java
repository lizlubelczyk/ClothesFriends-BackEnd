package com.ClothesFriends.ClothesFriendsBackEnd.model.User;

import jakarta.persistence.*;

@Entity
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private User user2;

    public Integer getId() {
        return id;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public User getUser2() {
        return user2;
    }
}
