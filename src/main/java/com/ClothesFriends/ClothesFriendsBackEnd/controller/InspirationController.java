package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration.CreateInspirationDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration.GetInspirationDTO;
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
    public ResponseEntity<Inspiration> createInspiration(@PathVariable Integer userId, @RequestParam("image") MultipartFile image, @RequestParam("description") String description) throws IOException {
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
    public ResponseEntity<GetInspirationDTO> getInspiration(@PathVariable Integer inspirationId) {
        Optional<Inspiration> inspiration = inspirationService.getInspirationById(inspirationId);

        String image = inspiration.get().getImage();
        String description = inspiration.get().getDescription();
            String username = userService.getUserById(inspiration.get().getUser().getId()).getUsername();
        Integer likes = inspirationService.countLikes(inspirationId);


        GetInspirationDTO getInspirationDTO = new GetInspirationDTO(image, likes, username, description);
        return ResponseEntity.ok(getInspirationDTO);
    }

    @GetMapping("/get/{userId}/all")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Iterable<GetInspirationDTO>> getAllUserInspirations(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        Iterable<Inspiration> inspirations = inspirationService.getInspirationsByUserId(user.getId());
        Iterable<GetInspirationDTO> getInspirationDTOs = new ArrayList<>();
        for (Inspiration inspiration : inspirations) {
            String image = inspiration.getImage();
            String description = inspiration.getDescription();
            String username = userService.getUserById(inspiration.getUser().getId()).getUsername();
            Integer likes = inspirationService.countLikes(inspiration.getId());
            GetInspirationDTO getInspirationDTO = new GetInspirationDTO(image, likes, username, description);
            ((ArrayList<GetInspirationDTO>) getInspirationDTOs).add(getInspirationDTO);

        }
        return ResponseEntity.ok(getInspirationDTOs);
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
        inspirationService.deleteInspirationById(inspirationId);
        return ResponseEntity.ok().build();
    }



}
