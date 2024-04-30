package com.ClothesFriends.ClothesFriendsBackEnd.repository;

import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingItemRepository extends JpaRepository<ClothingItem, Long> {
}
