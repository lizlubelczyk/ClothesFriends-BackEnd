package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.Outfit.*;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.Outfit;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.VoteType;
import com.ClothesFriends.ClothesFriendsBackEnd.service.Inspiration.LikeService;
import com.ClothesFriends.ClothesFriendsBackEnd.service.Outfit.OutfitService;
import com.ClothesFriends.ClothesFriendsBackEnd.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/outfit")
public class OutfitController {

    @Autowired
    private
    OutfitService outfitService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

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
    public ResponseEntity<GetMyOutfitDTO> getLatestOutfit(@PathVariable Integer userId) {
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
    public ResponseEntity<Boolean> hasOutfit(@PathVariable Integer userId) {
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
    public ResponseEntity<Void> deleteOutfit(@PathVariable Integer outfitId) {
        Outfit outfit = outfitService.getOutfitById(outfitId);
        if (outfit == null) {
            return ResponseEntity.status(404).build(); // Not found if outfit does not exist
        }

        outfitService.deleteOutfit(outfit);
        return ResponseEntity.noContent().build(); // No Content (204) on successful deletion
    }

    @GetMapping("get/{userId}/friendOutfits")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetFriendsOutfitsDTO>> getFriendsOutfits(@PathVariable Integer userId) {
        return ResponseEntity.ok(outfitService.getFriendsOutfits(userId));
    }

    @PostMapping("/{outfitId}/{userId}/vote")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> voteOutfit(@PathVariable Integer outfitId, @PathVariable Integer userId, @RequestBody Map<String, String> request) {
        Outfit outfit = outfitService.getOutfitById(outfitId);
        if (outfit == null) {
            return ResponseEntity.notFound().build(); // Not found if outfit does not exist
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build(); // Not found if user does not exist
        }

        String voteTypeString = request.get("voteType");
        if (voteTypeString == null) {
            return ResponseEntity.badRequest().build(); // Bad request if voteType is not provided
        }

        VoteType voteType;
        try {
            voteType = VoteType.valueOf(voteTypeString);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Bad request if voteType is invalid
        }

        outfitService.voteOutfit(outfit, user, voteType);
        return ResponseEntity.noContent().build(); // No Content (204) on successful vote
    }


    @GetMapping("/{outfitId}/{userId}/getVoteStatus")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<VoteStatusDTO> getVoteStatus(@PathVariable Integer outfitId, @PathVariable Integer userId) {
        VoteStatusDTO voteStatus = outfitService.getUserVoteStatus(outfitId, userId);
        return ResponseEntity.ok(voteStatus);
    }

    @DeleteMapping("/{outfitId}/{userId}/deleteVote")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> deleteVote(@PathVariable Integer outfitId, @PathVariable Integer userId) {
        Outfit outfit = outfitService.getOutfitById(outfitId);
        if (outfit == null) {
            return ResponseEntity.notFound().build(); // Not found if outfit does not exist
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build(); // Not found if user does not exist
        }

        outfitService.deleteVote(outfit, user);
        return ResponseEntity.noContent().build(); // No Content (204) on successful deletion
    }

    @PostMapping("/{outfitId}/{userId}/changeVote")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> changeVote(@PathVariable Integer outfitId, @PathVariable Integer userId, @RequestBody Map<String, String> request) {
        Outfit outfit = outfitService.getOutfitById(outfitId);
        if (outfit == null) {
            return ResponseEntity.notFound().build(); // Not found if outfit does not exist
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build(); // Not found if user does not exist
        }

        String voteTypeString = request.get("voteType");
        if (voteTypeString == null) {
            return ResponseEntity.badRequest().build(); // Bad request if voteType is not provided
        }

        VoteType voteType;
        try {
            voteType = VoteType.valueOf(voteTypeString);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Bad request if voteType is invalid
        }

        outfitService.changeVote(outfit, user, voteType);
        return ResponseEntity.noContent().build(); // No Content (204) on successful vote
    }

    @GetMapping("/{outfitId}/getLikes")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Integer> getLikes(@PathVariable Integer outfitId) {
        return ResponseEntity.ok(outfitService.countLikes(outfitId));
    }

    @GetMapping("/{outfitId}/getDislikes")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Integer> getDislikes(@PathVariable Integer outfitId) {
        return ResponseEntity.ok(outfitService.countDislikes(outfitId));
    }

    @PostMapping("/{outfitId}/{userId}/comment")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> commentOutfit(@PathVariable Integer outfitId, @PathVariable Integer userId, @RequestBody Map<String, String> request) {
        Outfit outfit = outfitService.getOutfitById(outfitId);
        if (outfit == null) {
            return ResponseEntity.notFound().build(); // Not found if outfit does not exist
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build(); // Not found if user does not exist
        }

        String comment = request.get("comment");
        if (comment == null) {
            return ResponseEntity.badRequest().build(); // Bad request if comment is not provided
        }

        outfitService.commentOutfit(outfit, user, comment);
        return ResponseEntity.noContent().build(); // No Content (204) on successful comment
    }

    @GetMapping("/{outfitId}/getComments")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetOutfitCommentsDTO>> getComments(@PathVariable Integer outfitId) {
        return ResponseEntity.ok(outfitService.getComments(outfitId));
    }

    @GetMapping("/{outfitId}/countComments")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Integer> countComments(@PathVariable Integer outfitId) {
        return ResponseEntity.ok(outfitService.countComments(outfitId));
    }

    @DeleteMapping("/{outfitId}/comment/{commentId}/deleteComment")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer outfitId, @PathVariable Integer commentId) {
        outfitService.deleteComment(commentId);
        return ResponseEntity.noContent().build(); // No Content (204) on successful deletion
    }

    @GetMapping("/{userId}/getMyOutfits")
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<GetArchivedOutfitsDTO>> getMyOutfits(@PathVariable Integer userId) {
        return ResponseEntity.ok(outfitService.getMyOutfits(userId));
    }

    @GetMapping(value = "/{outfitId}/getOutfitById", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetArchivedOutfitDTO> getOutfitById(@PathVariable Integer outfitId) {
        GetArchivedOutfitDTO outfit = outfitService.getArchivedOutfit(outfitId);
        return ResponseEntity.ok(outfit);
    }






}
