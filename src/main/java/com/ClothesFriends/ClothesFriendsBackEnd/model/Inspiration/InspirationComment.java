package com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration;

import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "inspirationComment")
public class InspirationComment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "inspiration_id", nullable = false)
    private Inspiration inspiration;

    public void setInspiration(Inspiration inspiration) {
        this.inspiration = inspiration;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCommentId() {
        return id;
    }
}
