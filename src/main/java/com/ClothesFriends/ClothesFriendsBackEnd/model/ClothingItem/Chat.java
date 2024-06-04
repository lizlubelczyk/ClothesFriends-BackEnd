package com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem;

import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "requesting_user_id", nullable = false)
    private User requestingUser;

    @ManyToOne
    @JoinColumn(name = "requested_user_id", nullable = false)
    private User requestedUser;

    @Column
    private ChatState chatState = ChatState.OPEN;

    @ManyToOne
    @JoinColumn(name = "clothing_item_id", nullable = false)
    private ClothingItem clothingItem;


    public void setRequestingUser(User user) {
        this.requestingUser = user;
    }

    public void setRequestedUser(User user) {
        this.requestedUser = user;
    }

    public Integer getId() {
        return id;
    }

    public User getRequestingUser() {
        return requestingUser;
    }

    public User getRequestedUser() {
        return requestedUser;
    }

    public Boolean isOpen() {
        return chatState == ChatState.OPEN;
    }

    public void close() {
        chatState = ChatState.CLOSED;
    }

    public void setClothingItem(ClothingItem clothingItem) {
        this.clothingItem = clothingItem;
    }

    public ClothingItem getClothingItem() {
        return clothingItem;
    }
}
