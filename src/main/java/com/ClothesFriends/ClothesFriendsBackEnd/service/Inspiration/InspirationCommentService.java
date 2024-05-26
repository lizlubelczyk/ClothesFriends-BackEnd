package com.ClothesFriends.ClothesFriendsBackEnd.service.Inspiration;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.Inspiration;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Inspiration.InspirationComment;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.Inspiration.InspirationCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspirationCommentService {

    @Autowired
    private InspirationCommentRepository inspirationCommentRepository;

    public void commentInspiration(Inspiration inspiration, User user, String comment) {
        InspirationComment inspirationComment = new InspirationComment();
        inspirationComment.setInspiration(inspiration);
        inspirationComment.setUser(user);
        inspirationComment.setComment(comment);
        inspirationCommentRepository.save(inspirationComment);


    }

    public void deleteComment(InspirationComment comment) {
        inspirationCommentRepository.delete(comment);
    }

    public List<InspirationComment> getComments(Integer inspirationId) {
        return inspirationCommentRepository.findAllByInspirationId(inspirationId);
    }

    public Integer countComments(Integer inspirationId) {
        return inspirationCommentRepository.findAllByInspirationId(inspirationId).size();
    }

    public InspirationComment getCommentById(Integer commentId) {
        return inspirationCommentRepository.findById(commentId).get();
    }


    public void deleteCommentById(Integer commentId) {
        inspirationCommentRepository.deleteById(commentId);
    }
}

