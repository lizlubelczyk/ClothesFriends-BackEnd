package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.ClothingItem.CreateClothingItemDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.ClothingItem.GetBorrowRequestDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.ClothingItem.GetClothingItemBySubcategoryDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.ClothingItem.GetClothingItemDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem.ClothingItem;
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

    @GetMapping("/getAll/{userId}/{subcategory}")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetClothingItemBySubcategoryDTO>> getAllClothingItemsByCategory(@PathVariable Integer userId, @PathVariable String subcategory) {
        List<GetClothingItemBySubcategoryDTO> clothingItems = clothingItemService.getAllClothingItemsBySubcategory(userId, subcategory);
        return ResponseEntity.ok(clothingItems);
    }

    @GetMapping("/get/{clothingItemId}")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetClothingItemDTO> getClothingItem(@PathVariable Integer clothingItemId) {
        GetClothingItemDTO clothingItem = clothingItemService.getClothingItem(clothingItemId);
        if (clothingItem.getName() == null) {
            return ResponseEntity.status(404).body(null); // Not found if clothing item does not exist
        }
        return ResponseEntity.ok(clothingItem);
    }

    @PostMapping("/changeAvailable/{clothingItemId}")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ClothingItem> changeAvailable(@PathVariable Integer clothingItemId) {
        ClothingItem clothingItem = clothingItemService.changeAvailable(clothingItemId);
        return ResponseEntity.ok(clothingItem);
    }

    @DeleteMapping("/{clothingItemId}/delete")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> deleteClothingItem(@PathVariable Integer clothingItemId){
        ClothingItem clothingItem = clothingItemService.getClothingItemById(clothingItemId).get();
        if (clothingItem == null) {
            return ResponseEntity.status(404).build(); // Not found if clothing item does not exist
        }
        clothingItemService.deleteClothingItem(clothingItemId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{clothingItemId}/borrowrequest/{userId}")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ClothingItem> borrowRequest(@PathVariable Integer clothingItemId, @PathVariable Integer userId) {
        ClothingItem clothingItem = clothingItemService.getClothingItemById(clothingItemId).get();
        if (clothingItem == null) {
            return ResponseEntity.status(404).body(null); // Not found if clothing item does not exist
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }
        clothingItemService.createBorrowRequest(clothingItem, user);
        return ResponseEntity.ok(clothingItem);
    }

    @GetMapping("/{userId}/{clothingItemId}/wasRequested")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Boolean> wasRequested(@PathVariable Integer userId, @PathVariable Integer clothingItemId) {
        ClothingItem clothingItem = clothingItemService.getClothingItemById(clothingItemId).get();
        if (clothingItem == null) {
            return ResponseEntity.status(404).body(null); // Not found if clothing item does not exist
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }
        return ResponseEntity.ok(clothingItemService.wasRequested(clothingItem, user));
    }

    @GetMapping("/{requestId}/getBorrowRequest")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetBorrowRequestDTO> getBorrowRequest(@PathVariable Integer requestId) {
        GetBorrowRequestDTO clothingItem = clothingItemService.getBorrowRequest(requestId);
        return ResponseEntity.ok(clothingItem);
    }

    @PostMapping("/{borrowRequestId}/acceptBorrowRequest")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> acceptBorrowRequest(@PathVariable Integer borrowRequestId) {
        clothingItemService.acceptBorrowRequest(borrowRequestId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{borrowRequestId}/rejectBorrowRequest")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> rejectBorrowRequest(@PathVariable Integer borrowRequestId) {
        clothingItemService.rejectBorrowRequest(borrowRequestId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{borrowRequestId}/wasHandled")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Boolean> wasHandled(@PathVariable Integer borrowRequestId) {
        return ResponseEntity.ok(clothingItemService.wasHandled(borrowRequestId));
    }

}
