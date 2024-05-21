package com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit;

import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "outfit_id", nullable = false)
    @JsonBackReference
    private Outfit outfit;


}
