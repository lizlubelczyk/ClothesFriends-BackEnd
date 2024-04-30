package com.ClothesFriends.ClothesFriendsBackEnd.service;

import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.ClothingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClothingItemService {
    @Autowired
    private ClothingItemRepository clothingItemRepository;

    public void saveClothingItem(ClothingItem clothingItem) {
        clothingItemRepository.save(clothingItem);
    }

    public List<ClothingItem> getAllClothingItems() {
        return clothingItemRepository.findAll();
    }

    public Optional<ClothingItem> getClothingItemById(Long id) {
        return clothingItemRepository.findById(id);
    }
}