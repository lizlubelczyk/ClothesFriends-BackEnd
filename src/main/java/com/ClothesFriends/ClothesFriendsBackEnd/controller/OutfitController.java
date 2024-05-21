package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.CreateOutfitDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.GetMyOutfitDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.Outfit;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.service.Outfit.OutfitService;
import com.ClothesFriends.ClothesFriendsBackEnd.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/api/outfit")
public class OutfitController {

    @Autowired
    private
    OutfitService outfitService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/create")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Outfit> createOutfit(@PathVariable Integer userId,
                                               @RequestParam("description") String description,
                                               @RequestParam("image") MultipartFile image
                                               ) throws IOException, IOException {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }

        CreateOutfitDTO outfit = new CreateOutfitDTO(image.getBytes(), description, userId);
        Outfit newOutfit = outfitService.saveOutfit(outfit, user);
        return ResponseEntity.ok(newOutfit);
    }

    // OutfitController.java
    @GetMapping("/{userId}/getLatest")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetMyOutfitDTO> getLatestOutfit(@PathVariable Integer userId){
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }
        GetMyOutfitDTO myOutfit = outfitService.getLatestOutfitByUserId(userId);

        // Check if outfit is empty and return appropriate response
        if (myOutfit.getOutfitId() == null) {
            return ResponseEntity.status(404).body(null); // No outfit found
        }

        return ResponseEntity.ok(myOutfit);
    }


    @GetMapping("/{userId}/hasOutfit")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Boolean> hasOutfit(@PathVariable Integer userId){
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }

        // Check if the user has an outfit and return the result
        boolean hasOutfit = outfitService.hasOutfit(userId);
        return ResponseEntity.ok(hasOutfit);
    }

    @DeleteMapping("/{outfitId}/delete")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> deleteOutfit(@PathVariable Integer outfitId){
        Outfit outfit = outfitService.getOutfitById(outfitId);
        if (outfit == null) {
            return ResponseEntity.status(404).build(); // Not found if outfit does not exist
        }

        outfitService.deleteOutfit(outfit);
        return ResponseEntity.noContent().build(); // No Content (204) on successful deletion
    }

}
