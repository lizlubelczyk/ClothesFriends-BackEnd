package com.ClothesFriends.ClothesFriendsBackEnd.repository;

import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem.BorrowRequest;
import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem.ClothingItem;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, Integer> {
    Boolean existsByClothingItemAndUser(ClothingItem clothingItem, User user);

    BorrowRequest getBorrowRequestByClothingItemAndUser(ClothingItem clothingItem, User user);
}

