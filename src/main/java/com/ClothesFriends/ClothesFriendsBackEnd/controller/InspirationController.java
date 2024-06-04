package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration.*;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Inspiration;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Like;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.service.FriendshipService;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inspiration")
public class InspirationController {
    @Autowired
    private InspirationService inspirationService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private FriendshipService friendshipService;

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

    @PostMapping("/post/{inspirationId}/like/{userId}")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Like> likeInspiration(@PathVariable Integer inspirationId, @PathVariable Integer userId) {
        Inspiration inspiration = inspirationService.getInspirationById(inspirationId).orElse(null);
        User user = userService.getUserById(userId);

        if (inspiration == null || user == null) {
            return ResponseEntity.notFound().build();
        }

        Like like = inspirationService.likeInspiration(inspiration, user);
        return ResponseEntity.ok(like);
    }

    @DeleteMapping("/delete/{inspirationId}/unLike/{userId}")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> unlikeInspiration(@PathVariable Integer inspirationId, @PathVariable Integer userId) {
        Inspiration inspiration = inspirationService.getInspirationById(inspirationId).orElse(null);
        User user = userService.getUserById(userId);

        if (inspiration == null || user == null) {
            return ResponseEntity.notFound().build();
        }

        inspirationService.unlikeInspiration(inspiration, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{inspirationId}/{userId}/hasLiked")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Boolean> hasLiked(@PathVariable Integer inspirationId, @PathVariable Integer userId) {
        Inspiration inspiration = inspirationService.getInspirationById(inspirationId).orElse(null);
        User user = userService.getUserById(userId);

        if (inspiration == null || user == null) {
            return ResponseEntity.notFound().build();
        }

        Boolean hasLiked = inspirationService.hasLiked(inspiration, user);
        return ResponseEntity.ok(hasLiked);
    }

    @GetMapping("/get/{inspirationId}/likes")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Integer> countLikes(@PathVariable Integer inspirationId) {
        Integer likes = inspirationService.countLikes(inspirationId);
        return ResponseEntity.ok(likes);
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

    @GetMapping("/get/{userId}/friendsInspirations")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetAllFriendsInspirationsDTO>> getFriendsInspirations(@PathVariable Integer userId) {
        List <GetAllFriendsInspirationsDTO> friendsInspirations = inspirationService.getFriendsInspirations(userId);
        return ResponseEntity.ok(friendsInspirations);
    }


    @PostMapping("/{inspirationId}/{userId}/comment")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> commentInspiration(@PathVariable Integer inspirationId, @PathVariable Integer userId, @RequestBody Map<String, String> request) {
        Inspiration inspiration = inspirationService.getInspirationById(inspirationId).orElse(null);
        User user = userService.getUserById(userId);
        String comment = request.get("comment");

        if (inspiration == null || user == null || comment == null) {
            return ResponseEntity.notFound().build();
        }


        inspirationService.commentInspiration(inspiration, user, comment);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{inspirationId}/getComments")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetInspirationCommentsDTO>> getComments(@PathVariable Integer inspirationId) {
        List <GetInspirationCommentsDTO> comments = inspirationService.getComments(inspirationId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{inspirationId}/countComments")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Integer> countComments(@PathVariable Integer inspirationId) {
        Integer comments = inspirationService.countComments(inspirationId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{inspirationId}/comment/{commentId}/deleteComment")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> deleteComment(@PathVariable Integer commentId) {
        inspirationService.deleteCommentById(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{userId}/liked")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetAllFriendsInspirationsDTO>> getLikedInspirations(@PathVariable Integer userId) {
        List<GetAllFriendsInspirationsDTO> inspirations = inspirationService.getLikedInspirations(userId);
        return ResponseEntity.ok(inspirations);
    }


}
