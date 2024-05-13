package com.ClothesFriends.ClothesFriendsBackEnd.service;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.CreateClothingItemDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.ClothingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClothingItemService {
    @Autowired
    private ClothingItemRepository clothingItemRepository;



    public ClothingItem saveClothingItem(CreateClothingItemDTO clothingItem, User user) throws IOException {
        ClothingItem newClothingItem = new ClothingItem();
        newClothingItem.setName(clothingItem.getName());
        newClothingItem.setDescription(clothingItem.getDescription());
        newClothingItem.setSubcategory(clothingItem.getSubcategory());
        newClothingItem.setAvailable(clothingItem.isAvailable());
        String imagePath = saveImage(clothingItem.getImage(), user.getId());
        newClothingItem.setImage(imagePath);
        return clothingItemRepository.save(newClothingItem);
    }

    private String saveImage(byte[] image, Integer userId) throws IOException {
        // Generate a unique filename for the image
        String filename = UUID.randomUUID().toString() + ".jpg";

        // Define the path to the user's clothing item directory
        Path clothingItemDirectory = Paths.get("C:/Users/lizlu/OneDrive/Documentos/3ero/LabI/ClothesFriends-FrontEnd/public/images/" + userId + "/clothingitems");

        // If the directory does not exist, create it
        if (!Files.exists(clothingItemDirectory)) {
            Files.createDirectories(clothingItemDirectory);
        }

        // Define the path to the image file
        Path imagePath = clothingItemDirectory.resolve(filename);

        // Convert byte array back to an InputStream
        InputStream inputStream = new ByteArrayInputStream(image);

        // Save the image file
        Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);

        // Return the path of the saved image file from the 'images' directory forward
        return "/images/" + userId + "/clothingitems/" + filename;
    }

    public List<ClothingItem> getAllClothingItems() {
        return clothingItemRepository.findAll();
    }

    public Optional<ClothingItem> getClothingItemById(Long id) {
        return clothingItemRepository.findById(id);
    }
}