package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.GetNotificationDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.User.*;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.Friendship;
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
    @GetMapping("/get/{userId}/profile")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Integer userId) {
        UserProfileDTO user = userService.getUserProfile(userId);
        return ResponseEntity.ok(user);

    }

    @GetMapping("/get/{userId}/edit")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EditUserDTO> getUserEdit(@PathVariable Integer userId) {
        EditUserDTO user = userService.getUserEdit(userId);
        return ResponseEntity.ok(user);

    }

    @GetMapping("/search/{userName}/id")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Integer> searchUserIds(@PathVariable String userName) {
        Integer userId = userService.getUserIdByUsername(userName);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }
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
    public ResponseEntity<User> updateCurrentUserProfilePicture(
            @PathVariable Integer userId,
            @RequestParam("profilePicture") MultipartFile profilePicture) {

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

    @PostMapping("/me/{userId}/befriend/{friendId}")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Friendship> befriendUser(@PathVariable Integer userId, @PathVariable Integer friendId) {
        // Get the current user
        User user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.status(404).body(null); // Not found if user does not exist
        }

        // Get the friend
        User friend = userService.getUserById(friendId);

        if (friend == null) {
            return ResponseEntity.status(404).body(null); // Not found if friend does not exist
        }

        CreateFriendshipDTO friendshipDTO = new CreateFriendshipDTO(userId, friendId);
        // Befriend the users
        Friendship friendship = userService.befriendUsers(friendshipDTO);

        return ResponseEntity.ok(friendship);
    }

    @GetMapping("/other/{userId}/profile")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetOtherUserDTO> getOtherUserProfile(@PathVariable Integer userId) {
        GetOtherUserDTO user = userService.getOtherUserProfile(userId);
        return ResponseEntity.ok(user);

    }

    @GetMapping("/get/{userId}/fullname")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> getFullName(@PathVariable Integer userId) {
        String fullName = userService.getFullName(userId);
        return ResponseEntity.ok(fullName);
    }

    @GetMapping("/{userId}/{friendId}/isFriend")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Boolean> isFriend(@PathVariable Integer userId, @PathVariable Integer friendId) {
        Boolean isFriend = userService.isFriend(userId, friendId);
        return ResponseEntity.ok(isFriend);
    }

    @DeleteMapping("/{userId}/{friendId}/deleteFriendship")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> unfriend(@PathVariable Integer userId, @PathVariable Integer friendId) {
        userService.deleteFriendship(userId, friendId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{userId}/friends")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetAllFriendsDTO>> getFriends(@PathVariable Integer userId) {
        List<GetAllFriendsDTO> friends = userService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    @GetMapping("/get/{userId}/friendCount")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Integer> countFriends(@PathVariable Integer userId) {
        Integer count = userService.countFriends(userId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/me/{userId}/notifications")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetNotificationDTO>> getNotifications(@PathVariable Integer userId) {
        List<GetNotificationDTO> notifications = userService.getNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/me/{userId}/notifications/unread")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetNotificationDTO>> getUnreadNotifications(@PathVariable Integer userId) {
        List<GetNotificationDTO> notifications = userService.getUnreadNotifications(userId);
        return ResponseEntity.ok(notifications);
    }
    @PostMapping("/notification/{notificationId}/markAsSeen")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> markNotificationAsSeen(@PathVariable Integer notificationId) {
        userService.markNotificationAsSeen(notificationId);
        return ResponseEntity.ok().build();
    }



}