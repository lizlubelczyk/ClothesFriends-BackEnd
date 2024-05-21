package com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration;

import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "`like`")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "inspiration_id", nullable = false)
    private Inspiration inspiration;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setInspiration(Inspiration inspiration) {
        this.inspiration = inspiration;
    }

    public Inspiration getInspiration() {
        return inspiration;
    }

    public Integer getId() {
        return id;
    }


}
