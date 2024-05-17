package com.ClothesFriends.ClothesFriendsBackEnd.service;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.EditUserDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.UpdateUserRequestDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.UserProfileDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Inspiration;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.User.UserRepository;
import com.ClothesFriends.ClothesFriendsBackEnd.service.Inspiration.InspirationService;
import com.ClothesFriends.ClothesFriendsBackEnd.service.Inspiration.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private InspirationService inspirationService;

    @Autowired
    private LikeService likeService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with id '" + id + "' not found"));
    }

    public UserProfileDTO getUserProfile(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with id '" + userId + "' not found"));

        return new UserProfileDTO(user.getUsername(), user.getProfilePicture(), user.getFullName());
    }

    public EditUserDTO getUserEdit(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with id '" + userId + "' not found"));

        return new EditUserDTO(user.getUsername(), user.getFullName(), user.getEmail(), user.getWhatsappLink(), user.getProfilePicture());
    }

    public User updateUser(UpdateUserRequestDTO updatedUser, Integer userId){
        // Find the existing user by ID
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Apply updates to the fields that are intended to change
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getFullName() != null) {
            existingUser.setFullName(updatedUser.getFullName());
        }
        if (updatedUser.getUsername() != null) {
            existingUser.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getWhatsappLink() != null) {
            existingUser.setWhatsappLink(updatedUser.getWhatsappLink());
        }

        // Do not update the password field, even if it's provided
        // Save the changes and return the updated user
        return userRepository.save(existingUser);
    }


    public User updateUserProfilePicture(Integer userId, String profilePictureUrl) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with id '" + userId + "' not found"));

        user.setProfilePicture(profilePictureUrl);
        return userRepository.save(user);
    }

    public String uploadProfilePicture(MultipartFile profilePicture, Integer userId) throws IOException {
        // Generate a unique filename for the profile picture
        String filename = UUID.randomUUID().toString() + "-" + profilePicture.getOriginalFilename();

        // Define the path to the user's profile picture directory
        Path profilePictureDirectory = Paths.get("C:/Users/lizlu/OneDrive/Documentos/3ero/LabI/ClothesFriends-FrontEnd/public/images/" + userId + "/profilepicture");

        // If the directory does not exist, create it
        if (!Files.exists(profilePictureDirectory)) {
            Files.createDirectories(profilePictureDirectory);
        }

        // Define the path to the profile picture file
        Path profilePicturePath = profilePictureDirectory.resolve(filename);

        // Save the profile picture file
        Files.copy(profilePicture.getInputStream(), profilePicturePath, StandardCopyOption.REPLACE_EXISTING);

        // Return the path to the profile picture file
        return profilePicturePath.toString();
    }
    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

   /* public List<Inspiration> getInspirationsByUserId(Integer userId) {
        return inspirationService.getInspirationsByUserId(userId);
    }*/

    public List<Inspiration> getLikedInspirationsByUserId(Integer userId) {
        return likeService.getLikedInspirationsByUserId(userId);
    }




    // Additional user-related logic can go here
}
