package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.UpdateUserRequestDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Inspiration;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.service.Inspiration.InspirationService;
import com.ClothesFriends.ClothesFriendsBackEnd.service.Inspiration.LikeService;
import com.ClothesFriends.ClothesFriendsBackEnd.service.TokenService;
import com.ClothesFriends.ClothesFriendsBackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    private final InspirationService inspirationService;

    @Autowired
    private final LikeService likeService;

    public UserController(UserService userService, TokenService tokenService, InspirationService inspirationService, LikeService likeService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.inspirationService = inspirationService;
        this.likeService = likeService;
    }

    // Get the current authenticated user's details
    @GetMapping("/get/{userId}")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")

    public ResponseEntity<User> getUser(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);

    }


    // Update the authenticated user's details
    @PostMapping("/me/update/{userId}")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<User> updateCurrentUser(@PathVariable Integer userId, @RequestBody UpdateUserRequestDTO updatedUser) {
        // Get the current user
        User user = userService.getUserById(userId);

        System.out.println(user.getUsername());

        if (user == null) {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }

        // Update the user's details
        User updated = userService.updateUser(updatedUser, userId);
        return ResponseEntity.ok(updated);

    }

    @PostMapping("/me/{userId}/profile-picture")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<User> updateCurrentUserProfilePicture(@PathVariable Integer userId, @RequestParam("profilePicture") MultipartFile profilePicture) {
        // Get the current user
        User user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }

        try {
            // Save the profile picture to the user's directory
            String profilePicturePath = userService.uploadProfilePicture(profilePicture, userId);

            // Update the user's profile picture in the database
            User updatedUser = userService.updateUserProfilePicture(user.getId(), profilePicturePath);

            return ResponseEntity.ok(updatedUser);
        } catch (IOException e) {
            // Handle the exception, e.g. by returning an error response
            return ResponseEntity.status(500).body(null); // Internal Server Error
        }
    }

    @DeleteMapping("/me/delete/{userId}")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")

    public ResponseEntity<Void> deleteCurrentUser(@PathVariable Integer userId) {
        // Get the current user
        User user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.status(404).build(); // Not found if user does not exist
        }

        // Delete the user
        userService.deleteUser(user);

        return ResponseEntity.noContent().build(); // No Content (204) on successful deletion
    }

    /*@GetMapping("/me/{userId}/inspirations")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Inspiration>> getMyInspirations(@PathVariable Integer userId) {
        // Get the current user
        User user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }

        // Get the user's inspirations
        List<Inspiration> inspirations = userService.getInspirationsByUserId(userId);

        return ResponseEntity.ok(inspirations);
    }
*/



}
