package com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit;

import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "outfitComment")
public class OutfitComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "outfit_id", nullable = false)
    @JsonBackReference
    private Outfit outfit;


    public User getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public void setOutfit(Outfit outfit) {
        this.outfit = outfit;
    }

    public void setUser(User user) {
        this.user = user;

    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }
}
