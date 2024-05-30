package com.ClothesFriends.ClothesFriendsBackEnd.service;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.GetNotificationDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.User.*;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Inspiration;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.Friendship;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.User.UserRepository;
import com.ClothesFriends.ClothesFriendsBackEnd.service.Inspiration.InspirationService;
import com.ClothesFriends.ClothesFriendsBackEnd.service.Inspiration.LikeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private NotificationService notificationService;

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
        return "/images/" + userId + "/profilepicture/" + filename;
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

    public Friendship befriendUsers(CreateFriendshipDTO friendshipDTO) {
        User user = userRepository.findById(friendshipDTO.getUser1())
                .orElseThrow(() -> new UsernameNotFoundException("User with id '" + friendshipDTO.getUser1() + "' not found"));
        User friend = userRepository.findById(friendshipDTO.getUser2())
                .orElseThrow(() -> new UsernameNotFoundException("User with id '" + friendshipDTO.getUser2() + "' not found"));

        notificationService.createBefriendNotification(user, friend);

        return friendshipService.saveFriendship(friendshipDTO);
    }

    public GetOtherUserDTO getOtherUserProfile(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with id '" + userId + "' not found"));

        return new GetOtherUserDTO(user.getProfilePicture(), user.getUsername(), user.getFullName());
    }

    public Integer getUserId(String userName) {
        User user = userRepository.findByUsername(userName).get();
        return user.getId();
    }

    public Integer getUserIdByUsername(String userName) {
        User user = userRepository.findByUsername(userName).get();
        if (user != null) {
            return user.getId();
        } else {
            return null;
        }
    }



    public String getFullName(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with id '" + userId + "' not found"));

        return user.getFullName();

    }

    public Boolean isFriend(Integer userId, Integer friendId) {
        return friendshipService.areFriends(userId, friendId);
    }

    @Transactional
    public void deleteFriendship(Integer userId, Integer friendId) {
        friendshipService.deleteFriendship(userId, friendId);
        User user = userRepository.findById(userId).get();
        User friend = userRepository.findById(friendId).get();
        notificationService.createUnfriendNotification(user, friend);
    }

    public List<GetAllFriendsDTO> getFriends(Integer userId) {
        List<User> friends = friendshipService.getFriends(userId);
        List<GetAllFriendsDTO> friendsDTO = new ArrayList<>();
        for (User friend : friends) {
            friendsDTO.add(new GetAllFriendsDTO(friend.getProfilePicture(), friend.getUsername(), friend.getId()));
        }
        return friendsDTO;
    }

    public Integer countFriends(Integer userId) {
        return friendshipService.countFriends(userId);
    }

    public List<GetNotificationDTO> getNotifications(Integer userId) {
        return notificationService.getNotifications(userId);
    }

    public void markNotificationAsSeen(Integer notificationId) {
        notificationService.markNotificationAsSeen(notificationId);
    }

    public List<GetNotificationDTO> getUnreadNotifications(Integer userId) {
        return notificationService.getUnreadNotifications(userId);
    }


    // Additional user-related logic can go here
}
