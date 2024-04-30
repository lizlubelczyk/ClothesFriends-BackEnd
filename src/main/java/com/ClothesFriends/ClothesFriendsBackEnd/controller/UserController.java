package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.model.User;
import com.ClothesFriends.ClothesFriendsBackEnd.service.UserDetailsService;
import com.ClothesFriends.ClothesFriendsBackEnd.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get the current authenticated user's details
    @GetMapping("/get/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getUser(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);

    }


    // Update the authenticated user's details
    @PutMapping("/me/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> updateCurrentUser(@PathVariable Integer userId, @RequestBody User updatedUser) {
        // Get the current user
        User user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }

        // Update the user's details
        User updated = userService.updateUser(updatedUser);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/me/{userId}/profile-picture")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> updateCurrentUserProfilePicture(@PathVariable Integer userId, @RequestParam("profilePicture") MultipartFile profilePicture) {
        // Get the current user
        User user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }

        try {
            // Upload the profile picture to Firebase Storage
            String profilePictureUrl = userService.uploadProfilePicture(profilePicture);

            // Update the user's profile picture in the database
            User updatedUser = userService.updateUserProfilePicture(user.getId(), profilePictureUrl);

            return ResponseEntity.ok(updatedUser);
        } catch (IOException e) {
            // Handle the exception, e.g. by returning an error response
            return ResponseEntity.status(500).body(null); // Internal Server Error
        }
    }

    @DeleteMapping("/me/{userId}/delete")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteCurrentUser(@PathVariable Integer userId) {
        // Get the current user
        User user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.status(404).build(); // Not found if user does not exist
        }

        // Delete the user
        userService.deleteUserById(userId);

        return ResponseEntity.noContent().build(); // No Content (204) on successful deletion
    }


}
