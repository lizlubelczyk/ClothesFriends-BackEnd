package com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem;

import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import jakarta.persistence.*;

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




}
