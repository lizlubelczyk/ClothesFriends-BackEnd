package com.ClothesFriends.ClothesFriendsBackEnd.model;

import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "clothing_item")
public class ClothingItem {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Lob
    @Column(name = "image", nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name= "available")
    private boolean available;

    @Column(name = "subcategory", nullable = false)
    private String subcategory;
    public ClothingItem() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
