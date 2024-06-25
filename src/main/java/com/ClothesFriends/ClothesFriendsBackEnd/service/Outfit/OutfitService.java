package com.ClothesFriends.ClothesFriendsBackEnd.service.Outfit;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.Outfit.*;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.Outfit;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.OutfitComment;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.Vote;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Outfit.VoteType;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.Outfit.OutfitRepository;
import com.ClothesFriends.ClothesFriendsBackEnd.service.FriendshipService;
import com.ClothesFriends.ClothesFriendsBackEnd.service.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OutfitService {

    @Autowired
    private OutfitRepository outfitRepository;

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private OutfitCommentService outfitCommentService;

    @Autowired
    private NotificationService notificationService;

    public Outfit findByUserId(Integer userId) {
        return outfitRepository.findByUserId(userId);
    }


    public Outfit saveOutfit(CreateOutfitDTO outfit, User user) throws IOException {
        Outfit newOutfit = new Outfit();
        newOutfit.setDescription(outfit.getDescription());
        newOutfit.setImage(saveImage(outfit.getImage(), user.getId()));
        newOutfit.setUser(user);
        return outfitRepository.save(newOutfit);
    }

    private String saveImage(byte[] image, Integer userId) throws IOException {
        // Generate a unique filename for the image
        String filename = UUID.randomUUID().toString() + ".jpg";

        // Define the path to the user's inspiration directory
        Path inspirationDirectory = Paths.get("C:/Users/lizlu/OneDrive/Documentos/3ero/LabI/ClothesFriends-FrontEnd/public/images/" + userId + "/outfits");

        // If the directory does not exist, create it
        if (!Files.exists(inspirationDirectory)) {
            Files.createDirectories(inspirationDirectory);
        }

        // Define the path to the image file
        Path imagePath = inspirationDirectory.resolve(filename);

        // Convert byte array back to an InputStream
        InputStream inputStream = new ByteArrayInputStream(image);

        // Save the image file
        Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
        return "/images/" + userId + "/outfits/" + filename;

    }

    // OutfitService.java
    public GetMyOutfitDTO getLatestOutfitByUserId(Integer userId){
        Outfit latestOutfit = outfitRepository.findLatestByUserId(userId);
        if (latestOutfit != null) {
            LocalDateTime createdAt = latestOutfit.getCreatedAt();
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(createdAt, now);
            if (duration.toMinutes() < 1) {  // Changed to 10 minutes
                return new GetMyOutfitDTO(latestOutfit.getId(), latestOutfit.getImage(), latestOutfit.getDescription());
            }
        }
        // Return an empty DTO to indicate no outfit found
        return new GetMyOutfitDTO(null, null, null);
    }


    public boolean hasOutfit(Integer userId) {
        GetMyOutfitDTO outfit = getLatestOutfitByUserId(userId);
        return outfit != null && outfit.getOutfitId() != null;
    }


    public Outfit getOutfitById(Integer outfitId) {
        return outfitRepository.findById(outfitId).orElse(null);
    }

    public void deleteOutfit(Outfit outfit) {
        outfitRepository.delete(outfit);
    }

    public List<GetFriendsOutfitsDTO> getFriendsOutfits(Integer userId) {
        List<User> friends = friendshipService.getFriends(userId);
        List<GetFriendsOutfitsDTO> friendsOutfits = new ArrayList<>();
        for (User friend : friends) {
            Outfit outfit = outfitRepository.findLatestByUserId(friend.getId());
            if (outfit != null) {
                LocalDateTime createdAt = outfit.getCreatedAt();
                LocalDateTime now = LocalDateTime.now();
                Duration duration = Duration.between(createdAt, now);
                if (duration.toMinutes() < 1) {
                    friendsOutfits.add(new GetFriendsOutfitsDTO(outfit.getId(), outfit.getDescription(), outfit.getImage(), friend.getUsername(), friend.getProfilePicture(), friend.getId()));
                }
            }

        }
        return friendsOutfits;
    }

    @Transactional
    public void voteOutfit(Outfit outfit, User user, VoteType voteType) {
        // Check if user has already voted, if yes update, otherwise create new vote
        Vote existingVote = voteService.getUsersVote(outfit, user);


        if (existingVote != null) {
            existingVote.setVoteType(voteType);
        } else {
            Vote vote = new Vote();
            vote.setOutfit(outfit);
            vote.setUser(user);
            vote.setVoteType(voteType);
            voteService.saveVote(vote);
        }
    }

    public VoteStatusDTO getUserVoteStatus(Integer outfitId, Integer userId) {
        Outfit outfit = outfitRepository.findById(outfitId).orElse(null);
        User user = new User();
        user.setId(userId);
        if (outfit == null) {
            return new VoteStatusDTO(false, null);
        }
        Vote vote = voteService.getUsersVote(outfit, user);
        if (vote == null) {
            return new VoteStatusDTO(false, null);
        }
        return new VoteStatusDTO(true, vote.getVoteType());
    }

    public void deleteVote(Outfit outfit, User user) {
        Vote vote = voteService.getUsersVote(outfit, user);
        if (vote != null) {
            voteService.deleteVote(vote);
        }
    }

    public void changeVote(Outfit outfit, User user, VoteType voteType) {
        Vote vote = voteService.getUsersVote(outfit, user);
        if (vote != null) {
            vote.setVoteType(voteType);
            voteService.saveVote(vote);
        }
    }

    public Integer countLikes(Integer outfitId) {
        return voteService.countLikes(outfitId);
    }

    public Integer countDislikes(Integer outfitId) {
        return voteService.countDislikes(outfitId);
    }

    public void commentOutfit(Outfit outfit, User user, String comment) {
        outfitCommentService.commentOutfit(outfit, user, comment);
        notificationService.notifyOutfitComment(outfit, user, comment);
    }

    public List<GetOutfitCommentsDTO> getComments(Integer outfitId) {
        List<OutfitComment> comments = outfitCommentService.getComments(outfitId);
        List<GetOutfitCommentsDTO> commentDTOs = new ArrayList<>();
        for (OutfitComment comment : comments) {
            commentDTOs.add(new GetOutfitCommentsDTO(comment.getUser().getId(), comment.getUser().getUsername(), comment.getComment(), comment.getUser().getProfilePicture(), comment.getId()));
        }

        return commentDTOs;
    }

    public Integer countComments(Integer outfitId) {
        return outfitCommentService.countComments(outfitId);
    }

    public void deleteComment(Integer commentId) {
        OutfitComment comment = outfitCommentService.getCommentById(commentId);
        outfitCommentService.deleteComment(comment);
    }

    public List<GetArchivedOutfitsDTO> getMyOutfits(Integer userId) {
        List<Outfit> outfits = outfitRepository.findAllByUserId(userId);
        List<GetArchivedOutfitsDTO> outfitDTOs = new ArrayList<>();
        for (Outfit outfit : outfits) {
            outfitDTOs.add(new GetArchivedOutfitsDTO(outfit.getId(), outfit.getImage()));
        }
        return outfitDTOs;
    }

    public GetArchivedOutfitDTO getArchivedOutfit(Integer outfitId) {
        Outfit outfit = outfitRepository.findById(outfitId).orElse(null);
        if (outfit != null) {
            return new GetArchivedOutfitDTO(outfit.getImage(), outfit.getCreatedAt());
        }
        return null;
    }
}
