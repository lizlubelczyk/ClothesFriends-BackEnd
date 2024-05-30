package com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem;

import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import jakarta.persistence.*;

import javax.sql.RowSet;

@Entity
@Table(name = "borrow_request")
public class BorrowRequest {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "clothing_item_id", nullable = false)
    private ClothingItem clothingItem;

    @Column(name = "status", nullable = false)
    private RequestStatus status = RequestStatus.PENDING;


    public void setUser(User user) {
        this.user = user;
    }

    public void setClothingItem(ClothingItem clothingItem) {
        this.clothingItem = clothingItem;
    }

    public Integer getId() {
        return this.id;
    }

    public ClothingItem getClothingItem() {
        return this.clothingItem;
    }

    public User getUser() {
        return this.user;
    }

    public void setRequestStatus(RequestStatus status) {
        this.status = status;
    }

    public RequestStatus getRequestStatus() {
        return this.status;
    }
}

