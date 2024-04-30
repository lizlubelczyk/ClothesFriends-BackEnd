package com.ClothesFriends.ClothesFriendsBackEnd.service;

import com.ClothesFriends.ClothesFriendsBackEnd.model.User;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.UserRepository;
import com.google.cloud.storage.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with id '" + id + "' not found"));
    }

    public User updateUser(User user) {
        return userRepository.save(user); // Saves and returns the updated user
    }

    public User updateUserProfilePicture(Integer userId, String profilePictureUrl) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with id '" + userId + "' not found"));

        user.setProfilePicture(profilePictureUrl);
        return userRepository.save(user);
    }

    public String uploadProfilePicture(MultipartFile profilePicture) throws IOException {
        // Get the default storage from the Firebase app
        Storage storage = StorageOptions.getDefaultInstance().getService();

        // Generate a unique filename for the profile picture
        String filename = UUID.randomUUID().toString() + "-" + profilePicture.getOriginalFilename();

        // Create a blobId with the filename
        BlobId blobId = BlobId.of("clothesfriends-8b835", "profile-pictures/" + filename);

        // Create a blobInfo
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(profilePicture.getContentType()).build();

        // Upload the profile picture to Firebase Cloud Storage
        Blob blob = storage.create(blobInfo, profilePicture.getBytes());

        // Return the download URL of the profile picture
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                blob.getBucket(), URLEncoder.encode(blob.getName(), StandardCharsets.UTF_8.name()));
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }


    // Additional user-related logic can go here
}
