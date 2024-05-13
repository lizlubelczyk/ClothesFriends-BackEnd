package com.ClothesFriends.ClothesFriendsBackEnd.service.Inspiration;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration.CreateInspirationDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Inspiration;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Like;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.Inspiration.InspirationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InspirationService {

    @Autowired
    private InspirationRepository inspirationRepository;

    @Autowired
    private LikeService likeService;


    public InspirationService(InspirationRepository inspirationRepository) {
        this.inspirationRepository = inspirationRepository;
    }


    public Inspiration saveInspiration(CreateInspirationDTO inspiration, User user) throws IOException {
        Inspiration newInspiration = new Inspiration();
        newInspiration.setImage(saveImage(inspiration.getImage(), user.getId()));
        newInspiration.setDescription(inspiration.getDescription());
        newInspiration.setUser(user);
        return inspirationRepository.save(newInspiration);
    }

    private String saveImage(byte[] image, Integer userId) throws IOException {
        // Generate a unique filename for the image
        String filename = UUID.randomUUID().toString() + ".jpg";

        // Define the path to the user's inspiration directory
        Path inspirationDirectory = Paths.get("C:/Users/lizlu/OneDrive/Documentos/3ero/LabI/ClothesFriends-FrontEnd/public/images/" + userId + "/inspirations");

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
        return imagePath.toString();
    }

    public Integer countLikes(Integer inspirationId) {
        return likeService.countLikes(inspirationId);
    }

    public List<Inspiration> getInspirationsByUserId(Integer userId) {
        return inspirationRepository.findAllByUserId(userId);
    }


    public Optional<Inspiration> getInspirationById(Integer inspirationId) {
        return inspirationRepository.findById(inspirationId);
    }

    public Like likeInspiration(Inspiration inspiration, User user) {
        likeService.likeInspiration(inspiration, user);
        return null;
    }

    public void unlikeInspiration(Inspiration inspiration, User user) {
        likeService.unlikeInspiration(inspiration, user);
    }

    public void deleteInspirationById(Integer inspirationId) {
        inspirationRepository.deleteById(inspirationId);
    }
}
