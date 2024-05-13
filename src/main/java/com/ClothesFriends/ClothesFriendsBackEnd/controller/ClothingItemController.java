package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.CreateClothingItemDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.service.ClothingItemService;
import com.ClothesFriends.ClothesFriendsBackEnd.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/clothingItem")
public class ClothingItemController {

    @Autowired
    private ClothingItemService clothingItemService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/create")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ClothingItem> createClothingItem(
            @PathVariable Integer userId,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image,
            @RequestParam("subcategory") String subcategory,
            @RequestParam("available") boolean available
    ) throws IOException {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }
        CreateClothingItemDTO clothingItemDTO = new CreateClothingItemDTO(name, description, image.getBytes(), subcategory, available, userId);
        ClothingItem newClothingItem = clothingItemService.saveClothingItem(clothingItemDTO, user);
        return ResponseEntity.ok(newClothingItem);
    }

    @GetMapping("/image/show")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public String show(Model map){
        List<ClothingItem> clothingItems = clothingItemService.getAllClothingItems();
        map.addAttribute("clothingItems", clothingItems);
        return "clothingItems";
    }

}
