package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration.CreateInspirationDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration.GetAllMyInspirationDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration.GetMyInspirationDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration.GetMyInspirationDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Inspiration;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Like;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.service.Inspiration.InspirationService;
import com.ClothesFriends.ClothesFriendsBackEnd.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inspiration")
public class InspirationController {
    @Autowired
    private InspirationService inspirationService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/create")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Inspiration> createInspiration(@PathVariable Integer userId,
                                                         @RequestParam("image") MultipartFile image,
                                                         @RequestParam("description") String description
                                                         ) throws IOException {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }
        CreateInspirationDTO inspiration = new CreateInspirationDTO(image.getBytes(), description, userId);
        Inspiration newInspiration = inspirationService.saveInspiration(inspiration, user);
        return ResponseEntity.ok(newInspiration);
    }

    @GetMapping("/get/{inspirationId}")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetMyInspirationDTO> getInspiration(@PathVariable Integer inspirationId) {
        GetMyInspirationDTO inspiration = inspirationService.getInspiration(inspirationId);

        if(inspiration == null) {
            return ResponseEntity.status(404).body(null); // Not found if inspiration does not exist
        }
        return ResponseEntity.ok(inspiration);
    }

    @GetMapping("/get/{userId}/all")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetAllMyInspirationDTO>> getAllUserInspirations(@PathVariable Integer userId) {
        List<GetAllMyInspirationDTO> inspirations = inspirationService.getInspirationsByUserId(userId);
        return ResponseEntity.ok(inspirations);
    }

    @PostMapping("/post/{inspirationId}/like")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Like> likeInspiration(@PathVariable Integer inspirationId, @RequestBody Integer userId) {
        Inspiration inspiration = inspirationService.getInspirationById(inspirationId).get();
        User user = userService.getUserById(userId);

        Like like = inspirationService.likeInspiration(inspiration, user);
        return ResponseEntity.ok(like);
    }

    @DeleteMapping("/delete/{inspirationId}/like")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> unlikeInspiration(@PathVariable Integer inspirationId, @RequestBody Integer userId) {
        Inspiration inspiration = inspirationService.getInspirationById(inspirationId).get();
        User user = userService.getUserById(userId);

        inspirationService.unlikeInspiration(inspiration, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{inspirationId}")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> deleteInspiration(@PathVariable Integer inspirationId) {
        Inspiration inspiration = inspirationService.getInspirationById(inspirationId).get();
        if(inspiration == null) {
            return ResponseEntity.status(404).body(null); // Not found if inspiration does not exist
        }
        inspirationService.deleteInspirationById(inspirationId);
        return ResponseEntity.noContent().build();
    }



}
